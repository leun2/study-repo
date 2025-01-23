import React from 'react';
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import 'styles/Todo.css'

import Header from 'components/layout/Header'
import Footer from 'components/layout/Footer'
import Login from 'components/auth/Login';
import Error from 'components/errors/Error';
import Todos from 'components/todo/Todos';
import { AuthProvider } from 'components/auth/AuthContext';
import AuthenticatedRoute from 'components/auth/AuthenticatedRoute';


function Todo() {

    return(
        <div>
            <AuthProvider>
                <BrowserRouter>
                    <Header />
                    <Routes>
                        <Route path='/' element={<Login />} />
                        <Route
                            path="/todos"
                            element={
                                <AuthenticatedRoute>
                                    <Todos />
                                </AuthenticatedRoute>
                            }
                        />
                        <Route path='*' element={<Error />} />
                    </Routes>
                    <Footer />
                </BrowserRouter>
            </AuthProvider>
        </div>
    )
}

export default Todo;
