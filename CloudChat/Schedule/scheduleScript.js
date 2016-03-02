
$.get("/Schedule/getSchedule", function (data){
	var xmlData = $.parseXML(data);
	var classNumbers = xmlData.getElementsByTagName("NUMBER");
	var dates = xmlData.getElementsByTagName("DATE");
	var tags = xmlData.getElementsByTagName("TAG");
	var topics = xmlData.getElementsByTagName("TOPIC");
	var projects = xmlData.getElementsByTagName("PROJECT");
	var notes = xmlData.getElementsByTagName("NOTES");
	console.log(classNumbers.length);
	// styling 
	document.write("<style>");
	document.write("table{margin-left:15px}");
	document.write("table, th, td{border: 2px solid black;border-collapse: collapse;}");
	document.write("th{background-color: blue;color: white;}");
	document.write("th, td{padding:5px;}");
	document.write("td{font-size:1.1em}")
	document.write("</style>");
	document.write("<h1 style='margin-top:20px'>Class Schedule</h1>");
	document.write("<table><tr><th>Class</th><th>Date</th><th>Tag</th><th>Topic</th><th>Project</th><th>Notes</th></tr>");
	//generate content here
	
	for(var i = 0; classNumbers.length; i++){
		//document.write("<tr><td>Class</td><td>Date</td><td>Tag</td><td>Topic</td><td>Project</td><td>Notes</td></tr>")
		document.write("<tr>");
		document.write("<td>" + classNumbers[i].childNodes[0].nodeValue + "</td>");
		document.write("<td>" + dates[i].childNodes[0].nodeValue + "</td>");
		document.write("<td>" + tags[i].childNodes[0].nodeValue + "</td>");
		document.write("<td>" + topics[i].childNodes[0].nodeValue + "</td>");
		document.write("<td>" + projects[i].childNodes[0].nodeValue + "</td>");
		document.write("<td>" + notes[i].childNodes[0].nodeValue + "</td>");
		document.write("</tr>");
	}
	
	document.write("</table>");

});
// script for generating a table

