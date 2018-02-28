import React from 'react';
import { connect } from 'react-redux';
import * as controller from './statistics.controller';
import StatisticsHeader from './Statistics-Header/statistics-header';
import StatisticsCard from './Statistics-Card/statistics-card';
import styles from './statistics.css';

class Statistics extends React.Component {

    componentWillMount() {
        this.props.statisticsActions.fetchStatistics();
    }

    render() {
        const { statistics } = this.props;

        return (
            <div className={styles.container}>
                <StatisticsHeader />
                <StatisticsCard
                    title="Logs Per User"
                    data={statistics.userLogs}
                    header1="Username"
                    header2="Amount"
                    key1="username"
                    key2="amount"
                />
                <StatisticsCard
                    title="Logs Per System"
                    data={statistics.systemLogs}
                    header1="System"
                    header2="Amount"
                    key1="system"
                    key2="amount"
                />
                <StatisticsCard
                    title="Failed Logins Per User"
                    data={statistics.failedLogins}
                    header1="Username"
                    header2="Amount"
                    key1="username"
                    key2="amount"
                />
                <StatisticsCard
                    title="Exports Per User"
                    data={statistics.userExports}
                    header1="Username"
                    header2="Amount"
                    key1="username"
                    key2="amount"
                />
            </div>
        )
    }
}

export default connect(
    controller.mapStateToProps,
    controller.mapDispatchToProps
)(Statistics);