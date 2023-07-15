# 基础镜像
FROM azul/zulu-openjdk-alpine:17-latest

LABEL maintainer="duanluan <duanluan@outlook.com>"
LABEL version="0.0.1"

# 将 feishu/target 下的 jar 包复制到容器中并命名为 app.jar
ADD feishu/build/libs/*.jar /app/app.jar

# 可以映射的端口
EXPOSE 8080

# 运行 jar 包
ENTRYPOINT ["java","-jar","/app/app.jar"]
