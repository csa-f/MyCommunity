const { SlashCommandBuilder } = require('discord.js');
const { friendChain } = require('../../config.json');

module.exports = {
  // 命令冷却时间（单位：秒）
  cooldown: 30,
  data: new SlashCommandBuilder()
    .setName('friendchain')
    .setDescription('Friend chain.')
    .addSubcommand(subCmd =>
      // 帮助
      subCmd.setName('help').setDescription('Show help.'))
    .addSubcommand(subCmd =>
      // 所有友链
      subCmd.setName('ls').setDescription('List all friend chains.'))
    .addSubcommand(subCmd =>
      // 添加友链
      subCmd.setName('add').setDescription('Add a new friend chain entry with a post ID tagged as \'Friend chain\' in the \'posts\' channel.')
        .addStringOption(option =>
          // 帖子线程 ID
          option.setName('threadid').setDescription('The thread ID of the post.').setRequired(true))
        .addStringOption(option =>
          // 消息 ID，如果为空则默认为第一条消息
          option.setName('msgid').setDescription('The message ID of the post.'))
    ),

  async execute(interaction) {
    // 延迟响应
    await interaction.deferReply({ ephemeral: true });

    const subCmdName = interaction.options.getSubcommand();
    switch (subCmdName) {
      // 帮助
      case 'help':
        return interaction.editReply(friendChain.helpContent);
      // 所有友链+
      case 'ls':
        return interaction.editReply(friendChain.feishuUrl);
      // 添加友链
      case 'add': {
        const threadid = interaction.options.getString('threadid');
        let msgid = interaction.options.getString('msgid');

        const client = interaction.client;
        // 频道
        const channel = client.channels.cache.get(friendChain.channelId);
        // 频道线程
        const thread = await channel.threads.fetch(threadid);
        if (!thread) {
          return interaction.editReply(`Thread \`${threadid}\` not found!`);
        }
        // 不指定消息 ID 时默认为第一条消息（和帖子线程 ID 相同）
        if (!msgid) {
          msgid = threadid;
        }
        // 消息
        const message = await thread.messages.fetch({ message: msgid, cache: false, force: true })
        // 消息内容
        const msgContent = message.content;
        // 正则匹配消息内容
        const name = msgContent.match(/\bname\s*:(.*?)(\n|$)/)[1];
        const url = msgContent.match(/\burl\s*:(.*?)(\n|$)/)[1];
        const icon = msgContent.match(/\bicon\s*:(.*?)(\n|$)/)[1];
        const description = msgContent.match(/\bdescription\s*:(.*?)(\n|$)/)[1];
        // 调用飞书 API
        const response = await fetch('http://192.168.123.177:8080/feishu/bitable/apps/C0UfbTai0a93BFsUrjOcFd0JnJf/tables/tblZ9a2NAlBvQ4Jz/records', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json;charset=UTF-8' },
          body: JSON.stringify({
            "fields": {
              "博客名（Blog Name）": name,
              "链接（URL）": { "text": url, "link": url },
              "ICON 链接（ICON URL）":  { "text": icon, "link": icon },
              "描述（Description）": description
            }
          })
        });
        const data = await response.json();
        console.log(data);
        if (data.code === 0) {
          return interaction.editReply(`Friend chain \`${name}\` added!`);
        }
        return interaction.editReply(`Friend chain \`${name}\` add failed!`);
      }
    }

    // 不支持的命令
    return interaction.editReply(`There is no command with name \`${subCmdName}\`!`);
  },
};
