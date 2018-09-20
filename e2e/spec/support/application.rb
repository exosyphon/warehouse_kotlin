class Application
  def initialize(name, start_command, health_check_command)
    @name, @start_command, @health_check_command = name, start_command, health_check_command
  end

  def start
    puts "starting health check for #{name}"
    until is_healthy?
      sleep 0.5
    end
  end

  def stop
  end

  private

  attr_reader :name, :start_command, :health_check_command, :pid

  def is_healthy?
    system(health_check_command)
  end
end