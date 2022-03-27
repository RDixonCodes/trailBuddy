<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="UTF-8">
<title>${event.name}</title>
</head>
<body>
<div class="header">
	<img src="https://cdn-icons-png.flaticon.com/512/565/565829.png" 
			alt="mountain image">
		<h1 class="font-weight-bolder">Trail Buddy</h1>
		</div>
	<div class="container my-5">
		<a href="/trips" class="float-right">Dashboard</a>
		<a href="/logout" class="float-right mx-5">Logout</a>
		<div class="row py-5">
			<div class="col mr-5">
				<h1>${trip.name}</h1>
				<p>Host: ${trip.host.firstName} ${event.host.lastName}</p>
				<p>Date: ${trip.tripDate}</p>
				<p>Location: ${trip.city}, ${trip.state}.</p>
				<p>People who are attending this hike: ${trip.joinedUsers.size()}</p>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th>Location</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${trip.joinedUsers}" var="user">
						<tr>
							<td>${user.firstName} ${user.lastName}</td>
							<td>${user.state}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="card bg-light mb-3">
			<div class="col">
				<h2 style= margin-left: "30px";>Message Wall</h2>
				<table class="table table-striped table-bordered table-sm">
					<tbody>
					<c:forEach items="${trip.messages}" var = "message">
						<tr>
							<td>${message.author.firstName} says: ${message.comment}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<form:form method="POST" action="/trips/${trip.id}/messages" modelAttribute="message">
					<p>
						<form:label path="comment">Add comment:</form:label>
						
						<form:errors path="comment"/>
				
						<form:input path="comment" type="text"/>
					</p>
					<button class="btn btn-outline-success">Submit</button>
				</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>