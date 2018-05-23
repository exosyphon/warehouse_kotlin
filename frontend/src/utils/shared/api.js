import axios from 'axios';

export const callFetchApi = (...params) => callFetchAxios(...params);

function callFetchAxios(endpoint, params, method, reqbody = {}) {
    switch (method) {
        case 'GET':
            return axios.get(endpoint)
                .then(response => response.data)
                .catch(e => e);
    }
};