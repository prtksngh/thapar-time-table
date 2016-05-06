$(document)
		.ready(

				function() {

					$("#content").hide();
					$("#display")
							.click(
									function(event) {
										$("#content").show();
										var group = $("#tutgroup").val();
										var year = $("#year").val();
										//console.log(selectedFaculty);
										$
												.ajax({

													type : "POST",
													url : "UgData",
													dataType : "json",
													data : {
														groupid : group,
														year : year
													},
													success : function(data) {

														var content = " ";
														content = content
																.concat("<table class='table table-bordered table-hover center_cells'>");
														content = content
																.concat("<tr><th>DAY</th><th>8-9</th><th>9-10</th><th>10-11</th><th>11-12</th><th>12-13</th><th>13-14</th><th>14-15</th><th>15-16</th><th>16-17</th><th>17-18</th></tr>");
														content = content
																.concat("<tr><td>MONDAY</td>");
														for ( var i = 0; i < data.monday.length; i++) {
															var temp = data.monday;
															content = content
																	.concat("<td>"
																			+ temp[i].subject
																			+ "<br/>"
																			+ temp[i].ltp
																			+ "<br/>"
																		    + temp[i].teacher
																			+ "<br/>"
																			+ temp[i].room
																			+ "</td>");

														}
														content = content
																.concat("</tr>");
														content = content
																.concat("<tr><td>TUESDAY</td>");

														for ( var i = 0; i < data.monday.length; i++) {
															var temp = data.tuesday;
															content = content
																	.concat("<td >"
																			+ temp[i].subject
																			+ "<br/>"
																			+ temp[i].ltp
																			+ "<br/>"
																			+ temp[i].teacher
																			+ "<br/>"
																			+ temp[i].room
																			+ "</td>");
														}
														content = content
																.concat("</tr>");

														content = content
																.concat("<tr><td>WEDNESDAY</td>");

														for ( var i = 0; i < data.monday.length; i++) {
															var temp = data.wednesday;
															content = content
																	.concat("<td >"
																			+ temp[i].subject
																			+ "<br/>"
																			+ temp[i].ltp
																			+ "<br/>"
																			+ temp[i].teacher
																			+ "<br/>"
																			+ temp[i].room
																			+ "</td>");
														}
														content = content
																.concat("</tr>");

														content = content
																.concat("<tr><td>THURSDAY</td>");

														for ( var i = 0; i < data.monday.length; i++) {
															var temp = data.thursday;
															content = content
																	.concat("<td >"
																			+ temp[i].subject
																			+ "<br/>"
																			+ temp[i].ltp
																			+ "<br/>"
																			+ temp[i].teacher
																			+ "<br/>"
																			+ temp[i].room
																			+ "</td>");
														}
														content = content
																.concat("</tr>");

														content = content
																.concat("<tr><td>FRIDAY</td>");

														for ( var i = 0; i < data.monday.length; i++) {
															var temp = data.friday;
															content = content
																	.concat("<td >"
																			+ temp[i].subject
																			+ "<br/>"
																			+ temp[i].ltp
																			+ "<br/>"
																			+ temp[i].teacher
																			+ "<br/>"
																			+ temp[i].room
																			+ "</td>");
														}
														content = content
																.concat("</tr>");

														content = content
																.concat("</table>");
														$("#content").empty();
														$("#content").append(
																$(content));

													}
												});
									});

				});
