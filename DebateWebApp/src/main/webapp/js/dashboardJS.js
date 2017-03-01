
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
//            window.onbeforeunload = function() {};
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
    $('#dvdRows').empty();
}

function processDebateList(debates){
    
    clearTable();
    
    var debateRows = $('#debateRows');
    
    $.each(debates, function (index, debate){
        
        var resField = $("<td>");
        var userField = $("<td>");
        var dateField = $("<td>");
        
        resField.text(debate.resolution);
        userField.text(debate.affirmativeUser);
        dateField.text(debate.date);
        
        var dRow = $("<tr>");
        dRow.append(resField);
        dRow.append(userField);
        dRow.append(dateField);
        
        debateRows.append(dRow);
    });
}
