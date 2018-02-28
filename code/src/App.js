import React, { Component } from 'react';
import Routes from './pages/routes'
import AppBar from './components/App-Bar/app-bar';
import NavigationBar from './components/Navigation-Bar/navigation-bar';
import styles from './App.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import lightBaseTheme from 'material-ui/styles/baseThemes/lightBaseTheme';
import getMuiTheme from 'material-ui/styles/getMuiTheme';

class App extends Component {
  render() {
    return (
      <MuiThemeProvider muiTheme={getMuiTheme(lightBaseTheme)}>
        <div className={styles.container}>
          <AppBar />            <NavigationBar />
          <div className={styles.content}>
            <Routes />
          </div>
        </div>
      </MuiThemeProvider>)
  }
}

export default App;
