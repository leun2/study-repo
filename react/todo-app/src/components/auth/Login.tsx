import React, { useState } from 'react';
import {useNavigate} from 'react-router-dom'
import { useAuth } from "components/auth/AuthContext";
import 'styles/Todo.css'


function Login() {

    const [username, setUserName] = useState('')

    const [password, setUserPassword] = useState('')

    const authContext = useAuth()

    const navigate = useNavigate();

    function handleUserName(e : React.ChangeEvent<HTMLInputElement>) {
        setUserName(e.target.value)
    }

    function handleUserPassword(e : React.ChangeEvent<HTMLInputElement>) {
        setUserPassword(e.target.value)

    }

    async function handleLoginFormSubmit() {
        if (await authContext.login(username, password)) {
            navigate(`/home`)
        } else {
            console.log('Please enter both username and password');
        }
    }

    return (
        <div>
            <div>
                <div>
                    <label>
                        username
                    </label>
                    <input 
                        type="text" 
                        name="username" 
                        value={username} 
                        onChange={handleUserName}/>
                </div>
                <div>
                    <label>
                        password
                    </label>
                    <input 
                        type="password" 
                        name="password" 
                        value={password} 
                        onChange={handleUserPassword}/>
                </div>
                <div>
                    <button 
                        type="button" 
                        name="loginButton"
                        onClick={handleLoginFormSubmit}>
                            login
                    </button>
                </div>
            </div>
        </div>
    )
}

export default Login;