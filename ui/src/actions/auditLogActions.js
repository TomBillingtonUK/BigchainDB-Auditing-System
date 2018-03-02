import * as types from './actionTypes';
import { baseUrl } from './actions.constants';

export function receiveAuditLogs(data) {
    return { type: types.RECIEVE_AUDIT_LOGS, auditLogs: data };
}

export function fetchAuditLogs(filterCriteria) {
    return dispatch => {
        return fetch(baseUrl + 'get',
            {
                method: 'POST',
                headers: {
                    'Accept': 'application/json'
                },
                body : filterCriteria
            })
            .then(response =>
                response.json().then(data => ({
                    data: data,
                    status: response.status
                })
                ))
            .then(response => {
                if (response.status === 200) {
                    dispatch(receiveAuditLogs(response.data))
                }
            })
    }
}