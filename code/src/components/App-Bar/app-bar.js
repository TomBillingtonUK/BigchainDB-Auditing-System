import React from 'react'
import styles from './app-bar.css';

export default class AppBar  extends React.Component{

render () {

    return (
        <div className={styles.container}>
            <h1 className={styles.title}> Auditing Application</h1>
        </div>
    )

}

}