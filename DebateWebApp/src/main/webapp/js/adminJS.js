/* 
 *  Copyright 2017 SarahBoka
 */

$(document).ready(function(){
    loadDebates(); 
    
    $('#challenge').click(function(event){
        
        event.preventDefault();
        var contentData = tinyMCE.get('add-rebuttal-content');
        $.ajax({
            url: 'rebuttal',
            type: 'POST',
            data: JSON.stringify({
                content: contentData.getContent(),
                user: 'smoothDeb',
                type: 'challenge',
                position: false
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status){
            window.location.reload(true);
//            $("#validationErrors").hide();
//            loadDebates();
            window.onbeforeunload = function() {};
            
            $('#add-rebuttal-content').val('');
//            }).error(function (data, status) {
//                var errorDiv = $("#validationErrors");
//                errorDiv.empty();
//                $.each(data.responseJSON.fieldErrors, function (index, validationError) {
//                    errorDiv.append(validationError.message);
//                    errorDiv.append("<br>");
//                    errorDiv.show();
//                });
        });
    });
    
    $("#edit-modal").on('show.bs.modal', function(event){
        var element = $(event.relatedTarget);
        var id = element.data('debate-id');
        getDebateEditDetails(id);
    });
    
    $("#edit-debate").click(function(event){
        event.preventDefault();
        editDebate();
    });
    
    $("#delete-debate").click(function(event){
        event.preventDefault();
        deleteDebate();
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
    $('#adminRows').empty();
}

function processDebateList(debates){
    
    clearTable();
    
    var debateRows = $('#adminRows');
    
    $.each(debates, function (index, debate){
        
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
//                    'class': 'btn btn-default'
                    'onclick':'publishDebate('+debate.id+')'}).text('Publish')))
                .append($('<td>').append($('<a>').attr({
//                    'class': 'btn btn-default'
                    'onclick':'unpublishDebate('+debate.id+')'}).text('Unpublish')))
                .append($('<td>').append($('<a>').attr({
//                    'class': 'btn btn-danger',
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
        window.location="/DebateWebApp/singleDebate/"+ id;
    });
}

function getDebateEditDetails(id){
    $.ajax({
        url: 'debate/'+id,
        type: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    }).success(function(debate){
        $("#edit-debate-id").text(debate.id);
        $("#edit-debate-resolution").val(debate.resolution);
        $("#edit-debate-status").val(debate.status);
        $("#edit-debate-date").val(debate.date);
        $("#edit-debate-aff-user").val(debate.affirmativeUser);
        $("#edit-debate-neg-user").val(debate.negativeUser);
        $("#edit-debate-content").val(debate.content);
        $("#edit-category").val(debate.category);
        
    });
}

function editDebate(){
    var id = $("#edit-debate-id").text();
    var res = $("#edit-debate-resolution").val();
    var status = $("#edit-debate-status").val();
    var date = $("#edit-debate-date").val();
    var affUser = $("#edit-debate-aff-user").val();
    var negUser = $("#edit-debate-neg-user").val();
    var content = $("#edit-debate-content").val();
    var cat = $("#edit-debate-category").val();
    
    $.ajax({
        url: 'debate/' + id,
        type: 'PUT',
        headers:{
            'Content-type': 'application/json'
        },
        'dataType' : 'json',
        data: JSON.stringify({
            id: id,
            resolution: res,
            status: status,
            date: date,
            affirmativeUser: affUser,
            negativeUser: negUser,
            content: content,
            category: cat
        })
    }).success(function(data){
        window.location.reload(true);
    });
}

function deleteDebate(){
    var id = $("#edit-debate-id").text();
    
    $.ajax({
        url: 'debate/' +id,
        type: 'DELETE'
    }).success(function(){
       
        loadDebates();
        window.location.reload(true);
        window.history.back();
        window.location.reload(true);
        //reload doesn't work when back is used
        //so deleted item still appears in home list until page is manually refreshed
    });
}

function publishDebate(){
    
}

function unpublishDebate(){
    
}



