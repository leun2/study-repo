import React, { createContext, useContext, useState } from 'react';

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext)

function AuthProvider({ children }) {
    const [authState, setAuthState] = useState({
        isAuthenticated: false,
        username: null
    });

    // const login = async (username: string, password: string): Promise<boolean> => {
    //     if (username === 'lee' && password === '1234') {
    //         setAuthState({ isAuthenticated: true, username });
    //         return true;
    //     }
    //     return false;
    // };

    const login = (username, password) => {
        if (username === 'lee' && password === '1234') {
            console.log('Logged in with:', { username, password });
            setAuthState({ isAuthenticated: true, username });
            return true; 
        } else {
            console.log('Please enter both username and password');
            return false; 
        }
    };

    const logout = () => {
        setAuthState({ isAuthenticated: false, username: null });
    };

    return (
        <AuthContext.Provider value={{ authState, login, logout }}>
            {children}
        </AuthContext.Provider>
    );  
}

export { AuthContext, AuthProvider };