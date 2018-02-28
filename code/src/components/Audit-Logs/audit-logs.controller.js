import {bindActionCreators} from 'redux';
import * as auditLogsActions from '../../actions/auditLogActions'

export function mapStateToProps(state) {
    return {
      data: state.auditLogs
    };
  }
  
  export function mapDispatchToProps(dispatch) {
    return {
        auditLogsActions: bindActionCreators(auditLogsActions, dispatch)
    };
  }