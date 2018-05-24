class Application
  def initialize(name, start_command, health_check_command)
    @name, @start_command, @health_check_command = name, start_command, health_check_command
  end

  def start
    puts "starting #{name}"
    @pid = Process.spawn(@start_command, pgroup: true)
    until is_healthy?
      sleep 0.5
    end
  end

  def stop
    Process.kill(9, pid)
  end

  private

  attr_reader :name, :start_command, :health_check_command, :pid

  def is_healthy?
    system(health_check_command)
  end
end