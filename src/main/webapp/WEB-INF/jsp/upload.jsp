<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>

  <form:form modelAttribute="media" method="post" enctype="multipart/form-data">
  <fieldset>
      <legend>${post.title}</legend>

<!-- 	<form method="POST" enctype="multipart/form-data" action="/upload">
		File to upload: <input type="file" name="file"><br /> 
		Name: <input type="text" name="name"><br /> <br /> 
		<input type="submit" value="Upload">
	</form>
	 -->
	<form:form modelAttribute="media" method="POST" commandName="media"  enctype="multipart/form-data">
	  
	        Upload your file please:
	        <form:input path="file" type="file" />
	        <input id="title" name="title" type="text" value="title" />
	        <form:button>Submit</form:button>
	        <%-- <form:errors path="file" cssStyle="color: #ff0000;" /> --%>
	    </form:form>


  </fieldset>
</form:form>