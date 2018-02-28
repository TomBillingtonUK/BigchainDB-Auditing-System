import React from 'react';
import { Switch, Route } from 'react-router-dom';
import Dashboard from './dashboard/dashboard';
import Logs from './logs/logs';
import Settings from './settings/settings';


export default class Routes extends React.Component {

    render() {

        return (
            <Switch>
                <Route exact path='/' component={Dashboard} />
                <Route path='/logs' component={Logs} />
                <Route path='/settings' component={Settings} />
            </Switch>
        )
    }
}