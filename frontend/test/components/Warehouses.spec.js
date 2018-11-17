import { mount, createLocalVue, RouterLink } from 'vue-test-utils'
import Warehouses from '../../src/components/warehouses'
import Vuex from 'vuex'
import VueRouter from 'vue-router'
import VueMaterial from 'vue-material'

global.window.document.createRange = function createRange() {
  return {
    setEnd: () => {},
    setStart: () => {},
    getBoundingClientRect: () => {
      return { right: 0 };
    },
    getClientRects: () => [],
    commonAncestorContainer: {
          nodeName: "BODY",
          ownerDocument: document,
        }
  };
};
const { getComputedStyle } = window
window.getComputedStyle = function getComputedStyleStub(el) {
	return {
		...getComputedStyle(el),
		transitionDelay: '',
		transitionDuration: '',
		animationDelay: '',
		animationDuration: '',
	}
}

describe('Warehouses', () => {
  let wrapper

  beforeEach(() => {
    const localVue = createLocalVue()
    localVue.use(Vuex)
    localVue.use(VueRouter)
    localVue.use(VueMaterial)

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

  it('renders the correct markup', (done) => {
    wrapper.find('.show-button').trigger('click')

    wrapper.vm.$nextTick(() => {
      expect(wrapper.find('.modal').html()).toContain("Preferences")
      done()
    })
  })

  it('should update values in a select inside a dialog', (done) => {
    wrapper.find('.show-button').trigger('click')

    wrapper.vm.$nextTick(() => {
      wrapper.find('.md-icon').trigger('click')
      wrapper.vm.$nextTick(() => {
        expect(wrapper.findAll('.option').length).toEqual(3)
        expect(wrapper.findAll('.option').at(0).text()).toEqual('First')
        expect(wrapper.findAll('.option').at(1).text()).toEqual('Second')
        expect(wrapper.findAll('.option').at(2).text()).toEqual('Third')

        wrapper.findAll('.option').at(0).trigger('click')
        wrapper.vm.$nextTick(() => {
          expect(wrapper.vm.$data.selectedValue).toEqual('First')
          done()
        })
      })
    })
  })

  it('should filter previously selected item from select options', (done) => {
    wrapper.vm.$data.lastSelectedValue = 'First'

    wrapper.find('.show-button').trigger('click')
    wrapper.vm.$nextTick(() => {
      wrapper.find('.md-icon').trigger('click')
      wrapper.vm.$nextTick(() => {
        expect(wrapper.findAll('.option').length).toEqual(2)
        expect(wrapper.findAll('.option').at(0).text()).toEqual('Second')
        expect(wrapper.findAll('.option').at(1).text()).toEqual('Third')
        done()
      })
    })
  })
})