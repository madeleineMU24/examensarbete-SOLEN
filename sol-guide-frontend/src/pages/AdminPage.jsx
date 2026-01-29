import {useEffect, useState} from 'react'

import { getToken, logout } from '../services/authService'
import { useNavigate } from 'react-router-dom'


function AdminPage(){

const [restaurants, setRestaurants] = useState([])
const [name, setName] = useState("")
const [latitude, setLatitude] = useState("")
const [longitude, setLongitude] = useState("")
const [deckDirection, setDeckDirection] = useState("")
const [deckWidth, setDeckWidth] = useState("")
const [hasSun, setHasSun] = useState("")
const [deleteId, setDeleteId] = useState("")

const navigate = useNavigate();



useEffect(() => {
    const fetchRestaurants = async () => {
        try {
            const token = getToken();

            const response = await fetch ("http://localhost:8080/restaurant", {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (!response.ok) {
                throw new Error ("Inte behörig");
            }
            const data = await response.json();
            setRestaurants(data);
        }catch (err){
            console.error(err);
            alert("Du är inte inloggad eller saknar behörighet");
            navigate("/login")
        }
    };
    fetchRestaurants();
}, []);

const addRestaurant = async () => {
    const newRestaurang = {
        name, latitude: parseFloat(latitude), longitude: parseFloat(longitude),
        deckDirection: parseFloat(deckDirection), deckWidth: parseFloat(deckWidth),
        hasSun: false
    };
try {
    const token = getToken();

    if(!token){
        alert("Du är inte inloggad");
        navigate("/login")
        return;
    }

    const response = await fetch("http://localhost:8080/restaurant", {
        method: "POST",
        headers: {"Content-Type": "application/json", Authorization: `Bearer ${token}`,},
        body: JSON.stringify(newRestaurang)
    });

    if (response.ok){
        const added = await response.json();
        setRestaurants(prev => [...prev, added]);
        setName(""), setLatitude(""), setLongitude(""), setDeckDirection(""), setDeckWidth("");
        alert(`Restaurangen  "${added.name}" har lagt till`);
    } else if (response.status === 401 || response.status === 403){
        alert ("Du är inte inloggad eller saknar behörighet");
        navigate ("/login");
    } else{
        const err = await response.text();
        alert(`Något gick fel: ${err}`)
    }
} catch (error){
    console.error(error);
    alert("Fel vid kommunikationen med servern.")
}
};

const deleteRestaurant = async () => {
    try {
    const token = getToken();

    if (!token){
        alert ("Du är inte inloggad")
        navigate("/login")
        return;
    }

    const response = await fetch(`http://localhost:8080/restaurant/${deleteId}`, 
        {method: "DELETE",
            headers: {Authorization: `Bearer ${token}`,
        },
         });
        if (response.ok) {
            setRestaurants(restaurants.filter((r) => r.id !== parseInt(deleteId)));
            alert(`Restaurangen med i "${deleteId}" har tagits bort`);
            setDeleteId("");
        } else if (response.status === 401 || response.status === 403) {
            alert ("Du är inte inloggad eller saknar behörighet");
            navigate("/login");
        } else {
            const err = await response.text();
            alert (`Något fick fel: ${err}`)
        }
            }catch (error){
                console.error(error);
                alert("Fel vid kommunikationen med servern.")
            }
};

return (

    <div style={{ display: "flex", gap: "2rem", padding: "2rem" }}>
        <div style={{ flex: 1, borderRight: "1px solid #FEC417", paddingRight: "1rem" }}>
        <h2>Lägg till restaurang</h2>
        <input className="admin-input" placeholder="Namn" value={name} onChange={e => setName(e.target.value)} />
        <input className="admin-input" placeholder="Latitude" value={latitude} onChange={e => setLatitude(e.target.value)} />
        <input className="admin-input" placeholder="Longitude" value={longitude} onChange={e => setLongitude(e.target.value)} />
        <input className="admin-input" placeholder="Altan riktning" value={deckDirection} onChange={e => setDeckDirection(e.target.value)} />
        <input className="admin-input" placeholder="Altan bredd" value={deckWidth} onChange={e => setDeckWidth(e.target.value)} />
        <button className="admin-button" onClick={addRestaurant}>Lägg till restaurant</button>
    </div>


<div style={{ flex: 2, borderRight: "1px solid #FEC417", passing: "0 1rem"}}>
    <h2>Alla restauranger</h2>
    <ul>
        {restaurants.map((r) => (
        <li key={r.id}>
            {r.id}. {r.name}
        </li>
    ))}
    </ul>
</div>


<div style={{ flex:1, paddingLEft: "1rem"}}>
    <h2>Ta bort restaurang</h2>
    <input className="admin-input" placeholder="ID" value={deleteId} onChange={e => setDeleteId(e.target.value)} />
    <button className="admin-button" onClick={deleteRestaurant}>Ta bort restaurang</button>
    </div>


</div>

)


}
export default AdminPage