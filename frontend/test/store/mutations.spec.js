import mutations from '../../src/store/mutations'

describe('Mutations', () => {
    describe('mutations', () => {
        describe('FETCH_WAREHOUSES_SUCCESS', () => {
            it('should set warehouses', () => {
                const state = {}
                mutations.FETCH_WAREHOUSES_SUCCESS(state, [{id: 1, name: 'bob'}])
                expect(state.warehouses).toEqual([{id: 1, name: 'bob'}])
            })
        })
    })
})