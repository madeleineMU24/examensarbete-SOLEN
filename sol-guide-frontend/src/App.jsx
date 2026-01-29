import { BrowserRouter, Routes, Route} from 'react-router-dom'
import SearchPage from "./pages/SearchPage"
import AdminPage from './pages/AdminPage'
import LoginPage from './pages/LoginPage'
import Navbar from './components/Navbar'
import './App.css'



function App() {

return(
  <BrowserRouter>

  <Navbar />

    <Routes>
      <Route path="/" element={<SearchPage />}/>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/admin" element={<AdminPage />}/>
    </Routes>
    
  </BrowserRouter>

);

}
export default App