#!/bin/bash

set -e

cd e2e
bundle install
bundle exec rspec