require 'spec_helper'

describe 'Warehouses Page' do
	before :all do
		Warehouse.create(name: 'warehouse 1')
		Warehouse.create(name: 'warehouse 2')
		Warehouse.create(name: 'bonus warehouse')
	end

	it 'should have a list of warehouses' do
		visit '/'

		expect(page).to have_text 'warehouse 1'
		expect(page).to have_text 'warehouse 2'
	end
end
