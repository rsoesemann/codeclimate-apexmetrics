FROM java:8-jre

MAINTAINER up2go-rsoesemann

RUN cd /tmp && \
     wget http://dl.bintray.com/groovy/maven/apache-groovy-binary-2.4.6.zip && \ 
	 unzip apache-groovy-binary-2.4.6.zip && \ 
	 mv groovy-2.4.6 /groovy  && \ 
	 rm apache-groovy-binary-2.4.6.zip

ENV GROOVY_HOME /groovy
ENV PATH $GROOVY_HOME/bin/:$PATH

RUN groupadd app -g 9000 && useradd -g 9000 -u 9000 -r -s /bin/false app

VOLUME /code
WORKDIR /code
COPY . /usr/src/app

USER app

CMD ["/usr/src/app/pmd.groovy", "--codeFolder=/code","--configFile=/config.json"]
