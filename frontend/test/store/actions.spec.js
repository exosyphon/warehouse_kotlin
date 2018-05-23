jest.mock('../../src/utils/shared/api')
import actions from '../../src/store/actions'
import {callFetchApi} from '../../src/utils/shared/api'

describe('Actions', () => {
    describe('FETCH_WAREHOUSES', () => {
        it('should call callFetchApi', (done) => {
            const commit = jest.fn()
            const context = {
                commit
            }
            
            callFetchApi.mockImplementation(calledWith => Promise.resolve())
            actions.FETCH_WAREHOUSES(context).then(() => {
                expect(callFetchApi).toHaveBeenCalledWith('http://localhost:8080', {}, 'GET')
                done()
            })
        })

        it('should update warehouses on success', (done) => {
            const commit = jest.fn()
            const context = {
                commit
            }

            callFetchApi.mockImplementation(calledWith => Promise.resolve([{id: 1, name: 'bob'}]))
            actions.FETCH_WAREHOUSES(context).then(() => {
                expect(commit).toHaveBeenCalledWith('FETCH_WAREHOUSES_SUCCESS', [{id: 1, name: 'bob'}])
                done()
            })  
        })

        it('should report failure on failure', (done) => {
            const commit = jest.fn()
            const context = {
                commit
            }

            callFetchApi.mockImplementation(calledWith => Promise.reject())
            actions.FETCH_WAREHOUSES(context).then(() => {
                expect(commit).toHaveBeenCalledWith('API_FAILURE', undefined)
                done()
            })
        })
    })
})