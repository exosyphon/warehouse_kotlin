FROM ubuntu:latest

RUN apt-get update
RUN apt-get install -y curl gnupg
RUN curl -sL https://deb.nodesource.com/setup_6.x | bash
RUN apt-get install -y build-essential inotify-tools postgresql-client nodejs autoconf bison libssl-dev libyaml-dev libreadline6-dev zlib1g-dev libncurses5-dev libffi-dev yarn

# Install OpenJDK-8
RUN apt-get install -y openjdk-8-jdk && \
    apt-get install -y ant && \
    apt-get clean;

# Fix certificate issues
RUN apt-get update && \
    apt-get install ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f;

# Setup JAVA_HOME -- useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

RUN apt-get install -y gradle

ENV GRADLE_HOME=/usr/lib/gradle-3.4.1-7ubuntu1
ENV PATH=$PATH:$GRADLE_HOME/bin

WORKDIR /app
EXPOSE 8080
