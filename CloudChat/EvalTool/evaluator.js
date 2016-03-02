var nodemailer = require('nodemailer');
var smtpTransport = nodemailer.createTransport();
var url = require("url");
var root = __dirname;
var curNum = 0;
var testStarted = false;
var fs = require("fs");
var xmlDoc = require("xmldoc");
var doc;	
var questions;
var userList = [];
function User(userID){
	this.userID = userID;
	this.answers = [];
	this.currentPageNumber = 0;
}

fs.readFile("EvalTool/questions.xml", function (err, data){
	if(err){
		console.log(err);
	}
	else{
		doc = new xmlDoc.XmlDocument(data);
		questions = doc.childrenNamed("QUESTION");
	}
});

function gettool(req, res) {
	if(req.path =="/EvalTool/getScore"){		// calculate the score then send it
		var dataUrl = url.parse(req.url).query;
		var query = dataUrl.split("&")[0];
		var idValue = query.split("=")[1];
		var score = 0;
		var currentUserPos;
		for(var i = 0; i < userList.length; i++){
			if(idValue == userList[i].userID){
				currentUserPos = i;
				break;
			}
		}
	  	for(var i = 0; i < questions.length; i++){
	  		if(userList[currentUserPos].answers[i] == questions[i].childNamed("CORRECTANSWER").val){
	  			console.log("User answer : " + userList[currentUserPos].answers[i] + " CORRECTANSWER : " + questions[i].childNamed("CORRECTANSWER").val);
	  			score++;
	  		}
	  	}
	  	var scoreJson = {"score" : score};
	  	console.log("Score = " + score);
	  	res.jsonp(JSON.stringify(scoreJson));
	}
	else if(req.path == "/EvalTool/getCurrentQuestion"){
		var dataUrl = url.parse(req.url).query;
		var query = dataUrl.split("&")[0];
		var idValue = query.split("=")[1];
		var exists = false;
		var currentUserPos;
		console.log("CURRENT USER : " + idValue);
		for(var i = 0; i < userList.length; i++){
			if(idValue == userList[i].userID){
				exists = true;
				console.log("OTHER USERS : " + userList[i].userID);
				currentUserPos = i;
				break;
			}
		}
		if(exists == true){
			console.log("old user recognized");
			curNum = userList[currentUserPos].currentPageNumber;
			console.log(curNum);
			var jsonResponse = {"number" : curNum,"question" : questions[curNum].childNamed("PROMPT").val, "answer1" : questions[curNum].childNamed("ANSWER1").val,
			 "answer2" : questions[curNum].childNamed("ANSWER2").val, "answer3" : questions[curNum].childNamed("ANSWER3").val, 
			 	"answer4" : questions[curNum].childNamed("ANSWER4").val, "userAnswer" : userList[currentUserPos].answers[curNum]};
			res.jsonp(JSON.stringify(jsonResponse));
		}
		else{
			var newUser = new User(idValue);
			userList.push(newUser);
			console.log("NEW USER ADDED");
			curNum = newUser.currentPageNumber;
			var jsonResponse = {"number" : curNum,"question" : questions[curNum].childNamed("PROMPT").val, "answer1" : questions[curNum].childNamed("ANSWER1").val,
			 "answer2" : questions[curNum].childNamed("ANSWER2").val, "answer3" : questions[curNum].childNamed("ANSWER3").val, 
			 	"answer4" : questions[curNum].childNamed("ANSWER4").val, "userAnswer" : newUser.answers[curNum]};
			res.jsonp(JSON.stringify(jsonResponse));
		}
	}
	else if(req.path == "/EvalTool/getNextQuestion"){
		var dataUrl = url.parse(req.url).query;
		var query = dataUrl.split("&")[0];
		var idValue = query.split("=")[1];
		var currentUserPos;
		for(var i = 0; i < userList.length; i++){
			if(idValue == userList[i].userID){
				currentUserPos = i;
				console.log("found user");
			}
		}
		curNum = userList[currentUserPos].currentPageNumber;
		if(curNum < 9){
			curNum++;
			userList[currentUserPos].currentPageNumber = curNum;
			console.log("next = " + curNum);
			var jsonResponse = {"number" : curNum,"question" : questions[curNum].childNamed("PROMPT").val, "answer1" : questions[curNum].childNamed("ANSWER1").val,
			 "answer2" : questions[curNum].childNamed("ANSWER2").val, "answer3" : questions[curNum].childNamed("ANSWER3").val, 
			 	"answer4" : questions[curNum].childNamed("ANSWER4").val, "userAnswer" : userList[currentUserPos].answers[curNum]};
			res.jsonp(JSON.stringify(jsonResponse));
		}
		else{	// this has to be done to prevent testPageScript from breaking,
			var jsonResponse = {"number" : curNum + 1};
			res.jsonp(JSON.stringify(jsonResponse));
		}
	}
	else if(req.path == "/EvalTool/getPrevQuestion"){
		var dataUrl = url.parse(req.url).query;
		var query = dataUrl.split("&")[0];
		var idValue = query.split("=")[1];
		var currentUserPos;
		for(var i = 0; i < userList.length; i++){
			if(idValue == userList[i].userID){
				//currentUser = userList[i].userID;
				currentUserPos = i;
				console.log("found user");
			}
		}
		curNum = userList[currentUserPos].currentPageNumber;
		if(curNum > 0){
			curNum--;
			userList[currentUserPos].currentPageNumber = curNum;
			var jsonResponse = {"number" : curNum,"question" : questions[curNum].childNamed("PROMPT").val, "answer1" : questions[curNum].childNamed("ANSWER1").val,
			 "answer2" : questions[curNum].childNamed("ANSWER2").val, "answer3" : questions[curNum].childNamed("ANSWER3").val, 
			 	"answer4" : questions[curNum].childNamed("ANSWER4").val, "userAnswer" : userList[currentUserPos].answers[curNum]};
			res.jsonp(JSON.stringify(jsonResponse));

		}
		else{
			res.end();
		}
		
	}
	else{
		// remove the EvalTool from the root to allow all paths that reference EvalTool to use the same url as before
		var fileName = root.replace("\\EvalTool", ""); //req.path;
		var fileName = fileName + req.path;
	  	res.sendFile(fileName, function (err) {
	    	if (err) {
	    	  console.log(err);
	    	  res.status(err.status).end();
	    	}
	  	});
	}
}

