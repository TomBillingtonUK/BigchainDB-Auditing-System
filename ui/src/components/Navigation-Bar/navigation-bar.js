import React from 'react'
import styles from './navigation-bar.css'
import { Link } from 'react-router-dom'
import IconButton from 'material-ui/IconButton';
import HomeIcon from 'material-ui/svg-icons/action/home';
import LogsIcon from 'material-ui/svg-icons/action/reorder';
import SettingsIcon from 'material-ui/svg-icons/action/settings';

export default class NavigationBar extends React.Component {

    render() {
        return (
            <nav className={styles.container}>
                <ul className={styles.navigation}>
                    <li>
                        <Link to='/'>
                            <IconButton>
                                <HomeIcon color='#fff'/>
                            </IconButton>
                        </Link>
                    </li>
                    <li>
                        <Link to='/logs'>
                            <IconButton>
                                <LogsIcon color='#fff'/>
                            </IconButton>
                        </Link>
                    </li>
                    <li>
                        <Link to='/settings'>
                            <IconButton>
                                <SettingsIcon color='#fff'/>
                            </IconButton>
                        </Link>
                    </li>
                </ul>
            </nav>
        )
    }
}