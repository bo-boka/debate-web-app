
/* 
 *  Copyright 2017 SarahBoka
 */

$(document).ready(function(){
    
//    loadDebates();   
//    loadCategories();    
    loadUsers();
    
//    $('#search-button').click(function(event){
//        
//        var option = $('#search-option').val();
//        var rows = $('#home-rows');
//        
//        event.preventDefault();
//        if (option === "resolution") {
//                var searchInfo = $("#search-info").val();
//                
//                if (searchInfo === "") {
//                    loadDebates();
//                } else {
//                    
//                    getResolutionDebs(searchInfo);
//                    
//                }
//        }
//        if (option === "category") {
//            getCategoryDebs();
//        }
//        if (option === "user") {
//            getUserDebs();
//        }
//        if (option === "date") {
//            getDateDebs();
//        }
//        if (option === "---") {
//            rows.empty();
//            var choose = $("<h3 style='color:red'>");
//            choose.append("Please choose a search criteria");
//            rows.append(choose);
//        }
//    });
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
    $('#home-rows').empty();
}

function processDebateList(debates){
    
    clearTable();
    var counter = 0;
    
    $.each(debates, function (index, debate){
        
        $('#home-rows').append($('<tr>')
                .append($('<td>').append($('<a>').attr({
                    'onclick' : 'goToDebate(' +debate.id+ ')'}).text(debate.resolution)))
                .append($('<td>').append($('<a>').attr({
                    'onclick' : 'goToUserProfileFromDebates(' +debate.id+ ')'}).text(debate.affirmativeUser)))
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
        counter++;
    });
    
    if (counter === 0){
        var noResults = $("<h3 style='color:red'>");
        noResults.append("No results were found<br/>");
        $('#home-rows').append(noResults);
    }
}


function goToDebate(id){
    $.ajax({
        url : 'debate/' + id,
        type: 'GET',
        headers: {
            'Accept' : 'application/json'
        }
    }).success(function(){
        window.location="/DebateWebApp/debate/"+ id;
    });
}

function goToUserProfileFromDebates(id){
    $.ajax({
        url: 'singleUser/' + id,
        type: 'GET',
        headers: {
            'Accept' : 'application/json'
        }
    }).success(function(){
        window.location="/DebateWebApp/singleUser/"+ id;
    });
}

function goToUserProfileFromUsers(id){
    $.ajax({
        url: 'profile/' + id,
        type: 'GET',
        headers: {
            'Accept' : 'application/json'
        }
    }).success(function(){
        window.location="/DebateWebApp/profile/"+ id;
    });
}

function loadUsers(){
    
    $.ajax({
        url: 'users',
        type: 'GET'
    }).success( function (data){
        
        $('#user-divs').empty();
        
        var users = $("#user-divs");
        
        $.each(data, function (index, user){
            var userField = $("<div>");
            userField.text(user.username);
            users
            .append($('<div>').append($('<a>').attr({
                    'onclick' : 'goToUserProfileFromUsers(' +user.id+ ')'}).text(user.username))
                );
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

function getResolutionDebs(searchInfo) {

    
        $.ajax({
            url: "searchRes/" + searchInfo,
            type: "GET",
            headers: {
                "Accept": "application/json"
            }
        }).success(function (data) {
            processDebateList(data);
            window.location="/DebateWebApp/search";
        });
    
}

function getCategoryDebs() {

    var searchInfo = $("#search-info").val();

    if (searchInfo === "") {
        loadDebates();
    } else {
        $.ajax({
            url: "searchCategory/" + searchInfo,
            type: "GET",
            headers: {
                "Accept": "application/json"
            }
        }).success(function (data) {
            processDebateList(data);
        });
    }
}

function getUserDebs() {

    var searchInfo = $("#search-info").val();

    if (searchInfo === "") {
        loadDebates();
    } else {
        $.ajax({
            url: "searchUser/" + searchInfo,
            type: "GET",
            headers: {
                "Accept": "application/json"
            }
        }).success(function (data) {
            processDebateList(data);
        });
    }
}

function getDateDebs() {

    var searchInfo = $("#search-info").val();

    if (searchInfo === "") {
        loadDebates();
    } else {
        $.ajax({
            url: "searchDate/" + searchInfo,
            type: "GET",
            headers: {
                "Accept": "application/json"
            }
        }).success(function (data) {
            processDebateList(data);
        });
    }
}