MAINTAINER Up2Go-DavidRenz

WORKDIR /usr/src/app/

COPY engine.json /
COPY package.json /usr/src/app/

RUN useradd -u 9000 -r -s /bin/false app
USER app

COPY . /usr/src/app

VOLUME /code
WORKDIR /code

CMD ["/usr/src/app/bin/pmd.java"]