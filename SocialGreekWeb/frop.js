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
	
// Global Variables
var event_id;

/* -- BADGE FUNCTIONS -- */

	//Bind to the create so the list badges page gets updated with the listing
	$(document).on("pagebeforeshow", "#badge_list_page", function(event, ui) {
		console.log("badge list page");
	
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
	$(document).on("pagebeforeshow", "#gov_org_list_page", function(event, ui) {
		console.log("gov org list page");
	
		//Remove the old rows
		$( ".gov_org_list" ).remove();
		
		//JQuery Fetch The New Ones
		$.ajax({
			url: "api/gov_orgs",
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(data);
	        	//Create The New Rows From Template
	        	$( "#gov_org_list_row_template" ).tmpl( data ).appendTo( "#gov_org_list" );
	        },
	        error: ajaxError
		});
		
		$("#gov_org_list").listview("refresh");
	});



/* -- ORGS FUNCTIONS -- */

	//Bind to the create so the list orgs page gets updated with the listing
	$(document).on("pagebeforeshow", "#org_list_page", function(event, ui) {
		console.log("Org List Page");
	
		//Remove the old rows
		$( ".org_list_row" ).remove();
		
		var url = "api/orgs";
		var gov_org = $.url().fparam("gov_org_id");
		if (gov_org > 0) {
			url = "api/gov_orgs/"+gov_org+"/orgs/1";
		}
		//JQuery Fetch The New Ones
		$.ajax({
			url: "api/orgs",
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(data);
	        	//Create The New Rows From Template
	        	$( "#org_list_row_template" ).tmpl( data ).appendTo( "#org_list" ); // TODO is there a way to check for nulls?
				gov_org = -1;
	        },
	        error: ajaxError
		});
		$("#org_list").listview("refresh");
	});
		
	//Bind the org detail page init text
	$(document).on("pagebeforeshow", "#org_detail_page", function(event, ui) {
		console.log("Org Detail Page");
		var org_id = $.url().fparam("org_id");
		var urls="api/orgs/"+org_id;
		
		//Remove the old rows
		$( ".org_detail_row" ).remove();
		
		//Instead of passing around in JS I am doing AJAX so direct links work
		//JQuery Fetch The Event
		$.ajax({
			url: "api/orgs/"+org_id,
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(urls);
				console.log(data);
	       		$( "#org_detail_nickname" )[0].innerHTML = "Nickname: " . data.NICKNAME;
	       		$( "#org_detail_letters" )[0].innerHTML = "Letters: ".data.LETTERS;
	       		$( "#org_detail_chapter" )[0].innerHTML = data.CHAPTER;
	       		$( "#org_detail_gov_org_id" )[0].innerHTML = data.GOV_ORG_ID;
	       		if (data.CUSTOM_PIC_URL && data.PIC_APPROVED==="true") { $( "#org_detail_custom_pic" )[0].innerHTML = data.CUSTOM_PIC_URL; }
	       		$( "#org_detail_type" )[0].innerHTML = data.TYPE;
	       		$( "#org_detail_focus" )[0].innerHTML = data.FOCUS;
	       		$( "#org_detail_year_founded" )[0].innerHTML = "Founded in " + data.YEAR_FOUNDED + ", Chapter Founded in " +  data.YEAR_CHAPTER_FOUNDED;
	       		$( "#org_detail_blurb" )[0].innerHTML = data.BLURB;
	       		$( "#org_detail_address" )[0].innerHTML = data.ADDRESS;
	       		$( "#org_detail_foursquare_link" )[0].setAttribute("href", "http://foursquare.com/v/" + data.FOURSQUARE);
	       		$( "#org_detail_homepage_url" )[0].setAttribute("href", data.HOMEPAGE_URL);
	       		$( "#org_detail_org_id" )[0].innerHTML = data.ORG_ID;
	       		$( "#org_detail_date_changed" )[0].innerHTML = data.DATE_CHANGED;
	        },
	        error: ajaxError
		});
	});
		
		
