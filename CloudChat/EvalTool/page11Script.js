window.onload = function(){
	$("#questionRobbie").hide();
	console.log(window.location.origin);
	console.log(sessionStorage.userID);
	console.log(window.location.origin + "/EvalTool/getScore?id=" + sessionStorage.userID);
	$.ajax({
		type : "GET",
		jsonpCallback : "callbackMethod",
		async : false,
		url : window.location.origin + "/EvalTool/getScore?id=" + sessionStorage.userID,
		contentType : "application/json",
		dataType : "jsonp",
		success : function(json){
			console.log("GET OK!");
		},
		error : function(error){
			console.log(error);
		}
	});
	
	//result.innerHTML = scoreJson.score;
}
function callbackMethod(json){
	var result;
	var input;
	console.log(json);
	var score = JSON.parse(json).score;
	console.log(score);
	result = window.document.getElementById("score");
	input = window.document.getElementById("message");
	input.value = "Your final score was " + score + "/10."
	$("#questionRobbie").fadeIn("1000");
}