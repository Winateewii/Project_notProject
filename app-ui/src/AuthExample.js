import React from 'react'
import {
  BrowserRouter as Router,
  Route,
  Link,
  Redirect,
  withRouter
} from 'react-router-dom'
import App from './App'
import Login from './Login'
import PrivateRoute from './PrivateRoute.js'
import { createBrowserHistory } from 'history';
export const history = createBrowserHistory();


class Dashboard extends React.Component {
  render() {
    return (
      <div>
        ...Loading
      </div>
    )
  }
}

export default function router () {
  return (
    <Router >
      <div>
        <Route path="/login" component={Login}/>
        <PrivateRoute exact path='/' component={App} />
      </div>
    </Router>
  )
}