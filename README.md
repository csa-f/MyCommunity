# 简介

MyCommunity，集成 Discord、飞书、QQ 频道等的机器人，旨在帮助社区成员更好地交流和分享资源。

# 使用教程

## 命令

| 名称           | 子命令    | 内容                                                                                 | 示例                                                                  |
|--------------|--------|------------------------------------------------------------------------------------|---------------------------------------------------------------------|
| /about       |        | 关于                                                                                 |                                                                     |
| /friendchain |        | 友链                                                                                 |                                                                     |
|              | help   | 帮助                                                                                 |                                                                     |
|              | ls     | 查看友链                                                                               |                                                                     |
|              | add    | 添加。<br/>`threadid`为`📨帖子话题-posts`中的帖子 ID。<br/>`msgid`为帖子中的消息 ID，如果是帖子中的第一条消息时可以忽略。 | /friendchain add threadid 1114623764911640687 msgid 1114623764911640687 |
|              | update | 更新。<br/>`threadid`为`📨帖子话题-posts`中的帖子 ID。<br/>`msgid`为帖子中的消息 ID，如果是帖子中的第一条消息时可以忽略。 | /friendchain update threadid 1114623764911640687 |

# 部署

## Discord

### 编写 Dockerfile 文件

[discord/Dockerfile](./discord/Dockerfile)

参考：[把一个 Node.js web 应用程序给 Docker 化 | Node.js](https://nodejs.org/zh-cn/docs/guides/nodejs-docker-webapp)

### 停止删除容器和镜像

```bash
docker stop my-community-discord & docker rm my-community-discord & docker rmi csaf/my-community-discord
```

### 构建镜像

```bash
docker build . -t csaf/my-community-discord
```

### 运行容器

```bash
docker run -d --name my-community-discord -e DISCORD_BOT_TOKEN= csaf/my-community-discord
```

### 合并运行

```bash
docker stop my-community-discord & docker rm my-community-discord & docker rmi csaf/my-community-discord & docker build . -t csaf/my-community-discord & docker run -d --name my-community-discord -e DISCORD_BOT_TOKEN= csaf/my-community-discord
```

### 导出导入镜像

```bash
docker save -o my-community-discord.tar csaf/my-community-discord:latest
docker load -i my-community-discord.tar
```

## Feishu
