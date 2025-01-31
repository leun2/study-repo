import { getUpdateTodoApi, updateTodoApi } from "api/TodoApi";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "components/auth/AuthContext";
import { Formik,  Form, Field, ErrorMessage  } from "formik";

interface Todo {
    todoId : number
    todoTitle : string;
    todoDescription: string;
    todoDone : boolean;
    todoDate : Date;
}

function UpdateTodo () {

    const { id } = useParams()

    const [todo, setTodo] = useState<Todo>();

    const authContext = useAuth()

    const navigate = useNavigate()

    const username = authContext.authState.username

    useEffect(() => {
        retrieveTodo();
    }, [id]);

    function retrieveTodo () {
        getUpdateTodoApi(username, Number(id))
            .then(response => {
                console.log(response)
                setTodo(response.data)
            })
            .catch(error => console.log(error))
    }

    function handleSubmit(values: Todo) {
        console.log("Updating todo...", values);
        updateTodoApi(username, values.todoId, values)
            .then(
                () => {
                    navigate(`/todos`)
                }
            )
            .catch(error => console.log(error));
    }

    function validate(values: Todo) {

        let errors: Partial<Todo> = {};

        if (!values.todoTitle) {
            errors.todoTitle = "Title is required.";
        } else if (values.todoTitle.length < 3) {
            errors.todoTitle = "Title must be at least 3 characters long.";
        }

        if (!values.todoDescription) {
            errors.todoDescription = "Description is required.";
        } else if (values.todoDescription.length < 5) {
            errors.todoDescription = "Description must be at least 5 characters long.";
        }

        return errors;
    }

    return (
        <div>
            <h2>Update Todo</h2>
            {todo ? (
                <Formik
                    initialValues = {todo}
                    enableReinitialize = {true}
                    onSubmit={handleSubmit}
                    validate={validate}
                    validateOnChange={false}
                    validateOnBlur={false}
                >
                    {({ isSubmitting }) => (
                        <Form>
                            <ErrorMessage name="todoTitle" component="div" className="alert alert-warning" />
                            <ErrorMessage name="todoDescription" component="div" className="alert alert-warning" />
                            <fieldset className="form-group">
                                <Field type="text" className="form-control m-2" name="todoTitle" />

                                <Field type="text" className="form-control m-2" name="todoDescription" />
                                <label> Done : </label>
                                <Field type="checkbox" name="todoDone" />

                                <Field type="date" className="form-control m-2" name="todoDate" />
                                <div>
                                    <button type="submit" className="btn btn-success m-5" disabled={isSubmitting}>
                                        Update
                                    </button>
                                </div>

                            </fieldset>
                        </Form>
                    )}
                </Formik>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    )
}

export default UpdateTodo