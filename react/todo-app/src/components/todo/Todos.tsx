import React, { useEffect, useState, useCallback } from "react";
import { retrieveAllTodosForUserNameApi, deleteTodoApi } from "api/TodoApi";
import { useAuth } from "components/auth/AuthContext";
import { useNavigate } from "react-router-dom";

interface TodoDto {
    todoId : number;
    todoTitle : string;
    todoDescription: string;
    todoDone : boolean;
    todoDate : string;
}

function Todos() {

    const authContext = useAuth()

    const username = authContext.authState.username

    const navigate = useNavigate()
    
    const [todos, setTodos] = useState<TodoDto[]>([]);

    const [message, setMessage] = useState<String | null>(null);

    const getAllTodos = useCallback(() => {
        retrieveAllTodosForUserNameApi(username)
            .then(response => {
                setTodos(response.data);
            })
            .catch((error) => errorResponse(error));
    }, [username]);

    useEffect(() => 
        getAllTodos()
    , [getAllTodos]);

    function deleteTodo(todoId : number) {
        deleteTodoApi(username, todoId)
        .then(
            () => {
                setMessage(`delete todo ${todoId}`)
                getAllTodos()
                setTimeout(() => setMessage(null), 3000);
            }
        )
        .catch((error) => errorResponse(error))
    }

    function updateTodo(todoId : number) {
        navigate(`/todo/${todoId}`)
    }

    function postTodo() {
        navigate(`/todo/post`)
    }

    function errorResponse(error:any) {
        console.log(error)
    }

    return (
        <div className="container">
            {message && <div className="alert alert-warning">{message}</div>}
            <table className="table">
                <thead>
                    <tr><th>id</th>
                        <th>title</th>
                        <th>description</th>
                        <th>done</th>
                        <th>date</th>
                        <th>delete</th>
                        <th>update</th>
                    </tr>
                </thead>
                <tbody>
                    {todos.map((todo, index) => (
                        <tr key={index}>
                            <td>{todo.todoId}</td>
                            <td>{todo.todoTitle}</td>
                            <td>{todo.todoDescription}</td>
                            <td>{todo.todoDone ? 'true' : 'false'}</td>
                            <td>{todo.todoDate}</td>
                            <td>
                                <button className="btn btn-warning" 
                                        onClick={() => deleteTodo(todo.todoId)}>
                                            delete
                                </button>
                            </td>
                            <td>
                                <button className="btn btn-success"
                                        onClick={() => updateTodo(todo.todoId)}>
                                            update
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div>
                <button className="btn btn-success"
                        onClick={() => postTodo()}>
                            add-todo
                </button>
            </div>
        </div>
    )

}

export default Todos;