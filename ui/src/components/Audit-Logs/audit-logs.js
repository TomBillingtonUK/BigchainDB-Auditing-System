import React from 'react';
import styles from './audit-logs.css'
import Card, { CardText, CardHeader } from 'material-ui/Card';
import AuditLogsTable from './Audit-Logs-Table/audit-logs-table';
import AuditLogsFilter from './Audit-Logs-Filter/audit-logs-filter';
import * as controller from './audit-logs.controller';
import { connect } from 'react-redux';
import {InitiaFilterCriteria} from './audit-logs.constants';

class AuditLogs extends React.Component {

    constructor (props)
    {
        super(props)

        this.state = {
            filter: InitiaFilterCriteria
        }

        this.updateFilter = this.updateFilter.bind(this);
        this.getAuditLogs = this.getAuditLogs.bind(this);
    }

    componentWillMount() {
       this.getAuditLogs();
    }

    getAuditLogs()
    {
        this.props.auditLogsActions.fetchAuditLogs(this.state.filter);
    }

    updateFilter(property, value) {
        let newFilter = Object.assign({} , this.state.filter , { [property] : value})

        this.setState({filter: newFilter})
    }

    render() {

        const { data } = this.props;

        return (
            <div className={styles.container}>
                <Card className={styles.tableCard}>
                    <CardHeader>
                        <h2>Audit Logs</h2>
                    </CardHeader>
                    <CardText>
                        <AuditLogsTable data={data} />
                    </CardText>
                </Card>
                <Card className={styles.filterCard}>
                    <CardHeader>
                        <h2>Filter</h2>
                    </CardHeader>
                    <CardText>
                        <AuditLogsFilter 
                            updateFilter={this.updateFilter} 
                            filterData={this.state.filter}
                            filterButtonOnClick={this.getAuditLogs}
                        />
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