const { SlashCommandBuilder } = require('discord.js');
const { about } = require('../../config.json');

module.exports = {
  // 命令冷却时间（单位：秒）
  cooldown: 30,
  data: new SlashCommandBuilder()
    .setName('about')
    .setDescription('About this community.'),
  async execute(interaction) {
    // 延迟响应，需要更多时间回复 https://discordjs.guide/slash-commands/response-methods.html#deferred-responses
    await interaction.deferReply({ ephemeral: true });

    // 循环 config.js 中 about 下的所有键值对
    let result = '';
    for (const [key, value] of Object.entries(about)) {
      result += `${key}: ${value}\n`;
    }
    // 回复
    // await interaction.reply(result)
    interaction.editReply(result);
  },
};
