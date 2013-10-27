console.log("RUNNING FROP JAVASCRIPT");
console.log($("#list_events_page"));
console.log($("#add_event_page"));
console.log($("#event_detail_page"));
console.log($("#edit_event_page"));

$(function() {
 // Handler for .ready() called.
	console.log("ready");

	//Bind to the create so the page gets updated with the listing
//	$("#list_events_page").bind("pagebeforeshow",function(){//event, ui){
	$(document).on("pagebeforeshow", "#list_events_page", function(event, ui) {
		console.log("pagebeforeshow");
	
		//Remove the old rows
		$( ".event_list_row" ).remove();
		
		//JQuery Fetch The New Ones
		$.ajax({
			url: "api/events",
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(data);
	        	//Create The New Rows From Template
	        	$( "#event_list_row_template" ).tmpl( data ).appendTo( "#events_list" );
	        },
	        error: ajaxError
		});
		
		$("#events_list").listview("refresh");
	});
	
	//Bind the add event page clear text
//	$("#add_event_page").bind("pagebeforeshow", function() {
	$(document).on("pagebeforeshow", "#add_event_page", function(event, ui) {
		console.log("Add Event Page");
		$("#add_event_title")[0].value = "";
//		$("#add_event_date")[0].value = "mm/dd/yyyy";
//		$("#add_event_org_id")[0].value = "";
		$("#add_event_foursquare")[0].value = "";
		$("#add_event_address")[0].value = "";
//		$("#add_event_start_time")[0].value = "";
//		$("#add_event_end_time")[0].value = "";
		$("#add_event_summary")[0].value = "";
		$("#add_event_type")[0].value = "";
		$("#add_event_special_notes")[0].value = "";
//		$("#add_event_alcohol")[0].value = "";
	});
		
	//Bind the add event page button
//	$("#add_event_button").bind("click", function() {
	$(document).on("pagebeforeshow", "#add_event_button", function(event, ui) {
		console.log("Add Event Button");
		$.ajax({
			url: "api/events",
			dataType: "json",
	        async: false,
	        // TODO
			data: {
				"title": $("#add_event_title")[0].value,
				"date": '2013-10-31',//$("#add_event_date")[0].value,
				"org_id": $("#add_event_org_id")[0].value,
				"foursquare": $("#add_event_foursquare")[0].value,
				"address": $("#add_event_address")[0].value,
				"start_time": '2013-10-31T21:00:00',//$("#add_event_start_time")[0].value,
				"end_time": '2013-11-01T03:30:00',//$("#add_event_end_time")[0].value,
				"summary": $("#add_event_summary")[0].value,
				"type": $("#add_event_type")[0].value,
				"special_notes": $("#add_event_special_notes")[0].value,
				"alcohol": $("#add_event_alcohol")[0].value
			},
			type: "POST",
	        error: ajaxError
		});
	});
		
	//Bind the detail page init text
//	$("#event_detail_page").bind("pagebeforeshow", function() {
	$(document).on("pagebeforeshow", "#event_detail_page", function(event, ui) {
		console.log("Event Detail Page");
		var event_id = $.url().fparam("event_id");
		
		//Remove the old rows
		$( ".event_detail_row" ).remove();
		
		//Instead of passing around in JS I am doing AJAX so direct links work
		//JQuery Fetch The Event
		$.ajax({
			url: "api/events/"+event_id,
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(data);
				data.ALCOHOL = (data.ALCOHOL == 1) ? "Yes" : "No";
	       		$( "#event_detail_template" ).tmpl( data ).appendTo( "#event_detail" );
	       		$( "#detail_head_title" )[0].innerHTML = data.DATE + " - " + data.TITLE;
	        },
	        error: ajaxError
		});
	});
	
	//Bind the edit page init text
//	$("#edit_event_page").bind("pagebeforeshow", function() {
	$(document).on("pagebeforeshow", "#edit_event_page", function(event, ui) {
		console.log("Edit Event Page");
		var event_id = $.url().fparam("event_id");
		
		//Instead of passing around in JS I am doing AJAX so direct links work
		//JQuery Fetch The Event
		$.ajax({
			url: "api/events/"+event_id,
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(data);
	       		$("#edit_event_text")[0].value = data.event;
	        },
	        error: ajaxError
		});
	});
	
	//Bind the edit page save button
//	$("#save_button").bind("click", function() {
	$(document).on("pagebeforeshow", "#save_button", function(event, ui) {
		console.log("Save Button");
		var event_id = $.url().fparam("event_id");
		$.ajax({
			url: "api/events/"+event_id,
			dataType: "json",
	        async: false,
			data: {"eventText": $("#edit_event_text")[0].value},
			headers: {"X-HTTP-Method-Override": "PUT"},
			type: "POST",
	        error: ajaxError
		});
	});
	
	//Bind the edit page remove button
//	$("#remove_button").bind("click", function() {
	$(document).on("pagebeforeshow", "#remove_button", function(event, ui) {
		console.log("Remove Button");
		var event_id = $.url().fparam("event_id");
		$.ajax({
			url: "api/events/"+event_id,
			dataType: "json",
	        async: false,
			type: "DELETE",
	        error: ajaxError
		});
	});
	
	//Cleanup of URL so we can have better client URL support
//	$("#edit_event_page").bind("pagehide", function() {
	$(document).on("pagebeforeshow", "#edit_event_page", function(event, ui) {
		$(this).attr("data-url",$(this).attr("id"));
		delete $(this).data()["url"];
	});

});

/******************************************************************************/

function ajaxError(jqXHR, textStatus, errorThrown){
	console.log("ajaxError "+textStatus+" "+errorThrown);
	$("#error_message").remove();
	$("#error_message_template").tmpl( {errorName: textStatus, errorDescription: errorThrown} ).appendTo( "#error_dialog_content" );
	$.mobile.changePage($("#error_dialog"), {
		transition: "pop",
		reverse: false,
		changeHash: false
	});
}
