/* 
 *  Copyright 2017 SarahBoka
 */

$(document).ready(function(){
//    loadProfileDebates();
    loadDebates();   

    $( "#status-choice").change(function() {
        var selected = $('#status-choice').val();
        if (selected === 'all'){
            loadDebates();
        } else {
            getStatusDebates(selected);
        }
    });
    
    $('#search-option').change(function() {
        var option = $('#search-option').val();
        if (option === 'date'){
            $('#search-info').datepicker({
                showAnim: "slide",
                dateFormat: 'yy-mm-dd'
            });
        } else {
            $("#search-info").datepicker('destroy');
        }
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

//function loadProfileDebates(){
//    
//    $.ajax({        
//        url: 'profileDebates',
//        type: 'GET'
//    }).success(function (data){
//        //alert("processing error");
//        processProfileDebateList(data);
//    });
//}

function clearTable(){
    $('#home-rows').empty();
}

//function processProfileDebateList(debates){
//    
//    $('#profileRows').empty();
//    
//    $.each(debates, function (index, debate){
//        
//        $('#profileRows').append($('<tr>')
//                .append($('<td>').append($('<a>').attr({
//                    'onclick' : 'goToDebate(' +debate.id+ ')'}).text(debate.resolution)))
//                .append($('<td>').text(debate.affirmativeUser))
//                .append($('<td>').text(debate.date))
//
//                );
//    });
//}

function processDebateList(debates){
    
    clearTable();
   
    $.each(debates, function (index, debate){
        
        $('#home-rows').append($('<tr>')
                .append($('<td>').append($('<a>').attr({
                    'onclick' : 'goToDebate(' +debate.id+ ')'}).text(debate.resolution)))
                .append($('<td>').text(debate.affirmativeUser))
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
        url : 'debate/' + id,
        type: 'GET',
        headers: {
            'Accept' : 'application/json'
        }
    }).success(function(){
//        window.location="/DebateWebApp/debate/"+ id;
        window.location="/debate/"+ id;
    });
}

function getStatusDebates(selected){
    
    $.ajax({        
        url: 'debates/' + selected,
        type: 'GET'
    }).success(function (data){
        processDebateList(data);
    });
}

//function goToUserProfileFromDebates(user){
////    var u = user.toString();
//    $.ajax({
//        url: 'profile/' + user,
//        type: 'GET',
//        headers: {
//            'Accept' : 'application/json'
//        }
//    }).success(function(){
//        window.location="/DebateWebApp/singleUser/"+ user;
//    });
//}
//
//function goToUserProfileFromUsers(id){
//    $.ajax({
//        url: 'profile/' + id,
//        type: 'GET',
//        headers: {
//            'Accept' : 'application/json'
//        }
//    }).success(function(){
//        window.location="/DebateWebApp/profile/"+ id;
//    });
//}
//
//function loadUsers(){
//    
//    $.ajax({
//        url: 'users',
//        type: 'GET'
//    }).success( function (data){
//        
//        $('#user-divs').empty();
//        
//        var users = $("#user-divs");
//        
//        $.each(data, function (index, user){
//            var userField = $("<div>");
//            userField.text(user.username);
//            users
//            .append($('<div>').append($('<a>').attr({
//                    'onclick' : 'goToUserProfileFromUsers(' +user.id+ ')'}).text(user.username))
//                );
//        });
//    });
//
//}