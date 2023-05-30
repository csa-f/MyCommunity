const { Events, Collection } = require('discord.js');

module.exports = {
  name: Events.InteractionCreate,
  async execute(interaction) {
    if (!interaction.isChatInputCommand()) return;

    const command = interaction.client.commands.get(interaction.commandName);

    if (!command) {
      console.error(`没有找到与 ${interaction.commandName} 相匹配的命令。`);
      return;
    }


    const { cooldowns } = interaction.client;

    if (!cooldowns.has(command.data.name)) {
      cooldowns.set(command.data.name, new Collection());
    }

    const now = Date.now();
    // 触发命令的用户 ID 和时间戳键值对集合的引用
    const timestamps = cooldowns.get(command.data.name);
    const defaultCooldownDuration = 3;
    // 命令指定冷却时间
    const cooldownAmount = (command.cooldown ?? defaultCooldownDuration) * 1000;

    if (timestamps.has(interaction.user.id)) {
      const expirationTime = timestamps.get(interaction.user.id) + cooldownAmount;

      if (now < expirationTime) {
        const expiredTimestamp = Math.round(expirationTime / 1000);
        return interaction.reply({
          content: `Please wait, you are on a cooldown for \`${command.data.name}\`. You can use it again <t:${expiredTimestamp}:R>.`,
          ephemeral: true
        }).then(() => {
          const timeLeft = expirationTime - now;
          setTimeout(() => interaction.deleteReply().catch(console.error), timeLeft);
        });
      }
    }
    timestamps.set(interaction.user.id, now);
    // 指定时间后删除超时
    setTimeout(() => {
      timestamps.delete(interaction.user.id);
    }, cooldownAmount);


    try {
      await command.execute(interaction);
    } catch (error) {
      console.error(`Error executing ${interaction.commandName}`);
      console.error(error);

      // // 如果交互已经被回复或者已经被推迟
      // if (interaction.replied || interaction.deferred) {
      //   // 通过 followUp 方法回复错误信息
      //   await interaction.followUp({ content: '执行该命令时出错了！', ephemeral: true });
      // } else {
      //   // 否则通过 reply 方法回复错误信息
      //   await interaction.reply({ content: '执行该命令时出错了！', ephemeral: true });
      // }
    }
  },
};
