# 使用官方的 Java 8 镜像作为基础镜像
FROM openjdk:8-jdk-alpine

# 设置工作目录
WORKDIR /app

# 将本地的 JAR 文件复制到容器中的 /app 目录下
COPY target/common-api-4.3.jar  /app/app.jar

# 暴露应用程序运行的端口（假设你的应用程序运行在 8080 端口）
EXPOSE 8890

# 设置容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "app.jar"]