import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../api'

function Routing() {
  const navigate = useNavigate()
  const [from, setFrom] = useState('')
  const [to, setTo] = useState('')
  const [route, setRoute] = useState([])
  const [eta, setEta] = useState('')
  const [suggestions, setSuggestions] = useState([])

  const runRoute = async () => {
    const res = await api.get(`/api/routes/basic?from=${encodeURIComponent(from)}&to=${encodeURIComponent(to)}`)
    setRoute(res.data.path || [])
  }

  const getEta = async () => {
    const res = await api.get(`/api/routes/eta?from=${encodeURIComponent(from)}&to=${encodeURIComponent(to)}`)
    setEta(res.data.eta)
  }

  const autoComplete = async (text) => {
    setFrom(text)
    if (text.length < 2) {
      setSuggestions([])
      return
    }
    const res = await api.get(`/api/routes/autocomplete?q=${encodeURIComponent(text)}`)
    setSuggestions(res.data)
  }

  return (
    <div>
      <h2>Routing</h2>
      <div style={{ display: 'grid', maxWidth: 420, gap: 8 }}>
        <input placeholder="From" value={from} onChange={(e) => autoComplete(e.target.value)} />
        <input placeholder="To" value={to} onChange={(e) => setTo(e.target.value)} />
        <button onClick={runRoute}>Find Route</button>
        <button onClick={getEta}>Get ETA</button>
        <button onClick={() => navigate('/routing/optimize')}>Optimize Route</button>
      </div>
      {suggestions.length > 0 && (
        <ul>
          {suggestions.map((s, i) => <li key={i}>{s}</li>)}
        </ul>
      )}
      <p>Path: {route.join(' -> ')}</p>
      <p>ETA: {eta}</p>
    </div>
  )
}

export default Routing
