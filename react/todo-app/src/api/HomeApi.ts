import axios from "axios"

const apiClient = axios.create (
    {
        baseURL: 'http://localhost:8080'
    }
) 

export function getHomeApi() {
    return apiClient.get('/v1/home')
}
