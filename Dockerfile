# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER len <891846581@qq.com>
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为task.jar
ADD task-0.0.1-SNAPSHOT.jar task.jar
# 运行jar包
RUN echo 'Asia/Shanghai' >/etc/timezone
RUN bash -c 'touch /task.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/task.jar"]