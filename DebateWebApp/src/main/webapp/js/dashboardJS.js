
$(document).ready(function(){
    loadDebates();
    
    $('#submitDebate').click(function(event){
        event.preventDefault();
        var contentData = tinyMCE.get('addDebateContent');
        $.ajax({
            url: 'debate',
            type: 'POST',
            data: JSON.stringify({
                resolution: $('#addResolution').val(),
                content: contentData.getContent(),
                affirmativeUser: "debatinNotHatin",
                category: $('#addCategory').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status){
//            $("#validationErrors").hide();
            clearTable();
            loadDebates();
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
    $('#dashRows').empty();
}

function processDebateList(debates){
    
    clearTable();
    
    $.each(debates, function (index, debate){
        
        $('#dashRows').append($('<tr>')
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
        url : 'singleDebate/' + id,
        type: 'GET',
        headers: {
            'Accept' : 'application/json'
        }
    }).success(function(){
        window.location="/DebateWebApp/singleDebate/"+ id;
    });
}
