import { callFetchApi } from "../utils/shared/api";
import * as constants from '../utils/shared/constants';

export default {
    FETCH_WAREHOUSES (context) {
        return callFetchApi(constants.url, {}, 'GET')
            .then((response) => context.commit('FETCH_WAREHOUSES_SUCCESS', response))
            .catch((error) => context.commit('API_FAILURE', error));
    }
}