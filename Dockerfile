FROM anapsix/alpine-java

MAINTAINER Up2Go-DavidRenz

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN addgroup app -g 9000 && adduser -g 9000 -u 9000 -S -s /bin/false app

RUN chown -R app /usr/src/app

VOLUME /code
WORKDIR /code

USER app

CMD java -cp /usr/src/app Pmd
