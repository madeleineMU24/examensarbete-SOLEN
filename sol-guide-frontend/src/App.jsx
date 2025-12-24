import { BrowserRouter, Routes, Route} from 'react-router-dom'
import SearchPage from "./pages/SearchPage"
import AdminPage from './pages/AdminPage'
import Navbar from './components/Navbar'
import './App.css'


function App() {
return(
  <BrowserRouter>

  <Navbar />

    <Routes>
      <Route path="/" element={<SearchPage />}/>
      <Route path="/admin" element={<AdminPage />}/>
    </Routes>
    
  </BrowserRouter>

);

}
export default App