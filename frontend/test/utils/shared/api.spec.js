import {callFetchApi} from '../../../src/utils/shared/api'
import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

let mock = new MockAdapter(axios)

describe('callFetchApi', () => {
    describe('GET', () => {
        it('should return response data on success', (done) => {
            mock.onGet('/smelly-cat').reply(200, {
                something: [
                    { id: 1, name: 'Mexican keyboard cat' },
                    { id: 2, name: 'Will it blend?' }
                ]
            })
          
           callFetchApi('/smelly-cat', {}, 'GET').then((result) => {
               expect(result).toEqual({
                something: [
                    { id: 1, name: 'Mexican keyboard cat' },
                    { id: 2, name: 'Will it blend?' }
                  ]
               })
               done()
           })
        })

        it('should return error on error', (done) => {
            mock.onGet('/smelly-cat').reply(401, {
                something: 'went wrong'
            })
          
           callFetchApi('/smelly-cat', {}, 'GET').then((result) => {
               expect(result.message).toEqual('Request failed with status code 401')
               done()
           })
        })
    })
})