$(document)
		.ready(

				function() {
					console.log("hello");
					$("#content").hide();
					$("#display")
							.click(
									function(event) {
										$("#content").show();
										var day = $("#dayid").val();
										var timeslot = $("#slot").val();
										console.log(day + " " + timeslot);
										$
												.ajax({

													type : "POST",
													url : "DaySlotWiseData",
													dataType : "json",
													data : {
														dayid : day,
														slotid : timeslot
													},
													success : function(data) {
														console.log("success");
														var content = " ";
														content = content
																.concat("<table class='table table-bordered table-hover'>");
														content = content
																.concat("<tr><th>TEACHER</th><th>LTP</th><th>GROUP</th><th>ROOM</th><th>SLOT</th></tr>");
														for ( var i = 0; i < data.entries.length; i++) {
															var entry = data.entries[i];
															var row = "<tr><td>"
																	+ entry.teacher
																	+ "</td><td>"
																	+ entry.ltp
																	+ "</td><td>"
																	+ entry.group
																	+ "</td><td>"
																	+ entry.room
																	+ "</td><td>"
																	+ entry.slot
																	+ "</td></tr>";
															content = content
																	.concat(row);
														}
														$("#content").empty();
														$("#content").append(
																$(content));

													}

												});
									});
				});
