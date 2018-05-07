require_relative 'spec_helper'

describe 'Warehouses Page' do
	before :all do
		ActiveRecord::Base.establish_connection('postgresql://localhost:5432/warehouses')
		class Warehouse < ActiveRecord::Base
			self.table_name = 'warehouse'
		end

		Warehouse.create(name: 'warehouse 1')
		Warehouse.create(name: 'warehouse 2')
		Warehouse.create(name: 'bonus warehouse')
	end

	after :all do
		Warehouse.destroy_all
	end

	it 'should have a list of warehouses' do
		visit '/'

		expect(page).to have_text 'warehouse 1'
		expect(page).to have_text 'warehouse 2'
	end
end
