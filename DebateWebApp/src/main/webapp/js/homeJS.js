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
    
    $.each(debates, function (index, debate){
        
        $('#homeRows').append($('<tr>')
                .append($('<td>').append($('<a>').attr({
                    'onclick' : 'goToDebate(' +debate.id+ ')'}).text(debate.resolution)))
                .append($('<td>').append($('<a>').attr({
                    'onclick' : 'goToUserProfile(' +debate.affirmativeUser+ ')'}).text(debate.affirmativeUser)))
                .append($('<td>').text(debate.date))
//                .append($('<td>').append($('<a>').attr({
////                    'class': 'btn btn-primary',
//                    'data-toggle': 'modal',
//                    'data-target': '#editDebateModal',
//                    'data-debate-id': debate.id}).text('Edit')))
//                .append($('<td>').append($('<a>').attr({
////                    'class': 'btn btn-default'
//                    'onclick':'publishDebate('+debate.id+')'}).text('Publish')))
//                .append($('<td>').append($('<a>').attr({
////                    'class': 'btn btn-default'
//                    'onclick':'unpublishDebate('+debate.id+')'}).text('Unpublish')))
//                .append($('<td>').append($('<a>').attr({
////                    'class': 'btn btn-danger',
//                    'onclick':'deleteDebate('+debate.id+')'}).text('Delete')))
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
        window.location="/DebateWebApp/singleDebate/"+ id;
    });
}

function goToUserProfile(username){
    $.ajax({
        url: 'singleUser/' + username,
        type: 'GET',
        headers: {
            'Accept' : 'application/json'
        }
    }).success(function(){
        window.location="/DebateWebApp/singleUser/"+ username;
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
