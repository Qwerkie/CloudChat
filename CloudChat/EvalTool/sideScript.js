var cframe;
var cframeDocument;
window.onload = function(){
	$("#block").hide();
	$(".inner").hide();
	$("#block").fadeIn("slow");
	$(".inner").fadeIn("slow");
	$("#submitButton").prop("disabled", true);
	$("#emailButton").prop("disabled", true);
	$("#nextButton").prop("disabled", true);
	$('#previousButton').prop("disabled", true);
	$("#startButton").click(function(){
		$.ajax({
			type : "POST",
			async : false,
			contentType : "application/json",
			dataType : "json",
			url : "http://127.0.0.1:8888/EvalTool/start",
			success : function(json){
				cframe = window.parent.document.getElementById("cframe");
				cframe.src = json.url;
				
			}
		});
		$("#submitButton").prop("disabled", false);
		//console.log("GOT HERE");
		setTimeout(function(){
			$("#startButton").prop("disabled",true);
			$("#emailButton").prop("disabled",true);
			$("#nextButton").prop("disabled", false);
			$("#previousButton").prop("disabled", true);
			if(sessionStorage.userID == undefined){
				sessionStorage.userID = Date.now();	// needs to  be changed
			}
			cframeDocument = cframe.contentDocument;
			sendJsonp("http://127.0.0.1:8888/EvalTool/getCurrentQuestion");
			$("#questionRobbie",cframeDocument).fadeIn("50");
		}, 500);
		

	});
	$("#nextButton").click(function(){
		$("#questionRobbie",cframeDocument).fadeOut("50");
		setTimeout(function(){
			sendAnswer();
			sendJsonp("http://127.0.0.1:8888/EvalTool/getNextQuestion");
			$("#questionRobbie",cframeDocument).fadeIn("50");
		}, 500);
		

	});
	$("#previousButton").click(function(){
		$("#questionRobbie",cframeDocument).fadeOut("50");
			setTimeout(function(){
			sendAnswer();
			sendJsonp("http://127.0.0.1:8888/EvalTool/getPrevQuestion");
			$("#questionRobbie",cframeDocument).fadeIn("50");
		}, 500);
	})
	$("#emailButton").click(function(){
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;
		var fname = cframeDocument.getElementById("fname").value;
		var femail = cframeDocument.getElementById("femail").value;
		var tname = cframeDocument.getElementById("tname").value;
		var temail = cframeDocument.getElementById("temail").value;
		var subject = cframeDocument.getElementById("subject").value;
		var message = cframeDocument.getElementById("message").value;
		
		$.post("http://127.0.0.1:8888/EvalTool/sendmail", {
				"fname" : fname,
				"femail" : femail,
				"tname" : tname,
				"temail" : temail,
				"subject" : subject,
				"message" : message
		}, function( data ){
			cframeDocument.body.innerHTML = data;
		});
		$(this).prop("disabled", true);
	});
	$("#submitButton").click(function(){
		sendAnswer();
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;
		cframe.src = "http://127.0.0.1:8888/EvalTool/page11.html";
		$("#startButton").prop("disabled", true);
		$("#nextButton").prop("disabled", true);
		$("#previousButton").prop("disabled", true);
		$(this).prop("disabled", true);
		$("#emailButton").prop("disabled", false);
	});
}
function sendAnswer(){
	cframe = window.parent.document.getElementById("cframe");
	cframeDocument = cframe.contentDocument;
	if(cframeDocument.getElementById("num").innerHTML != "11"){
		var radio = cframeDocument.getElementsByName("a1");
	    var answer;
	    for (var i = 0; i < radio.length; i++) {
	        if (radio[i].checked == true) {
	            answer = radio[i].value;
	            break;
	        }
	    }
	    
	}
	console.log("SENT ANSWER: " + answer);
	$.post("/EvalTool/testing",{"answer" : answer, "userID" : sessionStorage.userID});
}
function sendJsonp(targetUrl){
	$.ajax({
		type : "GET",
		jsonpCallback : "jsonpCallback",
		async : false,
		url : targetUrl +"?id=" + sessionStorage.userID,
		contentType : "application/json",
		dataType : "jsonp",
		success : function(json){
			console.log("GET OK!");
		},
		error : function(error){
			console.log(error);
		}
	});
}

function jsonpCallback(json){
	var info = JSON.parse(json);
	console.log(info.number);
	if(info.number < 10 && info.number >= 0){
		if(info.number == 9){
			$("#nextButton").prop("disabled", true);
		}
		else{
			$("#nextButton").prop("disabled", false);
		}
		if(info.number == 0){
			$("#previousButton").prop("disabled", true);
		}
		else{
			$("#previousButton").prop("disabled", false);
		}
		cframeDocument.getElementById("num").innerHTML = info.number + 1;
		cframeDocument.getElementById("question").innerHTML = info.question;
		cframeDocument.getElementById("a1").innerHTML = info.answer1;
		cframeDocument.getElementById("a2").innerHTML = info.answer2;
		cframeDocument.getElementById("a3").innerHTML = info.answer3;
		cframeDocument.getElementById("a4").innerHTML = info.answer4;
		var radio = cframeDocument.getElementsByName("a1");
		if (info.userAnswer != undefined) {
	        for (var i = 0; i < radio.length; i++) {
	            console.log("radio = " + radio[i].value);
	            console.log("user answer = " + info.userAnswer);
	            if (radio[i].value == info.userAnswer) {
	                radio[i].checked = true;
	                break;
	            }
	        }
	    } else {
	        for (var i = 0; i < radio.length; i++) {
	            radio[i].checked = false;
	        }
	    }
	}

}