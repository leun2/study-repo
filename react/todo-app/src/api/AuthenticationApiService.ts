import { apiClient } from "./ApiClient"

export const executeBasicAuthenticationApi =
    (token : string) => 
        apiClient.get(`v1/token`,{
            headers: {
                Authorization: token
            }
        })

export const executeJwtAuthenticationApi = 
    (username: string, password: string) =>
        apiClient.post('/authenticate', {
            username, 
            password
        });