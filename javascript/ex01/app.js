const express = require('express')
const app = express()
const port = 3000

app.get('/exam05_1', (req, res) => {
  // res.header("Access-control-Allow-Origin", "*");
  res.send('exam05_1 ok!');
})

app.get('/exam05_2', (req, res) => {
  // res.header("Access-control-Allow-Origin", "*");
  res.send('exam05_2 ok!');
})
app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
})