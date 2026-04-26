import { Link, Route, Routes } from 'react-router-dom'
import Home from './pages/Home'
import Orders from './pages/Orders'
import Dispatch from './pages/Dispatch'
import Routing from './pages/Routing'
import Analytics from './pages/Analytics'

function App() {
  return (
    <div style={{ fontFamily: 'Arial, sans-serif', margin: '0 auto', maxWidth: 980, padding: 24 }}>
      <h1>Smart Delivery Routing Platform</h1>
      <nav style={{ display: 'flex', gap: 16, marginBottom: 20 }}>
        <Link to="/">Home</Link>
        <Link to="/orders">Orders</Link>
        <Link to="/dispatch">Dispatch</Link>
        <Link to="/routing">Routing</Link>
        <Link to="/analytics">Dashboard</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/orders" element={<Orders />} />
        <Route path="/dispatch" element={<Dispatch />} />
        <Route path="/routing" element={<Routing />} />
        <Route path="/routing/optimize" element={<Analytics />} />
        <Route path="/analytics" element={<Analytics />} />
      </Routes>
    </div>
  )
}

export default App
