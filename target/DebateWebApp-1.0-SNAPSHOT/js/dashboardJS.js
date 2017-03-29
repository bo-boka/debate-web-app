
$(document).ready(function(){
    loadUserDebates();
    loadUnpubDebates();
    
    $('#submitDebate').click(function(event){
        event.preventDefault();
        addDebate();
    });
    
    $('#add-moderator-button').click(function(event){
        event.preventDefault();
        addModerator();
    });
    
    $("#edit-user-modal").on('show.bs.modal', function(event){
        var element = $(event.relatedTarget);
        var username = element.data('user-name');
        getUserEditDetails(username);
    });
    
    $("#edit-user").click(function(event){
        event.preventDefault();
        editUser();
    });
    
    tinymce.init({
                selector: '#addDebateContent',
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
    
});

function loadUserDebates(){
    
    $.ajax({
        url: 'userDebates',
        type: 'GET'
    }).success(function (data){
        processUserDebateList(data);
    });
}

function loadUnpubDebates(){
    $.ajax({
        url: 'unpubDebates',
        type: 'GET'
    }).success(function (data){
        processUnpubDebateList(data);
    });
}


function processUserDebateList(debates){
    
    $('#dashRows').empty();
    
    $.each(debates, function (index, debate){
        
        $('#dashRows').append($('<tr>')
                .append($('<td>').append($('<a>').attr({
                    'onclick' : 'goToDebate(' +debate.id+ ')'}).text(debate.resolution)))
                .append($('<td>').text(debate.affirmativeUser))
                .append($('<td>').text(debate.date))

                );
    });
}

function processUnpubDebateList(debates){
    
    $('#unpubRows').empty();
    
    $.each(debates, function (index, debate){
        
        $('#unpubRows').append($('<tr>')
                .append($('<td>').append($('<a>').attr({
                    'onclick' : 'goToDebate(' +debate.id+ ')'}).text(debate.resolution)))
                .append($('<td>').text(debate.affirmativeUser))
                .append($('<td>').text(debate.date))

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
        window.location="/DebateWebApp/debate/"+ id;
    });
}

function addDebate(){
    var contentData = tinyMCE.get('addDebateContent');
    var errorDiv = $("#validationErrors");
    $.ajax({
        url: 'debate',
        type: 'POST',
        data: JSON.stringify({
            resolution: $('#addResolution').val(),
            content: contentData.getContent(),
            category: $('#addCategory').val(),
            affirmativeUser:  'user', //dummie data to pass validation
            date: 'date', //same
            status: 'intro' //same
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status){
        errorDiv.empty();
        errorDiv.hide();
        $('#dashRows').empty();
        loadUserDebates();
        window.onbeforeunload = function() {};
        $('#addResolution').val('');
        contentData.setContent('');
        $('#addCategory').val('');
    }).error(function (data, status){
        errorDiv.empty();
        errorDiv.show();
        $.each(data.responseJSON.fieldErrors, function (index, validationError){
            errorDiv.append(validationError.message);
            errorDiv.append("<br>");
        });
    });
}

function addModerator(){
    $.ajax({
        url: 'mod',
        type: 'POST',
        data: JSON.stringify({
            firstName: $('#add-mod-first-name').val(),
            lastName: $('#add-mod-last-name').val(),
            email: $('#add-mod-email').val(),
            username: $('#add-mod-username').val(),
            password: $('#add-mod-password').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status){
        
        $('#add-mod-first-name').val('');
        $('#add-mod-last-name').val('');
        $('#add-mod-email').val('');
        $('#add-mod-username').val('');
        $('#add-mod-password').val('');
        
        }).error(function (data, status){
            var errorDiv = $("#validationErrorsMod");
            errorDiv.empty();
            errorDiv.show();
            $.each(data.responseJSON.fieldErrors, function (index, validationError){
                errorDiv.append(validationError.message);
                errorDiv.append("<br>");
            });
    });
}

function getUserEditDetails(name){
    $.ajax({
        url: 'user/'+ name,
        type: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    }).success(function(user){
        $('#edit-user-id').text(user.id);
        $('#edit-first-name').val(user.firstName);
        $('#edit-last-name').val(user.lastName);
        $('#edit-email').val(user.email);
        $('#edit-username').val(user.username);
        $('#edit-password').val(user.password);
    });
}

function editUser(){
    var id = $("#edit-user-id").text();
    
    $.ajax({
        url: 'user',
        type: 'PUT',
        headers:{
            'Content-type': 'application/json'
        },
        'dataType' : 'json',
        data: JSON.stringify({
            id: id,
            firstName: $('#edit-first-name').val(),
            lastName: $('#edit-last-name').val(),
            email: $('#edit-email').val(),
            username: $('#edit-username').val(),
            password: $('#edit-password').val()
        })
    }).success(function(data){
        window.location.reload(true);
    }).error(function (data, status){
            var errorDiv = $("#validationEditUserErrors");
            errorDiv.empty();
            errorDiv.show();
            $.each(data.responseJSON.fieldErrors, function (index, validationError){
                errorDiv.append(validationError.message);
                errorDiv.append("<br>");
            });
    });
}


