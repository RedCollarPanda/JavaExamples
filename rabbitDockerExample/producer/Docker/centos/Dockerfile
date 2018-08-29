FROM centos

RUN yum install -y wget
RUN wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/10.0.2+13/19aef61b38124481863b1413dce1855f/jdk-10.0.2_linux-x64_bin.rpm
RUN yum localinstall -y jdk-10.0.2_linux-x64_bin.rpm
VOLUME /tmp
ADD /config.properties config.properties
ADD /producer-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=config.properties"]
RUN java -version
RUN javac -version


