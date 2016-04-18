FROM ubuntu:15.04

MAINTAINER Up2Go-DavidRenz

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean

WORKDIR /usr/src/app/

COPY engine.json /
COPY package.json /usr/src/app/

RUN useradd -u 9000 -r -s /bin/false app
USER app

COPY . /usr/src/app

VOLUME /code
WORKDIR /code

RUN javac Apex.java
CMD java Apex