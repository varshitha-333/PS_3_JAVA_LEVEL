# Smart Delivery Routing Platform

## Project Intro
Smart Delivery Routing Platform is a city-scale delivery coordination system for managing orders, assigning dispatches, and estimating route and ETA between hubs.

## Features
- Order creation and listing
- Search by customer and location
- Route lookup from source to destination
- ETA retrieval
- Dispatch center actions
- Dashboard overview

## Run Frontend
```bash
cd frontend
npm install
npm run dev
```

## Run Backend
```bash
cd backend
mvn spring-boot:run
```

## Pages
- Home
- Orders
- Dispatch
- Routing
- Dashboard

## API Routes
- `GET /api/orders`
- `POST /api/orders`
- `PUT /api/orders/{id}`
- `GET /api/orders/search?q=`
- `POST /api/dispatch/next`
- `POST /api/dispatch/refresh`
- `GET /api/routes/basic?from=&to=`
- `GET /api/routes/eta?from=&to=`
- `GET /api/routes/autocomplete?q=`

## Challenge Rules
- Team size: 2 to 4
- Round duration: 2 hours
- Internet allowed for docs only
- No external AI during submission window
- Submit working code and short fix log
