import React from 'react';
import Card, { CardText, CardHeader } from 'material-ui/Card';
import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow,
    TableRowColumn,
} from 'material-ui/Table';
import styles from './statistics-card.css';

export default class StatisticsCard extends React.Component {

    render (){

        const {title, data, header1, header2, key1, key2} = this.props

        return(
            <Card className={styles.card}>
                <CardHeader>
                    <h3>{title}</h3>
                </CardHeader>
                <CardText>
                    <Table>
                        <TableHeader displaySelectAll={false} adjustForCheckbox={false}>
                            <TableHeaderColumn>{header1}</TableHeaderColumn>
                            <TableHeaderColumn>{header2}</TableHeaderColumn>    
                        </TableHeader>
                        <TableBody displayRowCheckbox={false}>
                            {data.map(dataRow => {
                                return (
                                    <TableRow>
                                        <TableRowColumn>{dataRow[key1]}</TableRowColumn>
                                        <TableRowColumn>{dataRow[key2]}</TableRowColumn>
                                    </TableRow>
                                )
                            })}
                        </TableBody>
                    </Table>
                </CardText>
            </Card>
        )
    }

}