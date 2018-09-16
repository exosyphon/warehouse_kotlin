require 'capybara/rspec'
require 'active_record'
require 'yaml'
require 'selenium-webdriver'

file_path = File.join(File.expand_path("..", File.expand_path(File.dirname(File.dirname(__FILE__)))), "app/src/main/resources/application-e2e.yml")
contents = YAML.load_file(file_path)

ActiveRecord::Base.establish_connection(contents['ruby']['datasource']['url'])
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
    Capybara.current_session.current_window.resize_to(2_500, 2_500)
    $backend = Application.new('backend', 'cd ../; SPRING_PROFILES_ACTIVE=e2e ./gradlew bootrun 1>/dev/null', "curl http://localhost:#{backend_port}/actuator/health")
    $frontend = Application.new('frontend', 'cd ../frontend; yarn serveE2E 1>/dev/null', "curl #{frontend_url}")
    $backend.start
    $frontend.start
  end

  config.after(:suite) do
    $backend.stop
    $frontend.stop
  end
end

Capybara.register_driver :chrome do |app|
  Capybara::Selenium::Driver.new(app,
    :browser => :chrome,
    :desired_capabilities => Selenium::WebDriver::Remote::Capabilities.chrome(
      'chromeOptions' => {
        'args' => [ "--no-sandbox", "--disable-dev-shm-usage", "--headless" ]
      }
    )
  )
end

Capybara.configure do |config|
  config.run_server = false
	config.default_driver = :chrome
  config.app_host = frontend_url
end
