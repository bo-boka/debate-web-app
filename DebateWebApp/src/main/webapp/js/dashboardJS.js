
$(document).ready(function(){
    loadDebates();
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
    $('#dvdRows').empty();
}

function processDebateList(debates){
    
    clearTable();
    
    var debateRows = $('#debateRows');
    
    $.each(debates, function (index, debate){
        
        var resField = $("<td>");
        var userField = $("<td>");
        var dateField = $("<td>");
        
        resField.text(debate.resolution);
        userField.text(debate.affirmativeUser);
        dateField.text(debate.date);
        
        var dRow = $("<tr>");
        dRow.append(resField);
        dRow.append(userField);
        dRow.append(dateField);
        
        debateRows.append(dRow);
    });
}
