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
<title>Trip Buddy</title>
</head>
<body>
	<div class="dashboard">
	<h1 style= margin-left:10px; >Welcome, <c:out value="${user.firstName}"/>!</h1>
	
	<h3 style= margin-left:10px;>Here are some of the trips that are in your state:</h3>
	
	<a href="/logout" style= margin-left:750px; >Logout</a>
	</div>
	<div class="trips">
	
	<table class="table table-warning table-sm">
	
   		<thead>
       		<tr>
            	<th>Name</th>
            	<th>Date</th>
            	<th>Location</th>
            	<th>State</th>
            	<th>Host</th>
            	<th>Actions/Status</th>
        	</tr>
        	
    	</thead>
    	
    	<c:forEach items="${userTrips}" var="trip">
    	
    <tbody>
		<tr>    
    		<td><a href="/trips/${trip.id}"><c:out value="${trip.name}"/></a></td>
    		
    		<td><c:out value="${trip.tripDate}"/></td>
    		
    		<td><c:out value="${trip.city}"/></td>
    		
    		<td><c:out value="${trip.state}"/></td>
    		
    		<td><c:out value="${trip.host.firstName}"/></td>
    		
    		<td>
				<c:choose>
				
					<c:when test="${trip.host.id == user.id}">
					
						<a href="/trips/edit/${trip.id}">Edit</a> <span> | </span>
						
						<a href="/trips/delete/${trip.id}">Delete</a>
						
					</c:when>
					
					<c:otherwise>
					
					<c:choose>
					
					<c:when test="${trip.joinedUsers.contains(user)}">
					
						<p>Joining <span> | </span> <a href="/trips/cancel/${trip.id}">Cancel</a></p>
						
					</c:when>
					
					<c:otherwise>
					
						<a href="/join/${trip.id}">Join</a>
						
						</c:otherwise>
						
					</c:choose>
					
				</c:otherwise>
				
				</c:choose>
				
			</td>
			
		</tr>
		
    </tbody>
    
    </c:forEach>
    
	</table>
	
	</div>
	
	<h3 style= margin-left:10px>Here are some of the other trips:</h3>
	
	<div class="trips">
	
	<table class="table table-success table-sm">
	
   		<thead>
   		
       		<tr>
       		
            	<th>Name</th>
            	<th>Date</th>
            	<th>Location</th>
            	<th>State</th>
            	<th>Host</th>
            	<th>Actions</th>
            	
        	</tr>
        	
    	</thead>
    	
    	<c:forEach items="${otherTrips}" var="trip">
    	
    <tbody>
    
		<tr>    
    		<td><a href="/trips/${trip.id}"><c:out value="${trip.name}"/></a> </td>
    		
    		<td><c:out value="${trip.tripDate}"/></td>
    		
    		<td><c:out value="${trip.city}"/></td>
    		
    		<td><c:out value="${trip.state}"/></td>
    		
    		<td><c:out value="${trip.host.firstName}"/></td>
    		
    		<td>
				<c:choose>
				
					<c:when test="${trip.host.id == user.id}">
					
						<a href="/trips/edit/${trip.id}">Edit</a> <span>|</span>
						
						<a href="/trips/delete/${trip.id}">Delete</a>
						
					</c:when>
					
					<c:otherwise>
					
						<c:choose>
						
							<c:when test="${trip.joinedUsers.contains(user)}">
							
							<p>Joining <span>|</span> <a href="/trips/cancel/${trip.id}">Cancel</a></p>
							
							</c:when>
							
							<c:otherwise>
							
							<a href="/join/${trip.id}">Join</a>
							
							</c:otherwise>
							
						</c:choose>
						
					</c:otherwise>
					
				</c:choose>
				
			</td>
			
		</tr>
		
    </tbody>
    
    </c:forEach>
    
	</table>
	
	</div>
	<div class="card bg-light mb-3">
	
	<h3>Create a trip</h3>
	<hr></hr>
	
	<form:form method="POST" action="/trips/new" modelAttribute="trip">
	
		<div class="form-group">
		
			<form:label path="name">Name: </form:label>
			
			<form:errors path="name"/>
			
			<form:input path="name"/>
		</div>
		
		<div class="form-group">
		
		<form:label path="tripDate">Date: </form:label>
		
		<form:errors path="tripDate"/>
		
		<form:input type="date" path="tripDate" style="width:180px;"></form:input>
		
		</div>
		
		<div class="location">
		
		<div class="form-group">
		
			<form:label path="city">Location: </form:label>
			
			<form:errors path="city"/>
			
					<form:select path="state">
						<option value="CA">CA</option>
						<option value="UT">UT</option>
						<option value="TX">TX</option>
						<option value="CO">CO</option>
						<option value="WA">WA</option>
						<option value="NV">NV</option>
					</form:select>
					
					<form:input path="city"/>
					
					</div>
					
			<button type="submit" class="btn btn-outline-primary">Create Trip</button>
			
		</div>
		
		</form:form>		
	</div>
	</div>
</body>
</html>