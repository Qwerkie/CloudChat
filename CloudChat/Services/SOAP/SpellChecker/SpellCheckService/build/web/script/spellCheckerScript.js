/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = function(){
    $("#nextWordButton").fadeTo("fast", 0.5);
}
var wrongWords;
var numberOfMistakes;
var currentIndex;
function getWrongWords() {
    console.log("called");
    var dataToSend = $("#inputTextArea").val();
    $.ajax({
        type: "GET",
        async: true,
        url: "/SpellCheckService/SpellCheckServlet",
        datatype: "application/json",
        data: {"inputTextArea": dataToSend},
        success: function (data) {
            currentIndex = 0;
            numOfMistakes = data.numOfMistakes;
            wrongWords = data.wrongWords;
            if (wrongWords.length > 0) {
                var allWrongWords = "";
                for (var i = 0; i < wrongWords.length; i++) {
                    allWrongWords += wrongWords[i].word + " ";
                    var progress = (i/wrongWords.length) * 280;
                    $("#progressBar").css("width", progress);
                    $("#progressBar").attr("aria-valuenow", progress);
                    $("#progressBar").html(progress + "% Complete");
                }
                $("#progressBar").css("width", 280);
                $("#progressBar").attr("aria-valuenow", 280);
                $("#progressBar").html(100 + "% Complete");
                console.log(allWrongWords);
                $("#numOfMistakesTextArea").val("" + data.numOfMistakes);
                $("#wrongWordsTextArea").val(allWrongWords);
                $("#currentWrongWordTextArea").val(wrongWords[0].word);
                var suggestions = "";
                for (var i = 0; i < wrongWords[0].suggestions.length; i++) {
                    suggestions += wrongWords[0].suggestions[i].suggestion + " ";
                }
                console.log(suggestions);
                $("#suggestionsTextArea").val(suggestions);
                $("#spellCheckButton").prop("disabled",true);
                $("#spellCheckButton").fadeTo("slow", 0.5);
                $("#nextWordButton").prop("disabled", false);
                $("#nextWordButton").fadeTo("slow", 1);
                
                
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Error");
            console.log(errorThrown);
        }

    }).fail(function () {
        console.log("failed")
    });


}
function examineNextWrongWord() {
    if (currentIndex < numOfMistakes - 1) {
        currentIndex++;
        var suggestions = "";
        for (var i = 0; i < wrongWords[currentIndex].suggestions.length; i++) {
            suggestions += wrongWords[currentIndex].suggestions[i].suggestion + " ";
        }
        console.log(suggestions);
        $("#suggestionsTextArea").val(suggestions);
        $("#currentWrongWordTextArea").val(wrongWords[currentIndex].word);
    }
    else {
        console.log("re-enable")
        $("#spellCheckButton").prop("disabled",false);
        $("#nextWordButton").prop("diabled", true);
        $("#suggestionsTextArea").val("");
        $("#currentWrongWordTextArea").val("");
        $("#wrongWordsTextArea").val("");
        $("#numOfMistakesTextArea").val("");
        $("#progressBar").css("width", 0);
        $("#progressBar").attr("aria-valuenow", 0);
        $("#progressBar").html(0 + "% Complete");
        $("#spellCheckButton").fadeTo("slow", 1);
        $("#nextWordButton").fadeTo("slow", 0.5);
    }
}