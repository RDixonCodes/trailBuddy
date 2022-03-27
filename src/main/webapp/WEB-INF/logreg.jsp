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
<title>Register/Login</title>
</head>
<body>
	<div class="header">
	<img src="https://cdn-icons-png.flaticon.com/512/565/565829.png" 
			alt="mountain image">
		<h1 class="font-weight-bolder">Trail Buddy</h1>
		<h2>(Please register or log in)</h2>
		</div>
	<div class="register">
	
	<h1>Register</h1>
	
	<p><form:errors path="user.*"/></p>
	
	<form:form method="POST" action="/register" modelAttribute="user">
	
		<div class="form-group">
		
			<form:label path="firstName">First Name:</form:label>
			
			
			<form:input class="user-group" type="text" path="firstName"/>
	
		</div>
		
		<div class="form-group">
		
			<form:label path="lastName">Last Name:</form:label>
			
			
			<form:input class="user-group" type="text" path="lastName"/>
				
		</div>
		<div class="location">
		<div class="form-group">
		
			<form:label path="location">Location: </form:label>
					<form:select path="state">
						<option value="CA">CA</option>
						<option value="UT">UT</option>
						<option value="TX">TX</option>
						<option value="CO">CO</option>
						<option value="WA">WA</option>
						<option value="NV">NV</option>
					</form:select>
					<form:input path="location"/>
		
		</div>
		</div>
	
		<div class="form-group">
		
			<form:label path="email">Email: </form:label>
	
			<form:input class="user-group" type="email" path="email"/>
		
		</div>
		
		<div class="form-group">
			
			<form:label path="password">Password: </form:label>
			
			<form:password class="user-group" path="password"/>
			
		</div>
		
		<div class="form-group">
		
			<form:label path="passwordConfirmation">Password Confirmation: </form:label>
			
			<form:password class="user-group" path="passwordConfirmation"/>
		
		</div>
		
		<button class="btn btn-outline-primary" type="submit">Register!</button>
		
	</form:form>
	
	</div>
	
	<div class="login">
	
	<h1>Login</h1>
	
	<p><c:out value="${error}"/></p>
	
	<form method="POST" action="/login">
	
		<p>
		
			<label type="email" for="email"> Email: </label>
			
			<input class="user-group" type="email" id="email" name="email"/>
		
		</p>
		
		<p>
		
			<label for="password">Password: </label>
			
			<input class="user-group" type="password" id="password" name="password"/>
			
		</p>
		
		<button class="btn btn-outline-primary" type="submit">Login!</button>
		
	</form>
	</div>
</body>
</html>