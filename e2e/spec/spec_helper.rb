require 'capybara/rspec'
require 'active_record'

Dir[File.expand_path('./support/**/*.rb', __dir__)].each {|f| require f}

RSpec.configure do |config|
  config.expect_with :rspec do |expectations|
    expectations.include_chain_clauses_in_custom_matcher_descriptions = true
  end

  config.mock_with :rspec do |mocks|
    mocks.verify_partial_doubles = true
  end

  config.include Capybara::DSL
end

Capybara.configure do |config|
  config.run_server = false
	config.default_driver = :selenium_chrome
  config.app_host = 'http://localhost:8081'
end
