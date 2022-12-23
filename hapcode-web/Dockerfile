#基于java8
FROM adoptopenjdk/openjdk8

#创建一个目录存放jar包(在容器里面创建目录)
RUN mkdir -p /home/logs/hapcode-premium

#复制jar包以及相关配置文件(复制jar包到容器的根目录)
COPY ./target/hapcode-premium.jar /hapcode-premium.jar

#添加进入docker容器后的目录
WORKDIR /usr/local/hapcode-premium

#配置项目端口
CMD ["--server.port=8080"]

#对外暴露的端口号
EXPOSE 8080

#运行脚本，启动springboot项目，这里我们指定加载配置文件的位置，并且通过数据卷挂载同步到容器中
ENTRYPOINT ["java","-jar","/hapcode-premium.jar"]
