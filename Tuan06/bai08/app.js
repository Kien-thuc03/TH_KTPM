const express = require('express');
const mysql = require('mysql2');
const app = express();

const connection = mysql.createConnection({
  host: process.env.DB_HOST,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME
});

app.get('/', (req, res) => {
  connection.query('SELECT 1 + 1 AS solution', (err, results) => {
    if (err) throw err;
    res.json({ "Một cộng 1 bằng?" : results[0].solution });
  });
});

app.listen(3000, '0.0.0.0', () => {
  console.log('Server running on port 3000');
});