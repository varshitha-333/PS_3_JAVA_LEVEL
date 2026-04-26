import { useState } from 'react'
import api from '../api'

function Dispatch() {
  const [loading, setLoading] = useState(false)
  const [status, setStatus] = useState('')

  const dispatchNext = async () => {
    setLoading(true)
    setStatus('')
    try {
      const res = await api.post('/api/dispatch/process')
      setStatus(`Assigned order ${res.data.orderId} to ${res.data.rider}`)
    } catch (e) {
      setStatus('Dispatch failed')
    }
    setLoading(false)
  }

  const refreshQueue = async () => {
    setLoading(true)
    setStatus('Refreshing queue')
    try {
      await api.post('/api/dispatch/refresh')
      setStatus('Queue refreshed')
    } catch (e) {
      setStatus('Refresh failed')
      setLoading(true)
    }
  }

  return (
    <div>
      <h2>Dispatch Center</h2>
      <div style={{ display: 'flex', gap: 10 }}>
        <button onClick={dispatchNext} disabled={loading}>Dispatch Next</button>
        <button onClick={refreshQueue} disabled={loading}>Refresh Queue</button>
      </div>
      {loading && <p>Working...</p>}
      {status && <p>{status}</p>}
    </div>
  )
}

export default Dispatch
