import { Link } from "react-router-dom"
import { useState } from "react"
import { getHomeApi } from "api/HomeApi"
import { useAuth } from "components/auth/AuthContext"

function Home() {

    const [ message, setMessage ] = useState(null) 

    const authContext = useAuth()

    const username = authContext.authState.username

    function getHomeContent(){
        getHomeApi()
            .then(response => {
                    setMessage(response.data)
                }
        
            )
            .catch((error) => console.log(error))
            .finally(() => console.log('clean-up'))
    }

    return(
        <div>
            <h3> Welcome {username} </h3>
            <div>
                <Link to="/todos">Management todos</Link>
            </div>
            <div>
                <button className="btn-btn-success" onClick={getHomeContent}>temp</button>
            </div>
            <div>
                {message}
            </div>
        </div>
    )
}

export default Home