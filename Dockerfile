# 选择openjdk镜像
FROM openjdk:alpine
# 作者信息
LABEL author=Hongyan.Zhang3@outlook.com
WORKDIR /opt/competition/
# 复制Jar包到docker目录
COPY target/competition-server-0.0.1-SNAPSHOT.jar .
# 复制配置文件到docker目录
COPY /src/main/resources/application.yml .
# 运行Jar包
CMD java -jar ./competition-server-0.0.1-SNAPSHOT.jar
# 暴露8080端口
EXPOSE 8080