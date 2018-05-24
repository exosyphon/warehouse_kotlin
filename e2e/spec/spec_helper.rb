require 'capybara/rspec'
require 'active_record'
require 'yaml'
require 'database_cleaner'

file_path = File.join(File.expand_path("..", File.expand_path(File.dirname(File.dirname(__FILE__)))), "src/main/resources/application-local.yml")
contents = YAML.load_file(file_path)

ActiveRecord::Base.establish_connection(contents['spring']['datasource']['url'].sub! 'jdbc:', '')
backend_port = contents['server']['port']
frontend_url = contents['frontend']['url']

Dir[File.expand_path('./support/**/*.rb', __dir__)].each {|f| require f}

RSpec.configure do |config|
  config.expect_with :rspec do |expectations|
    expectations.include_chain_clauses_in_custom_matcher_descriptions = true
  end

  config.mock_with :rspec do |mocks|
    mocks.verify_partial_doubles = true
  end

  config.include Capybara::DSL

  config.before(:suite) do
    $backend = Application.new('backend', 'cd ../; SPRING_PROFILES_ACTIVE=local ./gradlew bootrun 1>/dev/null', "curl http://localhost:#{backend_port}/actuator/health")
    $frontend = Application.new('frontend', 'cd ../frontend; yarn serveE2E 1>/dev/null', "curl #{frontend_url}")
    $backend.start
    $frontend.start
  end

  config.after(:suite) do
    DatabaseCleaner.strategy = :truncation, {except: %w[flyway_schema_history]}
    DatabaseCleaner.clean_with(:truncation, {except: %w[flyway_schema_history]})
    DatabaseCleaner.clean

    $backend.stop
    $frontend.stop
  end
end

Capybara.configure do |config|
  config.run_server = false
	config.default_driver = :selenium_chrome
  config.app_host = frontend_url
end
