console.log("RUNNING FROP JAVASCRIPT");
console.log($("#event_list_page"));
console.log($("#event_add_page"));
console.log($("#event_detail_page"));
console.log($("#event_edit_page"));

console.log($("#badge_list_page"));
console.log($("#badge_detail_page"));

$(function() {
 // Handler for .ready() called.
	console.log("ready");
	
/* -- BADGE FUNCTIONS -- */

	//Bind to the create so the list badges page gets updated with the listing
	$(document).on("pagebeforeshow", "#badge_list_page", function(event, ui) {
		console.log("pagebeforeshow");
	
		//Remove the old rows
		$( ".badge_list_row" ).remove();
		
		//JQuery Fetch The New Ones
		$.ajax({
			url: "api/badges",
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(data);
	        	//Create The New Rows From Template
	        	$( "#badge_list_row_template" ).tmpl( data ).appendTo( "#badge_list" );
	        },
	        error: ajaxError
		});
		
		$("#badge_list").listview("refresh");
	});
		
	//Bind the badge detail page init text
	$(document).on("pagebeforeshow", "#badge_detail_page", function(event, ui) {
		console.log("Badge Detail Page");
		var badge_id = $.url().fparam("badge_id");
		
		//Remove the old rows
		$( ".badge_detail_row" ).remove();
		
		//Instead of passing around in JS I am doing AJAX so direct links work
		//JQuery Fetch The Event
		$.ajax({
			url: "api/badges/"+badge_id,
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(data);
	       		$( "#badge_detail_template" ).tmpl( data ).appendTo( "#badge_detail" );
	       		$( "#badge_head_title" )[0].innerHTML = data.NAME;
	        },
	        error: ajaxError
		});
	});

/* -- GOV ORGS FUNCTIONS -- */

	// Bind to the create so the list gov orgs page gets updated with the listing
	$(document).on("pagebeforeshow", "#gov_orgs_list_page", function(event, ui) {
		console.log("pagebeforeshow");
	
		//Remove the old rows
		$( ".gov_orgs_list" ).remove();
		
		//JQuery Fetch The New Ones
		$.ajax({
			url: "api/gov_orgs",
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(data);
	        	//Create The New Rows From Template
	        	$( "#gov_orgs_list_row_template" ).tmpl( data ).appendTo( "#gov_orgs_list" );
	        },
	        error: ajaxError
		});
		
		$("#gov_orgs_list").listview("refresh");
	});

/* -- EVENT FUNCTIONS -- */

	//Bind to the create so the list events page gets updated with the listing
//	$("#list_events_page").bind("pagebeforeshow",function(){//event, ui){
	$(document).on("pagebeforeshow", "#event_list_page", function(event, ui) {
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
	        	$( "#event_list_row_template" ).tmpl( data ).appendTo( "#event_list" );
	        },
	        error: ajaxError
		});
		
		$("#event_list").listview("refresh");
	});
	
	//Bind the add event page clear text
	$(document).on("pagebeforeshow", "#event_add_page", function(event, ui) {
		console.log("Add Event Page");
		$("#event_add_text")[0].value = "";
	});
		
	//Bind the add event page button
	$(document).on("pagebeforeshow", "#event_add_page_button", function(event, ui) {
		console.log("Add Button");
		$.ajax({
			url: "api/events",
			dataType: "json",
	        async: false,
			data: {"eventText": $("#add_event_text")[0].value}, // TODO not sufficient (needs more fields and stuff)
			type: "POST",
	        error: ajaxError
		});
	});
		
	//Bind the event detail page init text
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
	       		$( "#event_detail_head_title" )[0].innerHTML = data.DATE + " - " + data.TITLE;
	       		$( "#event_detail_title" )[0].innerHTML = data.TITLE;
	       		$( "#event_detail_date" )[0].innerHTML = data.DATE;
	       		$( "#event_detail_type_id" )[0].innerHTML = "A " + data.TYPE + " Hosted by " + data.ORG_ID;
	       		$( "#event_detail_times" )[0].innerHTML = data.START_TIME + " to " + data.END_TIME;
	       		$( "#event_detail_address" )[0].innerHTML = data.ADDRESS;
	       		$( "#event_detail_foursquare_link" )[0].setAttribute("href", "http://foursquare.com/v/$" + data.FOURSQUARE);
	       		$( "#event_detail_summary" )[0].innerHTML = data.SUMMARY;
	       		$( "#event_detail_alcohol" )[0].innerHTML = "Alcohol: " + data.ALCOHOL;
	       		$( "#event_detail_date_changed" )[0].innerHTML = "Date changed: " + data.DATE_CHANGED;
	        },
	        error: ajaxError
		});
	});
	
	//Bind the edit page init text
	$(document).on("pagebeforeshow", "#event_edit_page", function(event, ui) {
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
	       		$("#event_edit_text")[0].value = data.event; // TODO needs more fields
	        },
	        error: ajaxError
		});
	});
	
	//Bind the edit page save button
	$(document).on("pagebeforeshow", "#event_edit_save_button", function(event, ui) {
		console.log("Save Button");
		var event_id = $.url().fparam("event_id");
		$.ajax({
			url: "api/events/"+event_id,
			dataType: "json",
	        async: false,
			data: {"eventText": $("#event_edit_text")[0].value}, // TODO might need more fields
			headers: {"X-HTTP-Method-Override": "PUT"},
			type: "POST",
	        error: ajaxError
		});
	});
	
	//Bind the edit page remove button
	$(document).on("pagebeforeshow", "#event_edit_remove_button", function(event, ui) {
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
	$(document).on("pagebeforeshow", "#event_edit_page", function(event, ui) {
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
