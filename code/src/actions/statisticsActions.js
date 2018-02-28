import * as types from './actionTypes';
import { baseUrl } from './actions.constants';

export function receiveStatistics(data) {
    return { type: types.RECIEVE_STATISTICS, statistics: data };
}

export function fetchStatistics() {
    return dispatch => {
        return fetch(baseUrl + 'stats',
            {
                method: 'GET',
                headers: {
                    'Accept': 'application/json'
                }
            })
            .then(response =>
                response.json().then(data => ({
                    data: data,
                    status: response.status
                })
                ))
            .then(response => {
                if (response.status === 200) {
                    dispatch(receiveStatistics(response.data))
                }
            })
    }
}