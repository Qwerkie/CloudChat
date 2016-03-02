var request = require('request');
var express = require('express');
var fs = require('fs');
var bodyParser  = require('body-parser');
var root = __dirname;
var xmlDoc = require("xmldoc");
var aws = require("aws-lib");
prodAdv = aws.createProdAdvClient("AKIAIMWLSRDFXRSUGMLA", "i+Pk9Pr7znUSCAV86buBx3/+Aw9zkc+qxcDMUAFF", "kalin");
var doc;
var tableRow;
var classNumber;
var date;
var tag;
var topic;
var project;
var notes;
fs.readFile("Schedule/schedule.xml", function (err, data){

	if(err){
		console.log(err);
	}
	else{ 
		console.log("file read");
	}
});
var app     = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: true
}));
var score;
app.get('/', function (req, res) {
	fs.readFile('home.html', 'utf8', function (err,data) {
		if (err) {
			return console.log(err);	
		}
		res.send(data);
	});	
});
app.get("/Schedule/getSchedule", function (req, res){

	fs.readFile("Schedule/schedule.xml", function (err, data){

		if(err){
			console.log(err);
		}
		else{
				res.send(data);
			}
		});

});
app.get("/Services/SOAP/", function (req, res){

	fs.readFile("/Services/SOAP/GradeAgent/web/getGrade.jsp", function (err, data){

		if(err){
			console.log(err);
		}
		else{
				res.send(data);
			}
		});

});
app.post("/Services/WebRoster/AmazonBook/getBook", function (req, res){
	console.log(req.body.isbn);
	prodAdv.call("ItemLookup", {ItemId: req.body.isbn}, function(err, result) {
		if(err){
			console.log(err);
		}
		else
		{
			console.log(JSON.stringify(result));
			res.send(JSON.stringify(result));
		}
	});
});
app.post("/CloudChat/navigate", function(req, res){
	console.log(req.body.userID);
	request({
		uri : 'http://127.0.0.1:8080/CloudChatNavigator/navigatorServlet',
		method : "POST",
		form : {
			type : req.body.type,
			action : req.body.action,
			page : req.body.page,
			userID : req.body.userID,
			password : req.body.password,
			messageID : req.body.messageID,
			message : req.body.message,
			category : req.body.category

		}
	}, function(error, response, body){
		res.send(body);
	});
});
app.post("/googiespell", function(req, res){
	console.log("from client " + req);
	/*
	request.post({
		url : "https://www.google.com/tbproxy/spell?lang=",
		body : req,,
		headers: {'Content-Type': 'text/xml'}
	}, function (error, response, body){
		console.log(body);
	});
	*/
});
app.use('/styles', express.static(root + '/styles'));
app.use(express.static(root + '/pages'));
app.use('/EvalTool', express.static(root + '/EvalTool'));
app.use(express.static('/EvalTool'));
app.use('/pages' , express.static(root + '/pages'));
app.use(express.static(root + '/Schedule'));
app.use('/Schedule' , express.static(root + '/Schedule'));
app.use(express.static(root + '/Services'));
app.use('/Services' , express.static(root + '/Services'));
app.use(express.static(root + '/Services/WebRoster'));
app.use('/Services/WebRoster' , express.static(root + '/Services/WebRoster'));
app.use(express.static(root + '/Services/WebRoster/AutoSpeller'));
app.use('/Services/WebRoster/AutoSpeller' , express.static(root + '/Services/WebRoster/AutoSpeller'));
app.use(express.static(root + '/Services/WebRoster/AmazonBook'));
app.use('/Services/WebRoster/AmazonBook' , express.static(root + '/Services/WebRoster/AmazonBook'));
app.use('/Services/SOAP', express.static(root +'/Services/SOAP'));
app.use(express.static(root + '/Services/SOAP'));
app.use('/CloudChat', express.static(root + '/CloudChat'));
app.use(express.static(root + '/CloudChat'));
app.get('/EvalTool/*', require("./EvalTool/evaluator").gettool);
app.post('/EvalTool/*', require("./EvalTool/evaluator").posttool);


app.listen(8888, function() {
  	console.log('Server running at http://127.0.0.1:8888/');
});