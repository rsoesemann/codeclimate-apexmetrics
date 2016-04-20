FROM java:8

MAINTAINER Up2Go-DavidRenz

RUN groupadd app -g 9000
RUN useradd -g 9000 -u 9000 -r -s /bin/false app 

VOLUME /code
WORKDIR /code

COPY . /usr/src/app

USER app

RUN ["javac /usr/src/app/bin/Pmd.java"]
CMD ["java /usr/src/app/bin/Pmd"]