import React from 'react';
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import 'styles/Todo.css'

import Header from 'components/layout/Header'
import Footer from 'components/layout/Footer'
import Login from 'components/auth/Login';
import Error from 'components/errors/Error';
import Todos from 'components/todo/Todos';
import Home from 'components/home/home';
import { AuthProvider } from 'components/auth/AuthContext';
import AuthenticatedRoute from 'components/auth/AuthenticatedRoute';
import UpdateTodo from './UpdateTodo';

function Todo() {

    return(
        <div>
            <AuthProvider>
                <BrowserRouter>
                    <Header />
                    <Routes>
                        <Route path='/' element={<Login />} />
                        <Route
                            path="/home"
                            element={
                                <AuthenticatedRoute>
                                    <Home />
                                </AuthenticatedRoute>
                            }
                        />
                        <Route
                            path="/todos"
                            element={
                                <AuthenticatedRoute>
                                    <Todos />
                                </AuthenticatedRoute>
                            }
                        />
                        <Route
                            path="/todo/:id"
                            element={
                                <AuthenticatedRoute>
                                    <UpdateTodo />
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
