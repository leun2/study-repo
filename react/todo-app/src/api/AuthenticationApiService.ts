import { ApiClient } from "./ApiClient"

export const executeBasicAuthenticationApi =
    (token : string) => 
        ApiClient.get(`v1/token`,{
            headers: {
                Authorization: token
            }
        })

export const executeJwtAuthenticationApi = 
    (username: string, password: string) =>
        ApiClient.post('/authenticate', {
            username, 
            password
        });