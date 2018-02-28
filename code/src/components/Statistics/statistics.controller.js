import {bindActionCreators} from 'redux';
import * as statisticsActions from '../../actions/statisticsActions'

export function mapStateToProps(state) {
    return {
      statistics: state.statistics
    };
  }
  
  export function mapDispatchToProps(dispatch) {
    return {
        statisticsActions: bindActionCreators(statisticsActions, dispatch)
    };
  }