# 参考：https://nodejs.org/zh-cn/docs/guides/nodejs-docker-webapp

# 使用 Node 镜像
FROM node:18

# 工作目录
WORKDIR /usr/src/app

# 复制当前目录下的 package.json 和 package-lock.yaml 到工作目录
COPY package*.json ./

# 设置 npm 镜像
RUN npm config set registry https://registry.npm.taobao.org
# 安装依赖，开发环境
RUN npm install
# 安装依赖，生产环境
# RUN npm ci --omit=dev

# 复制当前目录下的所有文件到工作目录
COPY . .

# 应用程序端口
EXPOSE 3000

# 安装 proxychains
RUN apt update && apt install -y proxychains-ng
# 配置 /etc/proxychains4.conf，取消注释 dynamic_chain，注释 socks4 127.0.0.1 开头的行，末尾添加 socks5 IP 端口
RUN sed -i -e '/^#dynamic_chain/s/^#//;/^socks4[[:space:]]\+127\.0\.0\.1\b/s/^/#/;$a socks5 192.168.123.42 7890' /etc/proxychains4.conf
# 排除后端服务器地址
RUN sed -i -e '/## RFC1918 Private Address Ranges/a localnet 192.168.123.0/255.0.0.0' /etc/proxychains4.conf

# 运行，开发环境。“|| true”表示前面的命令运行失败，容器仍然继续运行
ENTRYPOINT ["proxychains", "npm", "run", "start:dev" ,"||" ,"true"]
#ENTRYPOINT [ "npm", "run", "start:dev" ,"||" ,"true"]
# 运行，生产环境
#ENTRYPOINT ["proxychains", "npm", "run", "start" ,"||" ,"true"]
