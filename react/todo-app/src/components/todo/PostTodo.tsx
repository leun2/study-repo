import { getUpdateTodoApi, postTodoApi, updateTodoApi } from "api/TodoApi";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "components/auth/AuthContext";
import { Formik,  Form, Field, ErrorMessage  } from "formik";

interface PostTodoDto {
    todoTitle : string;
    todoDescription: string;
    todoDate : string;
}

function PostTodo() {

    const initDate = new Date().toISOString().split("T")[0];

    const [todo, setTodo] = useState<PostTodoDto>({
        todoTitle: "",
        todoDescription: "",
        todoDate: initDate,
    });

    const authContext = useAuth()

    const navigate = useNavigate()

    const username = authContext.authState.username

    function handleSubmit(values: PostTodoDto) {
        console.log("Posting todo...", values);
        postTodoApi(username, values)
            .then(
                () => {
                    navigate(`/todos`)
                }
            )
            .catch(error => console.log(error));
    }

    function validate(values: PostTodoDto) {

        let errors: Partial<PostTodoDto> = {};

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
            <h2>Add Todo</h2>
            <Formik
                initialValues={todo}
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
                            <Field type="text" className="form-control m-2" name="todoTitle" placeholder="Enter Title" />
                            <Field type="text" className="form-control m-2" name="todoDescription" placeholder="Enter Description" />
                            <Field type="date" className="form-control m-2" name="todoDate" />

                            <div>
                                <button type="submit" className="btn btn-success m-5" disabled={isSubmitting}>
                                    Add Todo
                                </button>
                            </div>
                        </fieldset>
                    </Form>
                )}
            </Formik>
        </div>
    )
}

export default PostTodo