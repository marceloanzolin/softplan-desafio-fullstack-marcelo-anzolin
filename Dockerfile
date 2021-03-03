FROM openjdk:8

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/starter

COPY /target/softplan-desafio-fullstack-marcelo-anzolin-0.0.1-SNAPSHOT.jar softplan_marcelo_anzolin.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 8080
EXPOSE 5005

CMD java ${ADDITIONAL_OPTS} -jar softplan_marcelo_anzolin.jar --spring.profiles.active=${PROFILE}