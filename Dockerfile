FROM java

MAINTAINER Up2Go-DavidRenz

RUN groupadd app -g 9000 && useradd -g 9000 -u 9000 -r -s /bin/false app 

VOLUME /code
WORKDIR /code

COPY . /usr/src/app

USER app

RUN java -version

RUN javac /usr/src/app/bin/Pmd.java
CMD ["java", "/usr/src/app/bin/Pmd"]