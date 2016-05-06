$(document).ready(

function() {



});

function validateForm() {

	var userid = $("#userid").val();
	var password = $("#password").val();
	
	if (userid == "") {
		alert("Username cannot be empty");
		return false;
	} else if (password == "") {
		alert("Password cannot be empty");
		return false;
	}
	return true;
}