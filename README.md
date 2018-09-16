# Prerequisites
 * git
 * jdk 8.171 or above
 * postgres 9.6+ (or use docker image)
 * rvm (or use docker image)
 * ruby version in .ruby-version file under e2e directory (or use docker image)
 * npm (or use docker image)
 
# Building
 * ./gradlew build
 * cd frontend; npm install -g yarn; yarn
 * cd e2e; gem install bundler; bundle
 
# Running unit tests
 * ./gradlew clean test
 * cd frontend; yarn test
 
# Running E2E tests
 * cd e2e; bundle exec rspec
 
# Docker
 * Build containers: docker-compose build
 * Run Postgres and app containers: docker-compose up
   - This runs `run.sh` script
 * If wanting to run commands inside docker container: docker-compose run -p 8080:8080 -p 8081:8081 app bash 
 * If you want to connect to the running docker container: docker exec -it 5ff9aad15a6b bash
 * If you only want to run postgres: docker run -p 5432:5432 -v ~/code/warehouse_kotlin/pg_data:/var/lib/postgresql/data -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e PGDATA=/var/lib/postgresql/data postgres:9
   - Must change application.yml files to use `localhost:5432` instead of `postgres:5432` if running tests outside container
 * To cleanup extra images and containers:
   - docker rm $(docker ps -aq)
   - docker rmi $(docker images -q)
