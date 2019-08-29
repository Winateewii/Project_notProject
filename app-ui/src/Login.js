import React from 'react'
import { Button, Col , Row, Form} from 'react-bootstrap';
import {
    BrowserRouter as Router,
    Route,
    Link,
    Redirect,
    withRouter
  } from 'react-router-dom'

class Login extends React.Component {
    constructor(props){ 
        super(props);
        this.state = {
            username: '',
            password: ''
        }
        if (localStorage.getItem('currentUser')){
            this.props.history.push('/');
        }
    
    }
    
    submit = (event) => {
        localStorage.setItem('currentUser',"dada");
        this.props.history.push('/');
    }

    render(){
        return ( 
                <Form className='App' >
                    <Row>
                        <Col>
                        <Form.Group>
                        <Col xs = {4}>
                            <Form.Label>Username</Form.Label>
                            <Form.Control type="text" placeholder="Enter username" value={this.state.username}
                            onChange={((event) => this.setState({username:event.target.value}))}/>
                        </Col>
                        </Form.Group>
                        <Form.Group>
                        <Col xs = {4}>
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" placeholder="Enter password" value={this.state.password}
                            onChange={((event) => this.setState({password:event.target.value}))}/>
                        </Col>
                        </Form.Group>
                        <Col xs = {2}>
                            <Button variant="danger" type="button" onClick={this.submit}>Submit</Button>
                        </Col>
                        </Col>
                    </Row>
                </Form>
        );
    }
}
export default Login;