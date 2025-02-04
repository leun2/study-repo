import { ApiClient } from "./ApiClient"

export function getHomeApi() {
    return ApiClient.get('/v1/home')
}
