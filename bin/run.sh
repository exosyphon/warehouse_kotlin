#!/bin/bash

set -e

# Wait for Postgres to become available.
until pg_isready -h $POSTGRES_HOST -U $POSTGRES_USER; do
  >&2 echo "Postgres is unavailable - sleeping"
  sleep 1
done

echo "\nPostgres is available: continuing with database setup...."

cd frontend
yarn serve &
cd ..
./gradlew bootRun