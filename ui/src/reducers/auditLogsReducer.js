import initialState from './initialState';
import { FETCH_AUDIT_LOGS, RECIEVE_AUDIT_LOGS } from '../actions/actionTypes';

export default function AuditLogsReducer(state = initialState.auditLogs, action) {
    let newState;
    switch (action.type) {
        case FETCH_AUDIT_LOGS:
            return action;
        case RECIEVE_AUDIT_LOGS:
            newState = action.auditLogs;
            return newState;
        default:
            return state;
    }
}