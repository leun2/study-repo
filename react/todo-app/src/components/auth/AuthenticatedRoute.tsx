import React from "react";
import { useAuth } from "components/auth/AuthContext";
import { Navigate } from "react-router-dom";

function AuthenticatedRoute({ children }: { children: React.ReactNode }) {
    const { authState } = useAuth();
    return authState.isAuthenticated ? <>{children}</> : <Navigate to="/" />;
}

export default AuthenticatedRoute;