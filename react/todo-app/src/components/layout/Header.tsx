import React, { useContext } from "react";
import {Link} from 'react-router-dom'
import { useAuth } from "components/auth/AuthContext";

function Header() {

    const authContext = useAuth()

    function logout() {
        authContext.logout()
    }

    return (
        <div className="header">
            <header className="border-bottom border-light border-5 mb-5 p-2">
                <div className="container">
                    <div className="row">
                        <nav className="navbar navbar-expand-lg">
                            <div className="collapse navbar-collapse">
                                <ul className="navbar-nav">
                                    {authContext.authState.isAuthenticated && (
                                        <li className="nav-item fs-5">
                                            <Link className="nav-link" to="/home">
                                                Home
                                            </Link>
                                        </li>
                                    )}
                                </ul>
                            </div>
                            <ul className="navbar-nav">
                                {!authContext.authState.isAuthenticated ? (
                                    <li className="nav-item fs-5">
                                        <Link className="nav-link" to="/">
                                            Login
                                        </Link>
                                    </li>
                                ) : (
                                    <li className="nav-item fs-5">
                                        <Link className="nav-link" to="/" onClick={logout}>
                                            Logout
                                        </Link>
                                    </li>
                                )}
                            </ul>
                        </nav>
                    </div>
                </div>
            </header>
        </div>
    );
}

export default Header;
