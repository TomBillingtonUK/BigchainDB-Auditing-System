import React from 'react';
import DatePicker from 'material-ui/DatePicker';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import RaisedButton from 'material-ui/RaisedButton';
import styles from './audit-logs-filter.css'

export default class AuditLogFiter extends React.Component {

    constructor(props) {
        super(props);

        this.updateDateFrom = this.updateDateFrom.bind(this);
        this.updateDateTo = this.updateDateTo.bind(this);
        this.updateSystem = this.updateSystem.bind(this);
        this.updateUsername = this.updateUsername.bind(this);
        this.updateType = this.updateType.bind(this);
    }

    updateDateFrom(event, data) {
        this.props.updateFilter("timestamp_start", data);
    }

    updateDateTo(event, data) {
        this.props.updateFilter("timestamp_end", data);
    }

    updateSystem(event, data) {
        this.props.updateFilter("system", data);
    }

    updateUsername(event, data) {
        this.props.updateFilter("username", data);
    }

    updateType(event, data) {
        this.props.updateFilter("transactionType", data);
    }

    render() {
        const {
            filterData,
            filterButtonOnClick,
        } = this.props;

        return (
            <div>
                <p> Date From : </p>
                <DatePicker
                    onChange={this.updateDateFrom}
                    value={filterData.timestamp_start}
                />
                <p> Date To : </p>
                <DatePicker
                    onChange={this.updateDateTo}
                    value={filterData.timestamp_end}
                />
                <p> System : </p>
                <TextField
                    onChange={this.updateSystem}
                    value={filterData.system}
                />
                <p> Username : </p>
                <TextField
                    onChange={this.updateUsername}
                    value={filterData.username}
                />
                <p> Type : </p>
                <SelectField
                    onChange={this.updateType}
                    value={filterData.transactionType}
                >
                    <MenuItem value="CREATE" primaryText="Create" />
                    <MenuItem value="UPDATE" primaryText="Update" />
                    <MenuItem value="DELETE" primaryText="Delete" />
                    <MenuItem value="READ" primaryText="Read" />
                    <MenuItem value="LOGIN" primaryText="Login" />
                    <MenuItem value="EXPORT" primaryText="Export" />
                </SelectField>
                <RaisedButton 
                    label="Filter"
                    onClick={filterButtonOnClick} 
                />
            </div>
        )
    }
}
