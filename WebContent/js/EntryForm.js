/*$("#teachers").select2({
	    placeholder: "Select A Teacher",
	    allowClear: true
	});*/
$(document).ready(function() {
	$("#teachers").select2({
		placeholder : "Select A Teacher",
		allowClear : true
	});
	$("#subjects").select2({
		placeholder : "Select A Subject",
	});
	$("#timeslots").select2({
		placeholder : "Select A Timeslot",
		allowClear : true
	});
	$("#room").select2({
		placeholder : "Select A Room",
		allowClear : true
	});
	$("#LTP").select2({
		placeholder : "Select A Room",
		allowClear : true
	});
	$("#day").select2({
		placeholder : "Select A Day",
		allowClear : true
	});
	$("#tutgroupst").select2({
		placeholder : "Select A Group",
		allowClear : true
	});
	$("#tutgroupend").select2({
		placeholder : "Select A Group",
		allowClear : true
	});

	$("#reset").click(function(){
	  resetAll();
	});

});
function resetAll()
{
	console.log("start");
	$("#teachers option:selected").removeAttr("selected");
	$("#subjects  option:eq(0)").prop("selected", true); 	
	$("#timeslots  option:eq(0)").prop("selected", true); 
	$("#room  option:eq(0)").prop("selected", true); 
	$("#LTP  option:eq(0)").prop("selected", true); 
	$("#day  option:eq(0)").prop("selected", true); 
	$("#tutgroupst  option:eq(0)").prop("selected", true); 
	$("#tutgroupend  option:eq(0)").prop("selected", true); 
console.log("end");
}
function validateForm() {
	//alert("started");
	var bool = true;
	//alert("started a");
	var teachers = document.getElementById("teachers").value;
	//alert("started b");
	var subjects = document.getElementById("subjects").value;
	//alert("started c");
	var timeslots = document.getElementById("timeslots").value;
	//alert("started d");
	var day = document.getElementById("day").value;
	//alert("started e");
	var room = document.getElementById("room").value;
	//alert("started f");
	var tutgroupst = document.getElementById("tutgroupst").value;
	//alert("started h");
	var ltp = document.getElementById("LTP").value;
	console.log(ltp);
	//alert("started1");
	if (teachers == null || teachers == "") {
		document.getElementById("teacherEmpty").style.display = "inline";
		bool = false;
	}
	//alert("started2");
	if (subjects == null || subjects == "") {
		document.getElementById("subjectEmpty").style.display = "inline";
bool=false;
	}
	//alert("started3");
	if (timeslots == null || timeslots == "") {
		document.getElementById("timeslotEmpty").style.display = "inline";
		bool = false;
	}
	//alert("started4");
	if (day == null || day == "") {
		document.getElementById("dayEmpty").style.display = "inline";
		bool = false;
	}
	//alert("started5");
	if (room == null || room == "") {
		document.getElementById("roomEmpty").style.display = "inline";
		bool = false;
	}
	//alert("started6");
	if (tutgroupst == null || tutgroupst == "") {
		document.getElementById("tutgroupstEmpty").style.display = "inline";
		bool = false;
	}
	//alert("started8");
	if (ltp == null || ltp == "") {
		document.getElementById("LtpEmpty").style.display = "inline";
		bool = false;
	}
	// alert("started9");

	if (bool == false) {
		document.getElementById("warn").style.display = "inline";
		return false;
	}
	return bool;
}


