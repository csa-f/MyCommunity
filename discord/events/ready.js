const { Events } = require('discord.js');

module.exports = {
  name: Events.ClientReady,
  once: true,
  execute(client) {
    console.log(`已准备就绪！登录账号为 ${client.user.tag}`);
  },
};
