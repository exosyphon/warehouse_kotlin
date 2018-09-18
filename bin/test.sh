#!/bin/bash

set -e
source /usr/local/rvm/scripts/rvm

# Wait for Postgres to become available.
until pg_isready -h $POSTGRES_HOST -U $POSTGRES_USER; do
  >&2 echo "Postgres is unavailable - sleeping"
  sleep 1
done

echo "\nPostgres is available: continuing with database setup...."

./gradlew test
cd frontend
yarn install
yarn test
cd ../e2e
bundle
bundle exec rspec