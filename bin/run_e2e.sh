#!/bin/bash

set -e
source /usr/local/rvm/scripts/rvm

cd e2e
bundle install
bundle exec rspec