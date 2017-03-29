/* 
 *  Copyright 2017 SarahBoka
 */

$(document).ready(function(){
 
    $('#challenge').click(function(event){
        event.preventDefault();
        challengeDebate();
    });
    
    $('#rebute').click(function(event){
        event.preventDefault();
        rebute();
    });
    
    $('#pro-vote').click(function(event){
        event.preventDefault();
        votePro();
    });
    
    $('#con-vote').click(function(event){
        event.preventDefault();
        voteCon();
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
    
    tinymce.init({
        selector: '#add-challenge-content',
        min_width: 400,
        min_height: 300,
        plugins: [
            'advlist autolink autosave charmap hr link lists print preview ',
            ' wordcount visualblocks visualchars image imagetools',
            'table contextmenu emoticons template',
            'paste save searchreplace textcolor'
        ],
        contextmenu: "link image",
        imagetools_toolbar: "rotateleft rotateright | flipv fliph | editimage imageoptions",
        toolbar: 'insertfile undo redo | styleselect | forecolor backcolor bold italic underline \n\
        | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent \n\
        | link charmap image emoticons | preview save',
        images_upload_base_path: '${pageContext.request.contextPath}/img'
    });

    tinymce.init({
        selector: '#add-rebuttal-content',
        min_width: 400,
        min_height: 300,
        plugins: [
            'advlist autolink autosave charmap hr link lists print preview ',
            ' wordcount visualblocks visualchars image imagetools',
            'table contextmenu emoticons template',
            'paste save searchreplace textcolor'
        ],
        contextmenu: "link image",
        imagetools_toolbar: "rotateleft rotateright | flipv fliph | editimage imageoptions",
        toolbar: 'insertfile undo redo | styleselect | forecolor backcolor bold italic underline \n\
        | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent \n\
        | link charmap image emoticons | preview save',
        images_upload_base_path: '${pageContext.request.contextPath}/img'
    });

//    $("#delete-debate").click(function(event){
//        event.preventDefault();
//        deleteDebate();
//    });
    
});

function challengeDebate(){
    var contentData = tinyMCE.get('add-challenge-content');
        $.ajax({
            url: 'challenge',
            type: 'POST',
            data: JSON.stringify({
                content: contentData.getContent(),
                position: false
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status){
            window.location.reload(true);
            $("#validationChallengeError").hide();
            window.onbeforeunload = function() {}; //keeps from asking if you want to leave page
            $('#add-challenge-content').val('');
        }).error(function (data, status) {
            var errorDiv = $("#validationChallengeError");
            errorDiv.empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                errorDiv.append(validationError.message);
                errorDiv.append("<br>");
                errorDiv.show();
            });
        });
}

function rebute(){
    var contentData = tinyMCE.get('add-rebuttal-content');
        $.ajax({
            url: 'rebuttal',
            type: 'POST',
            data: JSON.stringify({
                content: contentData.getContent(),
                type: 'refutation'
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status){
            window.location.reload(true);
            $("#validationRebuttalError").hide();
            window.onbeforeunload = function() {}; //keeps from asking if you want to leave page
            $('#add-rebuttal-content').val('');
        }).error(function (data, status) {
            var errorDiv = $("#validationRebuttalError");
            errorDiv.empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                errorDiv.append(validationError.message);
                errorDiv.append("<br>");
                errorDiv.show();
            });
        });
}

function votePro(){
    $.ajax({
        url: 'votePro',
        type: 'PUT',
        headers:{
            'Content-type': 'application/json'
        }
    }).success(function(data){
        window.location.reload(true);
    });
}

function voteCon(){
    $.ajax({
        url: 'voteCon',
        type: 'PUT',
        headers:{
            'Content-type': 'application/json'
        }
    }).success(function(data){
        window.location.reload(true);
    });
}

function getDebateEditDetails(id){
    $.ajax({
        url: 'deb/'+id,
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
        $("#edit-debate-category").val(debate.category);
        $("#edit-debate-pro-votes").val(debate.proVotes);
        $("#edit-debate-con-votes").val(debate.conVotes);
        $("input[name=publishDebate][value=" + debate.published + "]").prop('checked', true);
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
    var pro = $("#edit-debate-pro-votes").val();
    var con = $("#edit-debate-con-votes").val();
    var pub = $('input[name=publishDebate]:checked').val();
    
    var errorDiv = $("#validationDebateEditErrors");
    
    $.ajax({
        url: 'deb',
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
            category: cat,
            proVotes: pro,
            conVotes: con,
            published: pub
        })
    }).success(function(data){
        window.location.reload(true);
    }).error(function (data, status) {
        errorDiv.empty();
        $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            errorDiv.append(validationError.message);
            errorDiv.append("<br>");
            errorDiv.show();
        });
    });
}

//function deleteDebate(){
//    var id = $("#edit-debate-id").text();
//    
//    $.ajax({
//        url: 'deb/' +id,
//        type: 'DELETE'
//    }).success(function(){
//        alert("deleted");
//        
////        window.location.reload(true);
////        window.history.back();
////        window.location="redirect:/home";
////        
//        //reload doesn't work when back is used
//        //so deleted item still appears in home list until page is manually refreshed
//    });
//}
