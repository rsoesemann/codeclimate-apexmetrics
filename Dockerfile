FROM java

MAINTAINER Up2Go-DavidRenz

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN groupadd app -g 9000 && useradd -g 9000 -u 9000 -r -s /bin/false app 

RUN javac /usr/src/app/Pmd.java

RUN chown -R app /usr/src/app

VOLUME /code
WORKDIR /code

USER app

CMD ["java", "/usr/src/app/Pmd"]
