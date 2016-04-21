FROM anapsix/alpine-java

MAINTAINER Up2Go-DavidRenz

RUN addgroup app -g 9000 && adduser -g 9000 -u 9000 -S -s /bin/false app 

VOLUME /code
WORKDIR /code

COPY . /usr/src/app

User app

CMD ["java", "/usr/src/app/bin/Pmd"]