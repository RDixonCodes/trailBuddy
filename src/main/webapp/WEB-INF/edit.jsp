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
<title>Edit Trip</title>
</head>
<body>
	<div class="container my-5">
		<a href="/trips" class="float-right">Dashboard</a>
		<a href="/logout" class="float-right mx-5">Logout</a>
		<h1 class="my-3">${thisTrip.name}</h1>
		<h3>Edit Trip</h3>
		
		<div class="update">
		<form:form action="/trips/edit/${thisTrip.id}" method="POST" modelAttribute="updateTrip">
		
			<div class="name">
			<p>
	            <form:label path="name">Name:
	            <form:errors path="name"/>
	            <form:input path="name" value="${thisTrip.name}"/>
	            </form:label>
	        </p>
	        </div>
	        
	        <div class="date">
	        <p>
	            <form:label path="tripDate">Date:
	            <form:errors path="tripDate"/>
	            <form:input type="date" path="tripDate"/>
	            </form:label>
	        </p>
	        </div>
	        <div class="city">
	        <p>
	            <form:label path="city">Location:
	            <form:errors path="city"/>
	 
	            <form:select path="state">
	            	<option value="CA">CA</option>
					<option value="UT">UT</option>
					<option value="TX">TX</option>
					<option value="CO">CO</option>
					<option value="WA">WA</option>
					<option value="NV">NV</option>
	            </form:select>
	            <form:input path="city" value="${thisTrip.city}"/>
	            </form:label>
	        </p>
	        </div>
	        <button type="submit" class="btn btn-outline-success">Edit</button>
		</form:form>
		</div>
	</div>
</body>
</html>