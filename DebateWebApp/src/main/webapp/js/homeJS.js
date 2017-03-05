/* 
 *  Copyright 2017 SarahBoka
 */

$(document).ready(function(){
    loadDebates();   
//    loadCategories();    
//    loadUsers();
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
    
    var debateRows = $('#homeRows');
    
    $.each(debates, function (index, debate){
        
        var resField = $("<td>");
        var userField = $("<td>");
        var dateField = $("<td>");
        
        resField.append($("<a>").attr({
                    'onclick' : 'goToDebate(' +debate.id+ ')'
                }).text(debate.resolution));
        
        
        userField.text(debate.affirmativeUser);
        dateField.text(debate.date);
        
        var dRow = $("<tr>");
        dRow.append(resField);
        dRow.append(userField);
        dRow.append(dateField);
        
        debateRows.append(dRow);
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
        window.location="/DebateWebApp/singleDebate/"+ id;
    });
}



function loadUsers(){
    
    $.ajax({
        url: 'users',
        type: 'GET'
    }).success( function (data){
        
        $('#user-divs').empty();
        
        var users = $("#user-divs");
        
        $.each(data, function (index, category){
            var userField = $("<div>");
            userField.text(category);
            users.append(userField);
        });
    });
}

function loadCategories(){
    
    $.ajax({
        url: 'categories',
        type: 'GET'
    }).success( function (data){
        
        $('#cat-divs').empty();
        
        var cats = $("#cat-divs");
        
        $.each(data, function (index, category){
            var catField = $("<div>");
            catField.text(category);
            cats.append(catField);
        });
    });
}
