import { shallow } from 'vue-test-utils'
import App from '../../src/App'

describe('App', () => {
  const wrapper = shallow(App);
  
  it('renders the correct markup', () => {
    expect(wrapper.html()).toContain('<div id=\"app\"><h1>Warehouses</h1> <!----></div>')
  });
});