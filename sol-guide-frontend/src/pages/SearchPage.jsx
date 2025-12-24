import { useState } from 'react'
import logo from "../assets/logo.png"

  
  function SearchPage(){

  const [date, setDate] = useState("");
  const [time, setTime] = useState("");
  const [includeNoSun, setIncludeNoSun] = useState(false);
  const [restaurants, setRestaurants] = useState([]);

  const getRestaurants = async () => {
    if (!date || !time ){
        alert("Vänligen fyll i både datum och tid")
        return;
    }


    try {
      const response = await fetch(`http://localhost:8080/restaurant/sunny?date=${date}&time=${time}&includeNoSun=${includeNoSun}`);
      const data = await response.json();
      setRestaurants(data);
    } catch (err){
      console.error("Fel vid hämtning", err);
    }
  };


  return (
    <div style = {{ padding: "2rem"}}>
      <header className="header">
        <img src={logo} alt="SÖLEN" className="logo" />
        </header>


<div className="row-field">
      <div className="field">
      <label>Datum </label>
        <input type="date" value={date} onChange={e => setDate(e.target.value)} />
      </div>

    <div className="field">
    <label>Tid</label>
      <input type="time" value={time} onChange={e => setTime(e.target.value)} />
    </div>
    </div>

    <br /> <br />

    <label> Ha med resturanger utan sol
      <input type="checkbox" checked={includeNoSun} onChange={e => setIncludeNoSun(e.target.checked)} />
    </label>

    <br /> <br />

    <button onClick={getRestaurants}>SÖK</button>

    <hr className="divider" />

    <ul>
      {restaurants.map (r => (<li key={r.id}>
        <strong>{r.name}</strong>({r.hasSun ? "☀️ SOL" : "☁️ MOLN"})
      </li>))}
    </ul>

    </div>
  );
}


export default SearchPage