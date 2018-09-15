# Prerequisites
 * git
 * jdk 8.171 or above
 * postgres
 * rvm
 * ruby version in .ruby-version file under e2e directory
 * npm
 
# Building
 * ./gradlew build
 * cd frontend; npm install -g yarn; yarn
 * cd e2e; gem install bundler; bundle
 
# Running unit tests
 * ./gradlew clean test
 * cd frontend; yarn test
 
# Running E2E tests
 * cd e2e; rspec
 
# Docker
 * docker-compose up
 * If wanting to run commands inside docker container: docker-compose run app bash 
 * If you only want to run postgres: docker run -p 5432:5432 -v ~/code/warehouse_kotlin/pg_data:/var/lib/postgresql/data -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e PGDATA=/var/lib/postgresql/data postgres:9 