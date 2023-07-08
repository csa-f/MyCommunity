const { feishu } = require('../config.js');

module.exports = {
  saveFriendChain: async ({ description, icon, name, url }) => {
    return await fetch(feishu.baseUrl + '/bitable/apps/' + feishu.friendChain.appToken + '/tables/' + feishu.friendChain.tableId + '/records', {
      method: 'POST',
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
  }
}
