FROM gradle:jdk11 as build
LABEL maintainer "Julius Krah <juliuskrah@gmail.com>"

COPY --chown=gradle:gradle . /home/gradle
WORKDIR /home/gradle
RUN gradle assemble

FROM openjdk:11-jre as base
RUN useradd -ms /bin/bash command
WORKDIR /home/command
ENV JAESOFT_SLEEP=0 \ 
    JAVA_OPTS=""

ADD src/main/docker/entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown command:command entrypoint.sh
USER command

COPY --from=build /home/gradle/build/libs/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT [ "./entrypoint.sh" ]