const { feishu } = require('../config.js');

module.exports = {
  /**
   * 列出友链
   *
   * @param threadid 📨帖子话题-posts 中的帖子 ID
   * @param msgid 📨帖子话题-posts 中的帖子的消息 ID
   * @returns {Promise<any>} 友链列表
   */
  listFriendChain: async ({ threadid, msgid }) => {
    if (!msgid) {
      msgid = threadid;
    }
    const response = await fetch(feishu.baseUrl + '/bitable/apps/' + feishu.friendChain.appToken + '/tables/' + feishu.friendChain.tableId + '/records?' +
      new URLSearchParams({
        "filter": `AND(CurrentValue.[threadid]="${threadid}",CurrentValue.[msgid]="${msgid}")`,
      }).toString(), {
      method: 'GET'
    });
    return await response.json();
  },
  /**
   * 保存友链
   *
   * @param threadid 📨帖子话题-posts 中的帖子 ID
   * @param msgid 📨帖子话题-posts 中的帖子的消息 ID
   * @param name 博客名
   * @param url 链接
   * @param icon ICON 链接
   * @param description 描述
   * @returns {Promise<any>} 保存结果
   */
  saveFriendChain: async ({ threadid, msgid, name, url, icon, description }) => {
    const response = await fetch(feishu.baseUrl + '/bitable/apps/' + feishu.friendChain.appToken + '/tables/' + feishu.friendChain.tableId + '/records', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json;charset=UTF-8' },
      body: JSON.stringify({
        "fields": {
          "threadid": threadid,
          "msgid": msgid,
          "博客名（Blog Name）": name,
          "链接（URL）": { "text": url, "link": url },
          "ICON 链接（ICON URL）": { "text": icon, "link": icon },
          "描述（Description）": description
        }
      })
    });
    return await response.json();
  },
  /**
   * 更新友链
   * @param recordId 记录 ID
   * @param name 博客名
   * @param url 链接
   * @param icon ICON 链接
   * @param description 描述
   * @returns {Promise<any>} 更新结果
   */
  updateFriendChain: async ({ recordId, name, url, icon, description }) => {
    const response = await fetch(feishu.baseUrl + '/bitable/apps/' + feishu.friendChain.appToken + '/tables/' + feishu.friendChain.tableId + '/records/' + recordId, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json;charset=UTF-8' },
      body: JSON.stringify({
        "fields": {
          "博客名（Blog Name）": name,
          "链接（URL）": { "text": url, "link": url },
          "ICON 链接（ICON URL）": { "text": icon, "link": icon },
          "描述（Description）": description
        }
      })
    });
    return await response.json();
  }
}
