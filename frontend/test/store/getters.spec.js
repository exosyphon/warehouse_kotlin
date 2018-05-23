import getters from '../../src/store/getters'

describe('Getters', () => {
    describe('getters', () => {
        describe('getWarehouses', () => {
            it('should return current warehouses state', () => {
                const state = {warehouses: [{id: 1, name: 'bob'}]}
                expect(getters.getWarehouses(state)).toEqual([{id: 1, name: 'bob'}])
            })
        })
    })
})