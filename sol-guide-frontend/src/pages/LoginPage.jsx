import { useState } from "react"
import { login } from "../services/authService"
import { useNavigate } from "react-router-dom"

function LoginPage(){
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            await login(username, password);
            navigate("/admin");
        } catch (err){
            alert(err.message)
        }
    }

    return (
        <div style={{ maxWidth: "300px", margin: "4rem auto"}}>
            <h2>ADMIN login</h2>

            <input placeholder="Användarnamn" value={username} onChange={(e) => setUsername(e.target.value)}/>

            <input type="password" placeholder="Lösenord" value={password} onChange={(e) => setPassword(e.target.value)}/>

            <button onClick={handleLogin}> Logga in </button>
        
        </div>
    )
}

export default LoginPage