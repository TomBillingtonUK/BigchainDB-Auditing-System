import React from 'react';
import { Switch, Route } from 'react-router-dom';
import Dashboard from './dashboard/dashboard';
import Logs from './logs/logs';


export default class Routes extends React.Component {

    render() {

        return (
            <Switch>
                <Route exact path='/' component={Dashboard} />
                <Route path='/logs' component={Logs} />
            </Switch>
        )
    }
}