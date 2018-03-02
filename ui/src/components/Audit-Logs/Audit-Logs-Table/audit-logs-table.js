import React from 'react';
import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow,
    TableRowColumn,
} from 'material-ui/Table';


export default class AuditLogsTable extends React.Component {

    render() {
        const { data } = this.props;

        let content = '';

        if (data == null || data === undefined || data.length === 0) {
            content = (<p>No data</p>)
        } else {
            content = (<Table fixedHeader={true}>
                <TableHeader displaySelectAll={false} adjustForCheckbox={false}>
                    <TableRow>
                        <TableHeaderColumn> Date </TableHeaderColumn>
                        <TableHeaderColumn> System </TableHeaderColumn>
                        <TableHeaderColumn> Type </TableHeaderColumn>
                        <TableHeaderColumn> Username </TableHeaderColumn>
                        <TableHeaderColumn> Message </TableHeaderColumn>
                    </TableRow>
                </TableHeader>
                <TableBody displayRowCheckbox={false}>
                    {data.map(log => {
                        return (<TableRow key={log.id}>
                            <TableRowColumn> {log.date} </TableRowColumn>
                            <TableRowColumn> {log.system} </TableRowColumn>
                            <TableRowColumn> {log.transactionType} </TableRowColumn>
                            <TableRowColumn> {log.username} </TableRowColumn>
                            <TableRowColumn> {log.message} </TableRowColumn>
                        </TableRow>)
                    })}
                </TableBody>
            </Table>)
        }

        return content
    }
}