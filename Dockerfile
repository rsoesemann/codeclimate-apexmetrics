FROM java

MAINTAINER Up2Go-DavidRenz

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN groupadd app -g 9000 && useradd -g 9000 -u 9000 -r -s /bin/false app 
RUN chown -R app .

USER app

VOLUME /code
WORKDIR /code

RUN javac /usr/src/app/bin/Pmd.java -d /usr/src/app
CMD ["java", "/usr/src/app/Pmd"]
