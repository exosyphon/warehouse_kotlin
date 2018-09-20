require 'spec_helper'

describe 'Warehouses Page' do
	before :all do
	  Warehouse.delete_all
	  @records = [
		Warehouse.create(name: 'warehouse 1'),
		Warehouse.create(name: 'warehouse 2'),
		Warehouse.create(name: 'bonus warehouse')
	  ]
	end

	after :all do
      @records.each do |record|
        record.destroy!
      end
	end

	it 'should have a list of warehouses' do
		visit '/'

		expect(page).to have_text 'warehouse 1'
		expect(page).to have_text 'warehouse 2'
	end
end
