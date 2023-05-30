const fs = require('node:fs'); // 引入 Node.js 的文件系统模块
const path = require('node:path'); // 引入 Node.js 的路径处理模块
// 引入 discord.js 的必要类
const { Client, GatewayIntentBits, Collection } = require('discord.js');

const DISCORD_BOT_TOKEN = process.env.DISCORD_BOT_TOKEN;
if (!DISCORD_BOT_TOKEN) {
  console.log('[ERROR] DISCORD_BOT_TOKEN is not set in environment variables.');
  process.exit(1);
}

// 创建一个新的客户端实例
const client = new Client({ intents: [GatewayIntentBits.Guilds] });

client.cooldowns = new Collection(); // 命令冷却集合
client.commands = new Collection(); // 命令集合

// 从 commands 目录读取命令文件
const foldersPath = path.join(__dirname, 'commands'); // 拼接出命令文件所在的路径
const commandFolders = fs.readdirSync(foldersPath);

for (const folder of commandFolders) {
  const commandsPath = path.join(foldersPath, folder);
  const commandFiles = fs.readdirSync(commandsPath).filter(file => file.endsWith('.js'));
  for (const file of commandFiles) {
    const filePath = path.join(commandsPath, file);
    const command = require(filePath);
    // Set a new item in the Collection with the key as the command name and the value as the exported module
    if ('data' in command && 'execute' in command) {
      client.commands.set(command.data.name, command);
    } else {
      console.log(`[WARNING] The command at ${filePath} is missing a required "data" or "execute" property.`);
    }
  }
}

// 注册事件监听器
const eventsPath = path.join(__dirname, 'events');
const eventFiles = fs.readdirSync(eventsPath).filter(file => file.endsWith('.js'));
for (const file of eventFiles) {
  const filePath = path.join(eventsPath, file);
  const event = require(filePath);
  if (event.once) {
    client.once(event.name, (...args) => event.execute(...args));
  } else {
    client.on(event.name, (...args) => event.execute(...args));
  }
}

// 使用客户端的 token 登录 Discord
client.login(DISCORD_BOT_TOKEN);
