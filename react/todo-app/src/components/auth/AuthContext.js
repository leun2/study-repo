import { apiClient } from 'api/ApiClient';
import { executeJwtAuthenticationApi } from 'api/AuthenticationApiService';
import React, { createContext, useContext, useState } from 'react';

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext)

function AuthProvider({ children }) {
    const [authState, setAuthState] = useState({
        isAuthenticated: false,
        username: null,
        token: null
    });
/*
    async function login(username, password){

        const basicToken = 'Basic ' + window.btoa(username + ':' + password)

        try {
            const response = await executeBasicAuthenticationApi(basicToken)

            if (response.status === 200) {
                console.log('Logged in with:', { username, password });
                setAuthState({ isAuthenticated: true, username, token: basicToken });
                apiClient.interceptors.request.use(
                    (config) => {
                        config.headers.Authorization = basicToken;
                        return config;
                    },
                    (error) => {
                        return Promise.reject(error);
                    }
                );
                return true; 
            } else {
                console.log('Please enter both username and password');
                return false; 
            }
        }catch (e) {
            console.log(e)
            return false
        }
    };
*/

    async function login(username, password){


        try {
            const response = await executeJwtAuthenticationApi(username, password)
            
            const jwtToken = "Bearer " + response.data.token

            if (response.status === 200) {
                console.log('Logged in with:', { username, password });
                
                setAuthState({ isAuthenticated: true, username, token: jwtToken});
                apiClient.interceptors.request.use(
                    (config) => {
                        config.headers.Authorization = jwtToken;
                        return config;
                    },
                    (error) => {
                        return Promise.reject(error);
                    }
                );
                return true; 
            } else {
                console.log('Please enter both username and password');
                return false; 
            }
        }catch (e) {
            console.log(e)
            return false
        }
    };

    const logout = () => {
        setAuthState({ isAuthenticated: false, username: null, token: null });
    };

    return (
        <AuthContext.Provider value={{ authState, login, logout}}>
            {children}
        </AuthContext.Provider>
    );  
}

export { AuthContext, AuthProvider };