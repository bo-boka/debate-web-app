
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
    $.ajax({
        url: 'debate',
        type: 'POST',
        data: JSON.stringify({
            resolution: $('#addResolution').val(),
            content: contentData.getContent(),
            category: $('#addCategory').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status){
//            $("#validationErrors").hide();
        $('#dashRows').empty();
        loadUserDebates();
        window.onbeforeunload = function() {};
        $('#addResolution').val('');
        contentData.setContent('');
        $('#addCategory').val('');
//        }).error(function (data, status){
//            var errorDiv = $("#validationErrors");
//            errorDiv.empty();
//            $.each(data.responseJSON.fieldErrors, function (index, validationError){
//                errorDiv.append(validationError.message);
//                errorDiv.append("<br>");
//                errorDiv.show();
//            });
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
//            $("#validationErrors").hide();
        $('#add-mod-first-name').val('');
        $('#add-mod-last-name').val('');
        $('#add-mod-email').val('');
        $('#add-mod-username').val('');
        $('#add-mod-password').val('');
        
//        }).error(function (data, status){
//            var errorDiv = $("#validationErrors");
//            errorDiv.empty();
//            $.each(data.responseJSON.fieldErrors, function (index, validationError){
//                errorDiv.append(validationError.message);
//                errorDiv.append("<br>");
//                errorDiv.show();
//            });
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
    });
}


