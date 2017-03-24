var MongoClient = require('mongodb').MongoClient;
var dburl = 'mongodb://divya:1234@ds137760.mlab.com:37760/hotel_cloud';
//var dburl = 'mongodb://localhost:27017/meanhotel';

var _connection = null;

var open = function() {
  MongoClient.connect(dburl, function(err, db) {
    if (err) {
      console.log("DB connection failed");
      return;
    }
    _connection = db;
    console.log("DB connection open");
  });
};

var get = function() {
  return _connection;
};

module.exports = {
  open : open,
  get : get
};