console.log('RUNNING COMMENT JAVASCRIPT');
console.log($('#list_comments_page'));
console.log($('#add_comment_page'));
console.log($('#edit_comment_page'));

$(function() {
 // Handler for .ready() called.
	console.log('ready');

	//Bind to the create so the page gets updated with the listing
	$('#list_comments_page').bind('pagebeforeshow',function(event, ui){
		console.log('pagebeforeshow');
	
		//Remove the old rows
		$( ".comment_list_row" ).remove();
		
		//JQuery Fetch The New Ones
		$.ajax({
			url: "api/comment",
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(data);
	        	//Create The New Rows From Template
	        	$( "#comment_list_row_template" ).tmpl( data ).appendTo( "#comments_list" );
	        },
	        error: ajaxError
		});
		
		$('#comments_list').listview('refresh');
	});
	
	//Bind the add page clear text
	$('#add_comment_page').bind('pagebeforeshow', function() {
		console.log("Add Comment Page");
		$('#add_comment_text')[0].value = "";
	});
		
	//Bind the add page button
	$('#add_button').bind('click', function() {
		console.log("Add Button");
		$.ajax({
			url: "api/comment",
			dataType: "json",
	        async: false,
			data: {'commentText': $('#add_comment_text')[0].value},
			type: 'POST',
	        error: ajaxError
		});
	});
		
	//Bind the edit page init text
	$('#edit_comment_page').bind('pagebeforeshow', function() {
		console.log("Edit Comment Page");
		var comment_id = $.url().fparam("comment_id");
		
		//Instead of passing around in JS I am doing AJAX so direct links work
		//JQuery Fetch The Comment
		$.ajax({
			url: "api/comment/"+comment_id,
			dataType: "json",
	        async: false,
	        success: function(data, textStatus, jqXHR) {
				console.log(data);
	       		$('#edit_comment_text')[0].value = data.comment;
	        },
	        error: ajaxError
		});
	});
	
	//Bind the edit page save button
	$('#save_button').bind('click', function() {
		console.log("Save Button");
		var comment_id = $.url().fparam("comment_id");
		$.ajax({
			url: "api/comment/"+comment_id,
			dataType: "json",
	        async: false,
			data: {'commentText': $('#edit_comment_text')[0].value},
			headers: {'X-HTTP-Method-Override': 'PUT'},
			type: 'POST',
	        error: ajaxError
		});
	});
	
	//Bind the edit page remove button
	$('#remove_button').bind('click', function() {
		console.log("Remove Button");
		var comment_id = $.url().fparam("comment_id");
		$.ajax({
			url: "api/comment/"+comment_id,
			dataType: "json",
	        async: false,
			type: 'DELETE',
	        error: ajaxError
		});
	});
	
	//Cleanup of URL so we can have better client URL support
	$('#edit_comment_page').bind('pagehide', function() {
		$(this).attr("data-url",$(this).attr("id"));
		delete $(this).data()['url'];
	});

});

/******************************************************************************/

function ajaxError(jqXHR, textStatus, errorThrown){
	console.log('ajaxError '+textStatus+' '+errorThrown);
	$('#error_message').remove();
	$("#error_message_template").tmpl( {errorName: textStatus, errorDescription: errorThrown} ).appendTo( "#error_dialog_content" );
	$.mobile.changePage($('#error_dialog'), {
		transition: "pop",
		reverse: false,
		changeHash: false
	});
}