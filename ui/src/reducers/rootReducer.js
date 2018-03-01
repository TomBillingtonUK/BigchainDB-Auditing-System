import { combineReducers } from 'redux';
import AuditLogsReducer from './auditLogsReducer';
import StatisticsReducer from './statisticsReducer';

export const rootReducer = combineReducers({
    auditLogs : AuditLogsReducer,
    statistics : StatisticsReducer
});