import React from 'react';
import styles from './audit-logs.css'
import Card, { CardText, CardHeader } from 'material-ui/Card';
import AuditLogsTable from './Audit-Logs-Table/audit-logs-table';
import AuditLogsFilter from './Audit-Logs-Filter/audit-logs-filter';
import * as controller from './audit-logs.controller';
import { connect } from 'react-redux';

class AuditLogs extends React.Component {

    componentWillMount() { 
        this.props.auditLogsActions.fetchAuditLogs();
    }

    render() {

        const {data} = this.props;

        return (
            <div className={styles.container}>
                <Card className={styles.tableCard}>
                    <CardHeader>
                        <h2>Audit Logs</h2>
                    </CardHeader>
                    <CardText>
                        <AuditLogsTable data={data}  />
                    </CardText>
                </Card>
                <Card className={styles.filterCard}>
                    <CardHeader>
                        <h2>Filter</h2>
                    </CardHeader>
                    <CardText>
                        <AuditLogsFilter/>
                    </CardText>
                </Card>
            </div>
        )
    }
}

export default connect(
    controller.mapStateToProps,
    controller.mapDispatchToProps
)(AuditLogs);