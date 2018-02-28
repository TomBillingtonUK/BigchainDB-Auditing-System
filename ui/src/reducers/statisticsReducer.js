import initialState from './initialState';
import { FETCH_STATISTICS, RECIEVE_STATISTICS } from '../actions/actionTypes';

export default function StatisticsReducer(state = initialState.statistics, action) {
    let newState;
    switch (action.type) {
        case FETCH_STATISTICS:
            return action;
        case RECIEVE_STATISTICS:
            newState = action.statistics;
            return newState;
        default:
            return state;
    }
}