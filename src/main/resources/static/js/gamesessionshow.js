/**
 * JavaScript for gamesessionshow.html
 */


//Timer stuff
	//document.getElementById('#timeData').style.display = "none";

	var startTime = $("startTime").val();

	$('.timer').countid({
		clock: true,
		dateTplElapsed: "%H:%M:%S"
	});
	
	/*
	$("#timeData").bind("DOMSubtreeModified",function(){
		//$('#timeDisplay').html($("#timeData").text());
		sendUpdateTime();
	});
	*/
	
	/*<![CDATA[*/
		
		if ($("#userIsCreator").val() == "true") {
			window.setInterval(sendUpdateTime, 1000);
		}
    /*]]>*/
	//window.setInterval(sendUpdateTime, 1000);


    
//Tabs Initialization
$(document).ready(function() {
	$('[data-tabs-role="tabs"]').tabs();

	$(window).on('change.tabs', function(event) {
		event.preventDefault();
		console.log('change');
	});
});
	
    
var gameSessionId = $('#gameSessionId').val();

var stompClient = null;
var value = null;

if (window.addEventListener) { // Mozilla, Netscape, Firefox
    window.addEventListener('load', WindowLoad, false);
} else if (window.attachEvent) { // IE
    window.attachEvent('onload', WindowLoad);
}

function WindowLoad(event) {
    //alert("Another onload script");
    console.log('Calling init..');
    initSockJS();
    
   
}


function initPoints() {
	value = -1;
	console.log("Sending to initPoints..");
	sendUpdatePoints(); 
}



$('.pointbutton').click(function(){
    value = $(this).val();
});


$("#pointsForm").submit(function (event) {

    //stop submit the form, we will post it manually.
    event.preventDefault();
    
    sendUpdatePoints(); 
});

$("#descriptionForm").submit(function (event) {

    //stop submit the form, we will post it manually.
    event.preventDefault();
   
    //fire_ajax_submit();
    sendUpdateTask(); 
});

$("#voteOperationForm").submit(function (event) {
    //stop submit the form, we will post it manually.
    event.preventDefault();
});

$('#btnShowVotes').click(function(){
    sendShowVotes();
});

$('#btnClearVotes').click(function(){
    sendClearVotes();
});


function initSockJS() {
	
	 var socket = new SockJS("/sockjs-websocket");
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        //setConnected(true);
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/topic/updatepoints', function (response) {
	            //showResponse(JSON.parse(response.body).content);
	        	updateHiddenPoints((JSON.parse(response.body)));
	        });
	        
	        stompClient.subscribe('/topic/updatetask', function (response) {
	            //showResponse(JSON.parse(response.body).content);
	        	updateTask((JSON.parse(response.body)));
	        });
	        
	        stompClient.subscribe('/topic/showvotes', function (response) {
	            //showResponse(JSON.parse(response.body).content);
	        	showVotes((JSON.parse(response.body)));
	        });
	        
	        stompClient.subscribe('/topic/clearvotes', function (response) {
	            //showResponse(JSON.parse(response.body).content);
	        	updateHiddenPoints((JSON.parse(response.body)));
	        });
	        
	        stompClient.subscribe('/topic/updatetime/' + gameSessionId, function (response) {
	            //showResponse(JSON.parse(response.body).content);
	        	updateTime((JSON.parse(response.body)));
	        });
	        
	        initPoints();
	    });
	
	    
}

function fire_ajax_submit() {
	
	var request = {}
	//request["user"] = ${"passedUser"};
	request["points"] = $("#btnPoints").val();
	request["userId"] = $("#currentUserId").val();
	request["gameSessionId"] = $("#gameSessionId").val();
	
	 $.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/user/updatepoints",
	        data: JSON.stringify(request),
	        dataType: 'json',
	        cache: false,
	        timeout: 600000,
	        success: function (data) {

	            var json = "<h4>Ajax Response</h4><pre>"
	                + JSON.stringify(data, null, 4) + "</pre>";
	            $('#pointresponse').html(json);

	            console.log("SUCCESS : ", data);
	            //$("#btn-search").prop("disabled", false);

	        },
	        error: function (e) {

	            var json = "<h4>Ajax Response</h4><pre>" + e.responseText + "</pre>";
	            $('#pointresponse').html(json);

	            console.log("ERROR : ", e);
	            //$("#btn-search").prop("disabled", false);

	        }
	    });

	
}



function sendUpdatePoints() {
	/*<![CDATA[*/
	console.log("SENDING UpdatePoints..");
	
	var request = {}
	//request["user"] = ${"passedUser"};
	request["points"] = value;
	request["userId"] = $("#currentUserId").val();
	request["gameSessionId"] = $("#gameSessionId").val();
	request["showVotes"] = $("#gameSessionShowVotes").val();
		        
	stompClient.send("/msg/updateallpoints", {}, JSON.stringify(request));
	/*]]>*/
}

function updateHiddenPoints(response) {
	/*<![CDATA[*/    	
		
	var output = "<thead><tr><th>User</th><th>Points</th></tr></thead>";	
	
	for (var i = 0; i < response.usernames.length; i++) {
		if (response.showVotes) {
			output += "<tr class=\"tr-body\"><td>" + response.usernames[i] + "</td><td>" + response.points[i] + "</td></tr>"
		} else {
			output += "<tr class=\"tr-body\"><td>" + response.usernames[i] + "</td><td><div background-color=\"#ffffff\" color=\"#ffffff\">       </div></td></tr>";
		}			
	}	
	
	
	//So this hidden input val will update with the correct value.. (for some reason it would not update
	//for all sessions)
	$('#gameSessionShowVotes').val(response.showVotes);
    $('.pointresponse').html(output);

    /*]]>*/
}




function sendUpdateTask() {
	console.log("SENDING updateTask..");
	
	var request = {}
	request["gameSessionId"] = $("#gameSessionId").val();
	request["taskDescription"] = $("#task").val();
	        
	stompClient.send("/msg/updatetask", {}, JSON.stringify(request));
}

function updateTask(response) {
	$('#task').val(response.taskDescription);
}


function sendShowVotes() {
	console.log("SENDING showVotes..");	
	
	var request = {}
	request["gameSessionId"] = $("#gameSessionId").val();
	request["showVotes"] = "true";
	
	stompClient.send("/msg/showvotes", {}, JSON.stringify(request));
}

function sendClearVotes() {
	console.log("SENDING clearVotes..");	
	
	var request = {}
	request["gameSessionId"] = $("#gameSessionId").val();
	request["showVotes"] = "false";
	
	stompClient.send("/msg/clearvotes", {}, JSON.stringify(request));
}


function showVotes(response) {
	/*<![CDATA[*/
    
	var output = "<thead><tr><th>User</th><th>Points</th></tr></thead>";	
	
    for (var i = 0; i < response.usernames.length; i++) {
		output += "<tr class=\"tr-body\"><td>" + response.usernames[i] + "</td><td>" + response.points[i] + "</td></tr>"
	}
	
    $('.pointresponse').html(output);

    /*]]>*/
}

function sendUpdateTime() {
	console.log("SENDING updateTime..");
	
	
	var request = {}
	request["gameSessionId"] = $("#gameSessionId").val();
	request["timePassed"] = $("#timeData").text();
	
	stompClient.send("/msg/updatetime/" + gameSessionId, {}, JSON.stringify(request));
}

function updateTime(response) {
	/*<![CDATA[*/
    
	var output = "";
	output = response.timePassed;    
	
    $('#timeDisplay').html(output);
    /*]]>*/
}