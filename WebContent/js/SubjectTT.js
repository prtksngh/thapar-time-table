$(document)
		.ready(

				function() {
					 $("#content").hide();
					$("#display")
							.click(
									function(event) {
										 $("#content").show();
										var selectedSubject = $(
												"#subjectSelector").val();
										console.log(selectedSubject);

										$
												.ajax({

													type : "POST",
													url : "SubjectWiseData",
													dataType : "json",
													data : {
														subject : selectedSubject
													},
													success : function(data) {
														$("#subName")
																.html(
																		"Subject Name : "
																				+ data.subname);
														$("#lecCredit")
																.html(
																		"Lecture Credits : "
																				+ data.lecture);
														$("#tutCredit")
																.html(
																		"Tutorial Credits : "
																				+ data.tutorial);
														$("#pracCredit")
																.html(
																		"Practical Credits : "
																				+ data.practical);

														$("#infoBody").empty();
														for ( var i = 0; i < data.teachers.length; i++) {

															var row = "<tr> "
																	+ "<td>"
																	+ data.teachers[i].name
																	+ "</td>"
																	+ "<td>"
																	+ data.teachers[i].lecture
																	+ "</td>"
																	+ "<td>"
																	+ data.teachers[i].tutorial
																	+ "</td>"
																	+ "<td>"
																	+ data.teachers[i].practical
																	+ "</td>"
																	+ "</tr>";

															$(row)
																	.appendTo(
																			"#infoBody");
														}
														// info ends
														var content = " ";
														$("#daywise").empty()
														for ( var i = 0; i < data.daywise.length; i++) {
                                                             
															var day = data.daywise[i];
															//console.log(content);
															content = content
																	.concat("<h2>"
																			+ day.name
																			+ "</h2>");
															content = content
																	.concat("<table class='table table-bordered table-hover'>");
															content = content
																	.concat("<tr><th>TEACHER</th><th>LTP</th><th>ROOM</th><th>SLOT</th></tr>");
															for ( var j = 0; j < day.entries.length; j++) {

																var entry = day.entries[j];
																console.log(entry.teacher);
																content = content
																		.concat("<tr><td>"
																				+ entry.teacher
																				+ "</td>"
																				+ "<td>"
																				+ entry.ltp
																				+ "</td>"
																				+ "<td>"
																				+ entry.room
																				+ "</td>"
																				+ "<td>"
																				+ entry.slot
																				+ "</td></tr>");

															}
															content=content.concat("</table>");

														}
                                                          console.log(content)
														$("#daywise").append($(content));

														
													}

												});
										return false;

									});
				});
