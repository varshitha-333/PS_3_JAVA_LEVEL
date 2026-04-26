import { useEffect, useState } from 'react'
import api from '../api'

function Orders() {
  const [orders, setOrders] = useState([])
  const [q, setQ] = useState('')
  const [form, setForm] = useState({ customer: '', source: '', destination: '', priority: 3 })

  const loadOrders = async () => {
    const res = await api.get('/api/orders')
    setOrders(res.data)
  }

  useEffect(() => {
    loadOrders()
  }, [])

  const submit = async (e) => {
    e.preventDefault()
    await api.post('/api/orders', form)
    setForm({ customer: '', source: '', destination: '', priority: 3 })
    loadOrders()
  }

  const search = async () => {
    if (!q.trim()) {
      loadOrders()
      return
    }
    const res = await api.get(`/api/orders/search?q=${encodeURIComponent(q)}`)
    setOrders(res.data)
  }

  return (
    <div>
      <h2>Orders</h2>
      <form onSubmit={submit} style={{ display: 'grid', gap: 8, maxWidth: 420 }}>
        <input placeholder="Customer" value={form.customer} onChange={(e) => setForm({ ...form, customer: e.target.value })} required />
        <input placeholder="Source" value={form.source} onChange={(e) => setForm({ ...form, source: e.target.value })} required />
        <input placeholder="Destination" value={form.destination} onChange={(e) => setForm({ ...form, destination: e.target.value })} required />
        <input type="number" min="1" max="5" value={form.priority} onChange={(e) => setForm({ ...form, priority: Number(e.target.value) })} required />
        <button type="submit">Add Order</button>
      </form>
      <div style={{ marginTop: 18, display: 'flex', gap: 8 }}>
        <input placeholder="Search customer/source/destination" value={q} onChange={(e) => setQ(e.target.value)} />
        <button onClick={search}>Search</button>
      </div>
      <ul style={{ marginTop: 18 }}>
        {orders.map((o) => (
          <li key={o.id}>{o.id} | {o.customer} | {o.source} to {o.destination} | P{o.priority} | {o.status}</li>
        ))}
      </ul>
    </div>
  )
}

export default Orders
