<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles/style.css" />
<title>Voting System</title>
<style>
	ol li {
		margin-bottom: 10px;
		line-height: 20px;
	}

</style>
<%
	String userName = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				userName = cookie.getValue();
		}
	}
	if (userName == null)
		response.sendRedirect("login.jsp");
%>
</head>
<body>
	<div id="page">
		<div id="header">
			<div id="section">
				<div
					style="text-align: center; color: white; margin: 0px 0 0px 324px;">
					<h1>Online Voting System</h1>
				</div>
			</div>
			<ul>
				<li class="current"><a href="index.jsp">Project Description</a></li>
				<li><a href="vote.jsp">Vote Here</a></li>
				<li><a href="vote-stats.jsp">Voting Statistics</a></li>
				<li><a href="contact.jsp">Contact us</a></li>
				<li><a href="logoutServlet">Logout</a></li>
				<li><a class="welcome"><b>Welcome! <%=userName%></b></a></li>
			</ul>
			<div id="tagline" style="text-align: justify;">
				<div style="font-size: 17px">
					"Online Voting System" is a simple web-based online voting systems that will
					help our college to know student's most preferred language for software
					development. Online voting (also known as e-voting) is voting using
					electronic systems to aid casting and counting votes. <br> <br>
					
					Remote e-Voting is where voting is performed within the voter's sole
					influence, and is not physically supervised by representatives
					(e.g. voting from one's personal computer, mobile phone, television
					via the internet (also called i-voting). Electronic voting
					technology can speed the counting of ballots and can provide
					efficiency in statistics. <br> <br>
					
					In order for students to vote, they must follow the following procedure<br/>
					<ol>
						<li>Step1: They have to register first by clicking on "Register me" button on the home page. </li>
						<li>Step2: Once registered, they can use the "Sign me in" option with their respective username and password.</li> 
						<li>Step3: After successful sign in they can go to "Vote Here" tab and register their vote by selecting
						any one language of their choice and hitting the "Submit" button.</li>
						<li>Step4: Once done their vote will be registered. Note that <span style="font-weight: bold;">Only One</span> vote is allowed per student</span> 
						</li> 
						<li>Step5: In "Voting Statistics" tab students can see the total number of votes received for each development language.</li> 
					</ol>
					Thank you!
				</div>
			</div>
		</div>

		<div id="footer">
			<div>
				<div class="section">
					<p style="text-align: center;">Copyright &copy; Online Voting System - All Rights Reserved.</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>