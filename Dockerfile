FROM ubuntu:latest

RUN apt-get update
RUN apt-get install -y curl gnupg
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add -
RUN echo "deb https://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list
RUN apt-get install -y build-essential inotify-tools postgresql-client nodejs git openssl libpq-dev && \
                                                                                                             apt-get clean;
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

RUN \curl -sSL https://rvm.io/mpapis.asc | gpg --import -
RUN \curl -L https://get.rvm.io | bash -s stable
RUN /bin/bash -l -c "rvm requirements"
RUN /bin/bash -l -c "rvm install 2.5.1"
RUN /bin/bash -l -c "gem install bundler --no-ri --no-rdoc"
RUN /bin/bash -l -c "echo 'source /usr/local/rvm/scripts/rvm' >> ~/.bashrc"

ENV PATH="${PATH}:/usr/local/rvm/bin"

RUN apt-get install -y yarn

RUN curl -sS -o - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add
RUN echo "deb [arch=amd64]  http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list
RUN apt-get -y update
RUN apt-get -y install google-chrome-stable

RUN mkdir /app
COPY . /app
WORKDIR /app

RUN mkdir -p /root/.gradle
ENV HOME /root
VOLUME /root/.gradle

ENV SPRING_PROFILES_ACTIVE container
RUN export SPRING_PROFILES_ACTIVE

RUN ./gradlew assemble
RUN cd /app/frontend;yarn install
RUN /bin/bash -l -c "source ~/.bashrc;cd /app/e2e;bundle install"

EXPOSE 8080
EXPOSE 8081
