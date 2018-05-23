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
 * ./gradlew test
 * cd frontend; yarn test
 
# Running E2E tests
 * ./gradlew bootrun
 * cd frontend; yarn serve
 * cd e2e; rspec