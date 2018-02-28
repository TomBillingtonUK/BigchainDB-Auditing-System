import React from 'react';
import Card, { CardText, CardHeader } from 'material-ui/Card';
import styles from './statistics-header.css';

export default class StatisticsCard extends React.Component {

    render (){

        const {totalLogs} = this.props;

        return(
            <Card className={styles.card}>
                <CardHeader>
                    <h2>Auditing Dashboard</h2>
                </CardHeader>
                <CardText>
                </CardText>
            </Card>
        )
    }

}