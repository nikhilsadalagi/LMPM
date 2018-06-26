<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>

<form:form commandName="user" cssClass="form-horizontal registrationForm">

	<c:if test="${param.success eq true}">
		<div class="alert alert-success">Registration Successful</div>
	</c:if>

	 <div class="form-group">
    	<label for="name" class="col-sm-2 control-label">Name</label>
    	<div class="col-sm-10">
    		<form:input path="name" cssClass="form-control"/>
    		<form:errors path="name"/>
    	</div>
  	</div>
  	
	 <div class="form-group">
    	<label for="image" class="col-sm-2 control-label">Image</label>
    	<div class="col-sm-10">
	        <form:input path="image" type="file" />
    		<form:errors path="image"/>
    	</div>
  	</div>
  	<div class="form-group">
    	<label for="email" class="col-sm-2 control-label">Email</label>
    	<div class="col-sm-10">
    		<form:input path="email" cssClass="form-control"/>
    		<form:errors path="email"/>
    	</div>
  	</div>
  	<div class="form-group">
    	<label for="usn" class="col-sm-2 control-label">USN</label>
    	<div class="col-sm-10">
    		<form:input path="usn" cssClass="form-control" />
    		<form:errors path="usn"/>
    	</div>
  	</div>
  	
	 <div class="form-group">
	 	<label for="rol1" class="col-sm-2 control-label">Role</label>
    	<div class="col-sm-10">
    	
    	<input id="role11" name="role1" value="ROLE_STUDENT" type="radio">Student 
    	<input id="role12" name="role1" value="ROLE_TEACHER" type="radio">Teacher
    	<%-- 
    	<form:radiobutton path="role1" value="ROLE_STUDENT"/>Student 
    	<form:radiobutton path="role1" value="ROLE_TEACHER"/>Teacher --%>
		</div>
	</div>
  	
  	<div class="form-group">
    	<label for="password" class="col-sm-2 control-label">Password</label>
    	<div class="col-sm-10">
    		<form:password path="password" cssClass="form-control"/>
    		<form:errors path="password"/>
    	</div>
  	</div>
  	
  	<div class="form-group">
    	<label for="password" class="col-sm-2 control-label">Password Again</label>
    	<div class="col-sm-10">
			<input type="password" name="password_again" id="password_again" class="form-control" />
    	</div>
  	</div>
  	
  	<div class="form-group" >
    	<div class="col-sm-2">
    		<input type="submit" value="save" class="btn btn-lg btn-primary"/>
    	</div>
  	</div>
</form:form>

<script type="text/javascript">


$(document).ready(function(){
	$(".registrationForm").validate({
			rules : {
				name:{
					required:true,
					minlength :3,
					remote :{
						url : "<spring:url value='/register/available.html' />",
						type:"get",
						data :{
							username : function(){
								return $("#name").val();
							}
						}
		 			} 
				},
				email:{
					required:true,
					email:true
				},
				usn:{
					required:true,
					minlength :3,
				},
				password:{
					required:true,
					minlength : 5
				},
				password_again:{
					required:true,
					minlength : 5,
					equalTo: "#password"
				}
			
			},
			highlight:function(element){
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight:function(element){
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
			messages : {
				name :{
					remote : "Such username already exists!"
				}
			}
		}
	);
});


</script>