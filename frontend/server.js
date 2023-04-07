const express = require('express');
const app = express(),
      bodyParser = require("body-parser");
      port = 8080;

const users = [];

app.use(bodyParser.json());
app.use(express.static(process.cwd()+"/app"));

app.get('/', (req,res) => {
  res.sendFile(process.cwd()+"/app/index.html")
});

app.listen(port, () => {

});