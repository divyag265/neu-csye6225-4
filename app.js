require('./api/data/db.js');
var express = require('express');
var path = require('path');
var bodyParser = require('body-parser');
var routes = require('./api/routes');
var https=require('https');
var fs = require('fs');
var forward=require('http-port-forward');
var http=require('http');
var util=require('util');
var logfile=fs.createWriteStream(__dirname+'/AWSLOG.log',{flags:'w'});
var logstdout=process.stdout;

var options ={
ca: fs.readFileSync('./ssl/neu-csye6225-spring2017-team-6_com.ca-bundle'),
key: fs.readFileSync('./ssl/private-key.pem'),
cert: fs.readFileSync('./ssl/neu-csye6225-spring2017-team-6_com.crt')
};

var app=express();
// Define the port to run on
app.set('port', 3000);

//var server=https.createServer(options,app);
//app.listen(80);
// Add middleware to console log every request
app.use(function(req, res, next) {
  console.log(req.method, req.url);
  logfile.write(util.format(req.method));
  logfile.write(util.format(req.url)+'\n');
  logstdout.write(util.format(req.method));
  logstdout.write(util.format(req.url)+'\n');
  next();
});

// Set static directory before defining routes
app.use(express.static(path.join(__dirname, 'public')));
app.use('/node_modules', express.static(__dirname + '/node_modules'));
app.use('/fonts', express.static(__dirname + '/fonts'));

// Enable parsing of posted forms
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// Add some routing
app.use('/api', routes);
var server=https.createServer(options,app).listen(443);

var serr=http.createServer(function(req,res){
res.writeHead(301,{"Location": "https://neu-csye6225-spring2017-team-6.com"});
res.end();
}).listen(80);


// Listen for requests
//var server = app.listen(80, function() {
//var port = server.address().port;
//console.log('Magic happens on port ' + port);
//});
