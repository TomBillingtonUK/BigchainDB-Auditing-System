import { combineReducers } from 'redux';
import auditLogsReducer from './auditLogsReducer';
import statisticsReducer from './statisticsReducer';

export const rootReducer = combineReducers({
    auditLogs : auditLogsReducer,
    statistics : statisticsReducer
});