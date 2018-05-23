import { shallow, createLocalVue } from 'vue-test-utils'
import Warehouses from '../../src/components/warehouses'
import Vuex from 'vuex'

describe('Warehouses', () => {
  let wrapper

  beforeEach(() => {
    const localVue = createLocalVue()
    localVue.use(Vuex)
    
    const state = {
      warehouses: [{id: 1, name: 'tom'}]
    }
    const actions = {
      FETCH_WAREHOUSES: jest.fn(),
    }
    const getters = {
      getWarehouses: state => state.warehouses
    }
    const store = new Vuex.Store({state, actions, getters})

    wrapper = shallow(Warehouses, {store, localVue})
  })

  it('renders the correct markup', () => {
    expect(wrapper.find('.warehouse').html()).toContain("tom")
  })
})