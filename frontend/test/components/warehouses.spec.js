import { mount, createLocalVue, RouterLink } from 'vue-test-utils'
import Warehouses from '../../src/components/warehouses'
import Vuex from 'vuex'
import VueRouter from 'vue-router'

describe('Warehouses', () => {
  let wrapper

  beforeEach(() => {
    const localVue = createLocalVue()
    localVue.use(Vuex)
    localVue.use(VueRouter)

    const state = {
      warehouses: [{id: 1, name: 'tom'}]
    }
    const actions = {
      FETCH_WAREHOUSES: jest.fn(),
    }
    const getters = {
      getWarehouses: state => state.warehouses
    }

    const router = new VueRouter();
    const store = new Vuex.Store({state, actions, getters})

    wrapper = mount(Warehouses, {store, localVue, router})
  })

  it('renders the correct markup', () => {
    expect(wrapper.find('.warehouse').html()).toContain("tom")
  })

  it('should navigate to path in router-link when clicked', () => {
    expect(wrapper.vm.$route.path).toEqual("/")

    wrapper.find('.other-warehouses').trigger('click')

    expect(wrapper.vm.$route.path).toEqual("/other-warehouses")
  })
})