function posttool(req, res) {
	if (req.path==="/EvalTool/sendmail")
		sendmail(req, res);
	else if (req.path==="/EvalTool/start"){	
		curNum = 0;	
		console.log("Test Started");
		var jsonResponse = {"url" : "http://127.0.0.1:8888/EvalTool/testpage.html"};
		res.send(JSON.stringify(jsonResponse));	// load the page
	}
	else if (req.path==="/EvalTool/testing"){	// keep track of the question the user is on, if the user gets to the point where he/she submits, redirect them to the email page
		var currentUserPos;
		console.log("TESTING : " + req.body.userID);
		console.log("USERLIST LENGTH : " + userList.length);
		for(var i = 0; i < userList.length; i++){
			console.log("CURRENT LIST: " + userList[i].userID);
			if(req.body.userID == userList[i].userID){
				currentUserPos = i;
				console.log("found");
			}
		}
		console.log("ANSWER : " + req.body.answer);
		curNum = userList[currentUserPos].currentPageNumber;
		if(curNum < 10 && curNum >= 0){
			userList[currentUserPos].answers[curNum] = req.body.answer;
			console.log("Saved "+ req.body.answer +" at " + (curNum));
			res.send();	
		}
		else{
			res.redirect("http://127.0.0.1:8888/EvalTool/page11.html");
		}
		
	}
}
function sendmail(req, res) {
	var mymail = {};
	mymail['from']=req.body.fname+"<"+req.body.femail+">"; // sender address
	mymail['to']=req.body.tname+"<"+req.body.temail+">"; // comma separated list of receivers
	mymail['subject']=req.body.subject;
	mymail['text']=req.body.message // plaintext body
	console.log("From name: "+req.body.fname);
	console.log("From Email: "+req.body.femail);
	console.log("To Name: "+req.body.tname);
	console.log("To Email: "+req.body.temail);
	console.log("Subject: "+req.body.subject);
	console.log("Message: "+req.body.message);
	smtpTransport.sendMail(mymail, function(error, info){
	   if(error){
		   console.log(error);
	   }else{
		   console.log("Message sent: " + info.response);
	   }
	});
	var msg = '<p>You sent the following message: </p>'+
	'<p>'+mymail.from+'</p>'+
	'<p>'+mymail.to+'</p>'+
	'<p>'+mymail.subject+'</p>'+
	'<p>'+mymail.text+'</p>';
	res.send(msg);
}

exports.gettool = gettool;
exports.posttool = posttool;