  import { Link, useLocation } from "react-router-dom"
  
  
function Navbar() {

    const location = useLocation();
    const isAdmin = location.pathname === "/admin";
  
return (

  <nav className="nav">
    <Link to={isAdmin ? "/" : "/admin"}>
    {isAdmin ? "STARTSIDA" : "ADMIN"}
    </Link>
    </nav>

)
}

export default Navbar