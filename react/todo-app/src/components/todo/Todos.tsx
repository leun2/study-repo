import React, { useState } from "react";

function Todos() {
    const today = new Date()

    const targetDate = new Date(today.getFullYear(), today.getMonth(), today.getDate());

    // const todos = [
    //     { id: 1, description : "Learn AWS", done: false, date: targetDate },
    //     { id: 2, description: "Practice React", done: false, date: targetDate },
    // ]

    const [todos, setTodos] = useState([
        { id: 1, description: "Learn AWS", done: false, date: targetDate },
        { id: 2, description: "Practice React", done: false, date: targetDate },
    ]);

    return (
        <div className="container">
            <table className="table">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>description</th>
                        <th>done</th>
                        <th>date</th>
                    </tr>
                </thead>
                <tbody>
                    {todos.map((todo, index) => (
                        <tr key={index}>
                            <td>{todo.id}</td>
                            <td>{todo.description}</td>
                            <td>{todo.done.toString()}</td>
                            <td>{todo.date.toDateString()}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )

}

export default Todos;