import axios from "axios"

const apiClient = axios.create (
    {
        baseURL: 'http://localhost:8080'
    }
) 

interface Todo {
    todoId : number
    todoTitle : string;
    todoDescription: string;
    todoDone : boolean;
    todoDate : Date;
}

export const retrieveAllTodosForUserNameApi = 
    (user_name : String ) => apiClient.get(`/v1/user/${user_name}/todo`)

export const deleteTodoApi =
    (user_name : String, todo_id : number) => apiClient.delete(`/v1/user/${user_name}/todo/${todo_id}`)

export const getUpdateTodoApi = 
    (user_name : String, todo_id : number) => apiClient.get(`/v1/user/${user_name}/todo/${todo_id}/update`)

export const updateTodoApi =
    (user_name: string, todo_id: number, values: Todo) => 
        apiClient.patch(
            `/v1/user/${user_name}/todo/${todo_id}/update`,
            values, 
            { headers: { "Content-Type": "application/json" } }
        );