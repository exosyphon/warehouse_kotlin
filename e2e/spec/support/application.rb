class Application
  def initialize(name, health_check_command)
    @name = name
    @health_check_command = health_check_command
  end

  def start
    puts "starting health check for #{name}"
    until is_healthy?
      sleep 0.5
    end
  end

  private

  attr_reader :name, :health_check_command

  def is_healthy?
    system(health_check_command)
  end
end