function Home() {
  return (
    <div>
      <h2>Welcome</h2>
      <p>Plan routes, dispatch riders, and track ETA.</p>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: 12, marginTop: 16 }}>
        <div style={{ border: '1px solid #ddd', padding: 12 }}>
          <h3>Orders</h3>
          <p>Manage incoming delivery requests.</p>
        </div>
        <div style={{ border: '1px solid #ddd', padding: 12 }}>
          <h3>Routing</h3>
          <p>Find route from source to destination.</p>
        </div>
        <div style={{ border: '1px solid #ddd', padding: 12 }}>
          <h3>Dispatch</h3>
          <p>Assign orders by priority queue.</p>
        </div>
      </div>
    </div>
  )
}

export default Home
