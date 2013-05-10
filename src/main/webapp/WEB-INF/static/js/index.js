$(document).ready(function() {
	$("#sendTitle").click(function() {
		var f = $("form");
		var  titleUrl = $("#title").val();
		var action = CONTEXT_PATH + titleUrl;
		
		$('form').attr('action', action);
		f.submit();
	});
});