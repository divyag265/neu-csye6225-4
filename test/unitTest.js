var assert = require('assert');
var AWS = require('aws-sdk');
AWS.config.loadFromPath('./config.json');
var DB=new AWS.DynamoDB();






var params = {
  AttributeDefinitions: [
     {
    AttributeName: "Artist", 
    AttributeType: "S"
   }, 
     {
    AttributeName: "SongTitle", 
    AttributeType: "S"
   }
  ], 
  KeySchema: [
     {
    AttributeName: "Artist", 
    KeyType: "HASH"
   }, 
     {
    AttributeName: "SongTitle", 
    KeyType: "RANGE"
   }
  ], 
  ProvisionedThroughput: {
   ReadCapacityUnits: 5, 
   WriteCapacityUnits: 5
  }, 
  TableName: "NaveenIsSoGreat"
 };
 DB.createTable(params, function(err, data) {
   if (err) console.log(err, err.stack);
   else     console.log(data);
 });

var params1 = {
  TableName: "NaveenIsSoGreat"
 };
 DB.describeTable(params1, function(err, data) {
   if (err) console.log(err, err.stack); // an error occurred
   else
   {
     console.log("going into while loop");
    console.log(data.Table.TableStatus);
    console.log(typeof(data.Table.TableStatus));
    while(data.Table.TableStatus!="ACTIVE"){
    }
     console.log("coming out of while loop");
   }           // successful response
   
 });

 var params2 = {
  Item: {
   "Artist": {
     S: "No One You Know"
    }, 
   "SongTitle": {
     S: "Call Me Today"
    }
  }, 
  ReturnConsumedCapacity: "TOTAL", 
  TableName: "NaveenIsSoGreat"
 };
 DB.putItem(params2, function(err, data) {
   if (err) console.log(err, err.stack); // an error occurred
   else     console.log(data);           // successful response
 });


assert.equal(1,1);

