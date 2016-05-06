<%@page import="com.timetable.Database.*"%>

<% InfoPopulator info=new InfoPopulator();
   info.populateAll();
   String name=info.getTeacherName((String)session.getAttribute("tid"));
   session.setAttribute("teacherList", info.teacherList);
   session.setAttribute("subjectList", info.subList);
   session.setAttribute("roomList",info.roomList);
   session.setAttribute("timeSlotList",info.timeSlotList);
   session.setAttribute("tutGroupList", info.tutGroupList);
   session.setAttribute("teacherName", name);
     
%>
<%if(session.getAttribute("role").toString().equals("admin"))
	{%>
<jsp:forward page="AdminHome.jsp"></jsp:forward>
<% }
else
{
	%>
<jsp:forward page="UserHome.jsp"></jsp:forward>


<%}%>