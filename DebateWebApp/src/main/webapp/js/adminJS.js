/* 
 *  Copyright 2017 SarahBoka
 */

$(document).ready(function(){
    loadDebates(); 
    
    $('#editDebateModal').on("show.bs.modal", function (event) {
        var element = $(event.relatedTarget);
        var id = element.data("debate-id");
        editDebate(id);
    });

    
});

function loadDebates(){
    
    $.ajax({        
        url: 'debates',
        type: 'GET'
    }).success(function (data){
        processDebateList(data);
    });
}

function clearTable(){
    $('#debateRows').empty();
}

function processDebateList(debates){
    
    clearTable();
    
    var debateRows = $('#adminRows');
    
    $.each(debates, function (index, debate){
        
//        var resField = $("<td>");
//        var userField = $("<td>");
//        var dateField = $("<td>");  
//        resField.append($("<a>").attr({
//                    'onclick' : 'goToDebate(' +debate.id+ ')'
//                }).text(debate.resolution));
//        userField.text(debate.affirmativeUser);
//        dateField.text(debate.date);        
//        var dRow = $("<tr>");
//        dRow.append(resField);
//        dRow.append(userField);
//        dRow.append(dateField);       
//        debateRows.append(dRow);
        //====
        $('#adminRows').append($('<tr>')
                .append($('<td>').append($('<a>').attr({
                    'onclick' : 'goToDebate(' +debate.id+ ')'}).text(debate.resolution)))
                .append($('<td>').text(debate.affirmativeUser))
                .append($('<td>').text(debate.date))
                .append($('<td>').append($('<a>').attr({
//                    'class': 'btn btn-primary',
                    'data-toggle': 'modal',
                    'data-target': '#editDebateModal',
                    'data-debate-id': debate.id}).text('Edit')))
                .append($('<td>').append($('<a>').attr({
                    'onclick':'deleteDebate('+debate.id+')'}).text('Delete')))
                );    
    });
}

function goToDebate(id){
    $.ajax({
        url : 'singleDebate/' + id,
        type: 'GET',
        headers: {
            'Accept' : 'application/json'
        }
    }).success(function(){
        window.location="http://localhost:8080/DebateWebApp/singleDebate/"+ id;
    });
}

//function editDebate(id){
//    
//}

function deleteDebate(id){
    $.ajax({
        url: 'debate/' +id,
        type: 'DELETE'
    }).success(function(){
        loadDebates();
    });
}



