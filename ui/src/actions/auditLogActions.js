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
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(
                    {
                        timeStamp_start: filterCriteria.timeStamp_start,
                        timeStamp_end: filterCriteria.timeStamp_end,
                        system: filterCriteria.system,
                        userName: filterCriteria.userName,
                        transactionType: filterCriteria.transactionType
                    }
                )
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