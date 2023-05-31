# 简介

MyCommunity，集成 Discord、飞书、QQ 频道等的机器人，旨在帮助社区成员更好地交流和分享资源。

# 使用教程

## 命令

| 名称     | 内容 |示例|
|--------|----|-|
| /about | 关于 ||

# 部署

## Discord

### 编写 Dockerfile 文件

[discord/Dockerfile](./discord/Dockerfile)

参考：[把一个 Node.js web 应用程序给 Docker 化 | Node.js](https://nodejs.org/zh-cn/docs/guides/nodejs-docker-webapp)

### 构建镜像

```bash
docker build . -t csaf/my-community-discord
```

### 运行

```bash
docker run -d --name my-community-discord -e DISCORD_BOT_TOKEN= csaf/my-community-discord
```
