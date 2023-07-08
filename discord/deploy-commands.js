// 引入discord.js库中的REST和Routes模块
const { REST, Routes } = require('discord.js');
// 引入配置文件中的applicationId、guildId
const { applicationId, guildId } = require('./config.js');
// 引入Node.js中的fs和path模块
const fs = require('node:fs');
const path = require('node:path');

const DISCORD_BOT_TOKEN = process.env.DISCORD_BOT_TOKEN;
if (!DISCORD_BOT_TOKEN) {
  console.log('[ERROR] DISCORD_BOT_TOKEN is not set in environment variables.');
  process.exit(1);
}

// 创建空数组用于存储所有命令的数据
const commands = [];
// 获取命令文件夹中的所有命令文件
const foldersPath = path.join(__dirname, 'commands');
const commandFolders = fs.readdirSync(foldersPath);

// 遍历所有命令文件夹
for (const folder of commandFolders) {
  // 获取当前命令文件夹中的所有命令文件
  const commandsPath = path.join(foldersPath, folder);
  const commandFiles = fs.readdirSync(commandsPath).filter(file => file.endsWith('.js'));
  // 遍历当前命令文件夹中的所有命令文件，将每个命令的数据转换为JSON格式并存储到数组中
  for (const file of commandFiles) {
    const filePath = path.join(commandsPath, file);
    const command = require(filePath);
    if ('data' in command && 'execute' in command) {
      commands.push(command.data.toJSON());
    } else {
      console.log(`[WARNING] The command at ${filePath} is missing a required "data" or "execute" property.`);
    }
  }
}

// 创建REST模块的实例并设置token
const rest = new REST().setToken(DISCORD_BOT_TOKEN);

// 部署所有应用(/)命令
(async () => {
  try {
    console.log(`Started refreshing ${commands.length} application (/) commands.`);

    // 使用put方法将所有应用(/)命令部署到指定的服务器
    const data = await rest.put(
      // 部署到所有服务器
      // Routes.applicationCommands(applicationId),
      // 部署到指定服务器
      Routes.applicationGuildCommands(applicationId, guildId),
      { body: commands },
    );

    console.log(`Successfully reloaded ${data.length} application (/) commands.`);
  } catch (error) {
    // 如果有错误，则打印错误信息
    console.error(error);
  }
})();