/* -- EVENT FUNCTIONS -- */

	//Bind to the create so the list events page gets updated with the listing
	$(document).on("pagebeforeshow", "#event_list_page", function(event, ui) {
		console.log("event list page");
	
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

	//Bind the event list row links
	$(document).on("click", ".event_list_row_link", function(event, ui) {
		console.log("Event List Row Link clicked");

		var target = event.target || event.srcElement;
		while (target && !target.id) {
		    target = target.parentNode;
		}

		// Substring: remove ID prefix to get event_id
		event_id = target.id.substring(20, target.id.length);
		console.log("New Event ID from clicked element:");
		console.log(event_id);
	});		

	//Bind the add event page clear text
	$(document).on("pagebeforeshow", "#event_add_page", function(event, ui) {
		console.log("Add Event Page");

		$("#event_add_title")[0].value = "";
		$("#event_add_date")[0].value = "mm/dd/yyyy";

		//Remove the org_id options
		$( ".event_add_org_id_row" ).remove();

		//JQuery Fetch The New org_id options
		$.ajax({
			url: "api/orgs",
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(data);
	        	//Create The New Rows From Template
	        	$( "#event_add_org_id_row_template" ).tmpl( data ).appendTo( "#event_add_org_id" );
	        },
	        error: ajaxError
		});
		
		$("#event_add_org_id").selectmenu("refresh");

		$("#event_add_foursquare")[0].value = "";
		$("#event_add_address")[0].value = "";
		$("#event_add_start_time")[0].value = "HH:MM";
		$("#event_add_end_time")[0].value = "HH:MM";
		$("#event_add_summary")[0].value = "";
		$("#event_add_type")[0].value = "";
		$("#event_add_special_notes")[0].value = "";

		$("#event_add_alcohol_yes").prop('checked', false).checkboxradio("refresh");
	});
		
	//Bind the add event page button
	$(document).on("click", "#event_add_page_submit_button", function(event, ui) {
		console.log("Add Event Button");

		$.ajax({
			url: "api/events",
			dataType: "json",
	        async: false,

			data: {
				'title': $('#event_add_title')[0].value,
				'date': '2013-10-31',//$('#event_add_date')[0].value,
				'org_id': '1',//$('#event_add_org_id')[0].value,
				'foursquare': $('#event_add_foursquare')[0].value,
				'address': $('#event_add_address')[0].value,
				'start_time': '2013-10-31T21:00:00',//$('#event_add_start_time')[0].value,
				'end_time': '2013-11-01T03:30:00',//$('#event_add_end_time')[0].value,
				'summary': $('#event_add_summary')[0].value,
				'type': $('#event_add_type')[0].value,
				'special_notes': $('#event_add_special_notes')[0].value,
				'alcohol': '1'//$('#event_add_alcohol_yes')[0].value
			},

			type: 'POST',
	        error: ajaxError
		});
	});		
		
	//Bind the event detail page init text
	$(document).on("pagebeforeshow", "#event_detail_page", function(event, ui) {
		console.log("event detail page");
		console.log("Current Event ID: ");
		console.log(event_id);

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
	        },
	        error: ajaxError
		});
	});
	
	//Bind the edit page init text
	$(document).on("pagebeforeshow", "#event_edit_page", function(event, ui) {
		console.log("Edit Event Page");
		event_id = $.url().fparam("event_id");
		
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
		event_id = $.url().fparam("event_id");
		$.ajax({
			url: "api/events/"+event_id,
			dataType: "json",
	        async: false,
			data: {"eventText": $("#event_edit_text")[0].value}, // TODO might need more fields
			headers: {"X-HTTP-Method-Override": "PUT"},
			type: "PUT",
	        error: ajaxError
		});
	});
	
	//Bind the edit page remove button
	$(document).on("pagebeforeshow", "#event_edit_remove_button", function(event, ui) {
		console.log("Remove Button");
		event_id = $.url().fparam("event_id");
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
