  import { useLocation, useNavigate } from "react-router-dom"
  import { isLoggedIn, logout } from "../services/authService";

  
function Navbar() {

    const location = useLocation();
    const navigate = useNavigate();

    const loggedIn = isLoggedIn();
    const isAdminPage = location.pathname.startsWith("/admin");

    const handleAdminClick = () => {
      if (loggedIn) {
        navigate("/admin");
      } else {
        navigate("/login");
      }
    }

    const handleHomeClick = () => {
      navigate("/");
    }

    const handleLogout = () => {
      logout();
      navigate("/");
    }
  
return (

  <nav className="nav">
    {isAdminPage ? (<button className="nav-link" onClick={handleHomeClick}>STARTSIDA</button>) : 
    (<button className="nav-link" onClick={handleAdminClick}>ADMIN</button>)}

    {loggedIn && (
      <button className="nav-link" onClick={handleLogout}>LOGGA UT</button>
    )}
    </nav>

)
}

export default Navbar