FROM openjdk:8u181

RUN apt-get update
RUN apt-get install -y build-essential inotify-tools postgresql-client openssl libpq-dev && \
                                                                                                             apt-get clean;
RUN mkdir -p /root/.gradle
ENV HOME /root
VOLUME /root/.gradle

ENV SPRING_PROFILES_ACTIVE container
RUN export SPRING_PROFILES_ACTIVE

RUN mkdir /app
COPY . /app
WORKDIR /app

WORKDIR /app
EXPOSE 8080
