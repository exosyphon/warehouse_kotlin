FROM ubuntu:latest

RUN apt-get update
RUN apt-get install -y curl gnupg build-essential inotify-tools postgresql-client git openssl libpq-dev && \
                                                                                                             apt-get clean;
RUN \curl -sSL https://rvm.io/mpapis.asc | gpg --import -
RUN \curl -L https://get.rvm.io | bash -s stable
RUN /bin/bash -l -c "rvm requirements"
RUN /bin/bash -l -c "rvm install 2.5.1"
RUN /bin/bash -l -c "gem install bundler --no-ri --no-rdoc"
RUN /bin/bash -l -c "echo 'source /usr/local/rvm/scripts/rvm' >> ~/.bashrc"

ENV PATH="${PATH}:/usr/local/rvm/bin"

RUN curl -sS -o - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add
RUN echo "deb [arch=amd64]  http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list
RUN apt-get -y update
RUN apt-get -y install google-chrome-stable

WORKDIR /app
