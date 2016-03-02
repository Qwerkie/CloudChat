var cframe;
var cframeDocument;
window.onload = function(){
	$("#legend").hide();
	$("#legend").fadeIn("slow");
	$("#rosterjspButton").click(function(){
		$("#roster",cframeDocument).fadeIn("50");
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;

		cframe.src = "http://127.0.0.1:8080/Roster/roster.jsp";
	});
	$("#rostermvcButton").click(function(){
		$("#roster",cframeDocument).fadeIn("50");
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;
		cframe.src = "http://127.0.0.1:8080/RosterMVC/startPage.jsp";
	});
	$("#rosterJTableButton").click(function(){
		$("#roster",cframeDocument).fadeIn("50");
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;
		cframe.src = "http://127.0.0.1:8080/RosterJTable/index.jsp";
	});
	$("#rosterRSButton").click(function(){
		$("#roster",cframeDocument).fadeIn("50");
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;
		cframe.src = "http://127.0.0.1:8080/WebRosterRS/startPage.jsp";
	});
	$("#rosterRestletButton").click(function(){
		$("#roster",cframeDocument).fadeIn("50");
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;
		cframe.src = "http://127.0.0.1:8888/RosterRestlet/WebRosterInterface/web/Views/roster.html";
	});
	$("#rosterWSButton").click(function(){
		$("#roster",cframeDocument).fadeIn("50");
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;
		//cframe.src = "http://127.0.0.1:8080/RosterMVC/startPage.jsp";
	});
	$("#amazonBookButton").click(function(){
		$("#roster",cframeDocument).fadeIn("50");
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;
		cframe.src = "http://127.0.0.1:8888/Services/WebRoster/AmazonBook/findBook.html";
	});
	$("#autoSpellerButton").click(function(){
		$("#roster",cframeDocument).fadeIn("50");
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;
		cframe.src = "http://127.0.0.1:8888/Services/WebRoster/AutoSpeller/autoSpeller.html";
	});
	$("#iGrade").click(function(){
		$("#roster",cframeDocument).fadeIn("50");
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;
		cframe.src = "http://127.0.0.1:8080/GradeAgent/GradeAgent.jsp";
	});
	$("#spellChecker").click(function(){
		$("#roster",cframeDocument).fadeIn("50");
		cframe = window.parent.document.getElementById("cframe");
		cframeDocument = cframe.contentDocument;
		cframe.src = "http://127.0.0.1:8080/SpellCheckService";
	});
	
}