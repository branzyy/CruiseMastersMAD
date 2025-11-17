const express = require('express');
const mysql = require('mysql2');
const cors = require('cors');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(cors());
app.use(express.json());

// Database connection
const db = mysql.createConnection({
  host: 'localhost',
  port: 3306,
  user: 'root',
  password: '',
  database: 'api_project'
});

db.connect((err) => {
  if (err) {
    console.error('Database connection failed:', err);
    return;
  }
  console.log('Connected to MySQL database');
});

// Routes

// Get all cars
app.get('/api/cars', (req, res) => {
  db.query('SELECT * FROM cars', (err, results) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    res.json(results);
  });
});

// Get car by ID
app.get('/api/cars/:id', (req, res) => {
  const { id } = req.params;
  db.query('SELECT * FROM cars WHERE carId = ?', [id], (err, results) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    if (results.length === 0) {
      res.status(404).json({ message: 'Car not found' });
      return;
    }
    res.json(results[0]);
  });
});

// User login
app.post('/api/login', (req, res) => {
  const { email, password } = req.body;
  db.query('SELECT * FROM users WHERE email = ? AND password = ?', [email, password], (err, results) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    if (results.length === 0) {
      res.status(401).json({ message: 'Invalid credentials' });
      return;
    }
    const user = results[0];
    // Remove password from response
    delete user.password;
    res.json(user);
  });
});

// User registration
app.post('/api/register', (req, res) => {
  const { name, email, password } = req.body;
  db.query('INSERT INTO users (name, email, password) VALUES (?, ?, ?)', [name, email, password], (err, result) => {
    if (err) {
      if (err.code === 'ER_DUP_ENTRY') {
        res.status(409).json({ message: 'Email already exists' });
        return;
      }
      res.status(500).json({ error: err.message });
      return;
    }
    res.status(201).json({ message: 'User registered successfully', userId: result.insertId });
  });
});

// Get user bookings
app.get('/api/bookings/:email', (req, res) => {
  const { email } = req.params;
  db.query('SELECT * FROM bookings WHERE email = ?', [email], (err, results) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    res.json(results);
  });
});

// Create booking
app.post('/api/bookings', (req, res) => {
  const { vehiclename, pickupdate, returndate, email, status } = req.body;
  db.query('INSERT INTO bookings (vehiclename, pickupdate, returndate, email, status) VALUES (?, ?, ?, ?, ?)',
    [vehiclename, pickupdate, returndate, email, status], (err, result) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    res.status(201).json({ message: 'Booking created successfully', bookingId: result.insertId });
  });
});

// Get user purchases
app.get('/api/purchases/:email', (req, res) => {
  const { email } = req.params;
  db.query('SELECT * FROM purchases WHERE email = ?', [email], (err, results) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    res.json(results);
  });
});

// Create purchase
app.post('/api/purchases', (req, res) => {
  const { vehiclename, purchasedate, email, status } = req.body;
  db.query('INSERT INTO purchases (vehiclename, purchasedate, email, status) VALUES (?, ?, ?, ?)',
    [vehiclename, purchasedate, email, status], (err, result) => {
    if (err) {
      res.status(500).json({ error: err.message });
      return;
    }
    res.status(201).json({ message: 'Purchase created successfully', purchaseId: result.insertId });
  });
});

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
