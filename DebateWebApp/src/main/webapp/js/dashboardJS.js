
$(document).ready(function(){
    loadUserDebates();
    
    $('#submitDebate').click(function(event){
        event.preventDefault();
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
            clearTable();
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

function clearTable(){
    $('#dashRows').empty();
}

function processUserDebateList(debates){
    
    clearTable();
    
    $.each(debates, function (index, debate){
        
        $('#dashRows').append($('<tr>')
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
