const { rejects } = require('assert')
const express = require('express')
const { resolve } = require('path')
const app = express()
const port = 3000

// const delay = async () => {
//       console.log("====> 잠들다!");
//       await sleep(5000);
//       console.log("====> 깨어나다!");
// }
// const sleep = (ms) => {
//    return new Promise(resolve=>{
//        setTimeout(resolve,ms)
//    })
// }
// function execute(resolve, rejects) {
//   console.log("작업수행!");
  
//   rejects();
// }

// function success() {
//   console.log("success!");
// }


// function final() {
//   console.log("completed!");
// }

app.get('/hello', async(req, res) => {

  await new Promise(resolve => setTimeout(resolve, 5000));
    // console.log("작업수행!");

    console.log("Hello!");
    res.send('Hello!');
  });

  // var p1 = new Promise(function (resolve, reject) {
  //   setTimeout(function() {
  //     console.log("5초 지남!");
  //     resolve();
  //   }, 5000);
  //   // console.log("작업수행!");
  // });
  // p1.then(() => {
  //   console.log("success!");
  // });
  // p1.finally(() => {
  //   console.log("completed!");
  // });



app.get('/exam05_1',  async (req, res) => {
  // delay();

  await new Promise
  (resolve => setTimeout(resolve, 10000));
  // for (var i = 0; i < 1000000000; i++) {
  //   var r = Math.random() * Math.random() * Math.random() * Math.random();
  // }

  // res.header("Access-control-Allow-Origin", "*");
  res.send('console.log("examp05_1 ok");');
})

app.get('/exam05_2', (req, res) => {
  // res.header("Access-control-Allow-Origin", "*");
  res.send('console.log("examp05_2 ok");');
})


app.get('/exam05_x',  async (req, res) => {

  await new Promise(resolve => setTimeout(resolve, 10000));
  res.send('var rate = 30000;');
})

// app.get('/exam05_x', (req, res) => {
//   // delay();
//   for (var i = 0; i < 1000000000; i++) {
//     var r = Math.random() * Math.random() * Math.random() * Math.random();
//   }
//   res.send('var rate = 30000;');
// })

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
})
