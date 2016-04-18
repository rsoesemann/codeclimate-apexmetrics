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

RUN groupadd app -g 9000
RUN useradd -g 9000 -u 9000 -r -s /bin/false app 

VOLUME /code
WORKDIR /code

COPY . /usr/src/app

RUN ["javac /usr/src/app/bin/Apex.java"]
CMD ["java /usr/src/app/bin/Apex"]