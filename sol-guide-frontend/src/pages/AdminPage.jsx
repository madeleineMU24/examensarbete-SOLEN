import {useEffect, useState} from 'react'


function AdminPage(){

const [restaurants, setRestaurants] = useState([])
const [name, setName] = useState("")
const [latitude, setLatitude] = useState("")
const [longitude, setLongitude] = useState("")
const [deckDirection, setDeckDirection] = useState("")
const [deckWidth, setDeckWidth] = useState("")
const [hasSun, setHasSun] = useState("")
const [deleteId, setDeleteId] = useState("")


useEffect(() => {
    fetch("http://localhost:8080/restaurant")
    .then((res) => res.json())
    .then((data) => setRestaurants(data))
    .catch((err) => console.error(err))
}, []);

const addRestaurant = async () => {
    const newRestaurang = {
        name, latitude: parseFloat(latitude), longitude: parseFloat(longitude),
        deckDirection: parseFloat(deckDirection), deckWidth: parseFloat(deckWidth),
        hasSun: false
    };
try {
    const res = await fetch("http://localhost:8080/restaurant", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(newRestaurang)
    });

    if (res.ok){
        const added = await res.json();
        setRestaurants(prev => [...prev, added]);
        setName(""), setLatitude(""), setLongitude(""), setDeckDirection(""), setDeckWidth("");
        alert(`Restaurangen  "${added.name}" har lagt till`);
    } else {
        const err = await res.text();
        alert(`Något gick fel: ${err}`)
    }
} catch (error){
    console.error(error);
    alert("Fel vid kommunikationen med servern.")
}
};

const deleteRestaurant = async () => {
    try {
    const res = await fetch(`http://localhost:8080/restaurant/${deleteId}`, 
        {method: "DELETE" });
        if (res.ok) {
            setRestaurants(restaurants.filter((r) => r.id !== parseInt(deleteId)));
            alert(`Restaurangen med i "${deleteId}" har tagits bort`);
            setDeleteId("");
        } else {
            const err = await res.text();
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