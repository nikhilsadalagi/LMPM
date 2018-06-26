<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>
<h2>${user.name}</h2><%-- ${user.image}
<img src="getImageDetails.jsp?image=${user.image}" width="150" >
 --%><table class="table table-hover">
<tr>
	<td width="150" rowspan="6"><img src="../../image/kakashi.png" width="150" >
	</td>
</tr> 
	<tr>
		<td>USN</td>
		<td>${user.usn}</td>
	</tr>
	<tr>
		<td>email</td>
		<td>${user.email}</td>
	</tr>
	<tr>
		<td>Last login</td>
		<td>${user.lastlogin}</td>
	</tr>
	<tr>
		<td></td>
		<td>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalUpdate">Update</button></td>
	</tr>
</table>

<!-- Button trigger modal -->

    <security:authorize access="hasRole('ROLE_TEACHER')">
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  New Subject
</button>
</security:authorize>
<form:form commandName="post" cssClass="form-horizontal blogForm">
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">New Subject</h4>
      </div>
      
	      <div class="modal-body">
	        <div class="form-group">
	    	<label for="title" class="col-sm-2 control-label">Title</label>
	    	<div class="col-sm-10">
	    		<form:input path="title" cssClass="form-control"/>
    			<form:errors path="title"/>
	    	</div>
	  	</div>
	  	
	      <div class="modal-body">
	        <div class="form-group">
	    	<label for="content" class="col-sm-2 control-label">Description</label>
	    	<div class="col-sm-10">
	    		<form:input path="content" cssClass="form-control"/>
	    		<form:errors path="content"/>
	    	</div>
	  	</div>
	  	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <input type="submit" class="btn btn-primary" value="save" />
      </div>
    </div>
  </div>
</div>
</div>
</form:form>

<script type="text/javascript">
$(document).ready(function(){
	$('.nav-tabs a:first').tab('show');
	$(".triggerRemove").click(function (e){
		e.preventDefault();
		$("#modalRemove .removeBtn").attr("href",$(this).attr("href"));
		$("#modalRemove").modal();
	});
	$(".triggerUpload").click(function (e){
		e.preventDefault();
		$("#modalUpload .uploadForm").attr("action",$(this).attr("href"));
		$("#modalUpload").modal();
	});
	$('.blogForm').validate({
		rules : {
			title:{
				required:true,
				minlength :1
			},
			content:{
				required:true,
				minlength :1
			}
		},
		highlight:function(element){
			$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		},
		unhighlight:function(element){
			$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
		}
	});
});
</script>

<ul class="nav nav-tabs" role="tablist">
<c:forEach items="${user.posts}" var="post">
  <li><a href="#post_${post.id}" data-toggle="tab">${post.title}</a></li>
 </c:forEach>
</ul>

<!-- Tab panes -->
<div class="tab-content">







         
		<c:forEach items="${user.posts}" var="post">
				
		  		 
		  		<div role="tabpanel" class="tab-pane" id="post_${post.id}">
		  		<div class="row">

        <div class="col-sm-8 blog-main">

          <div class="blog-post">
		  		<br/>  		
		  		<div align="right">
		  			<a href='<spring:url value="./upload.html?postid=${post.id}"/>' class="btn btn-primary trigger triggerUpload">Add Material</a>
		  			<a href='<spring:url value="/post/remove/${post.id}.html"/>' class="btn btn-danger triggerRemove">Remove Subject</a>
		  		</div>
		  		<br/>${post.content}<br/>
		  		<br/>posted on ${post.posteddate}<br/>
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Title</th>
						<th>Description</th>
						<th>File</th>
						<th>Posted on</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${post.medias}" var="media">
						<tr>
							<td>${media.title}</td>
							<td>${media.description}</td>
							<td><a href="/download.html?file=${media.fileURI}">${media.fileURI}</a></td>
							<td><fmt:formatDate value="${media.publisheddate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<form id="comment" action="/comment.html?post=${post.id}&user=${user.id}" class="commentClass" method="POST" enctype="multipart/form-data">
		      <div class="modal-header">
		        <h4 class="modal-title" id="myModalLabel">Comment</h4>
		      </div>
		      <div class="modal-body">
			        <textarea class="form-control" id="comment" name="comment" placeholder="comment" rows="3"></textarea>
		      </div>
		      
		      <div class="modal-footer">
			        <button type="submit" class="btn btn-primary" value="Submit">Comment</button>
		      </div>
      
	    </form>
			
		  	
          </div><!-- /.blog-post -->
        </div><!-- /.blog-main -->

        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
          <div class="sidebar-module sidebar-module-inset">
         	Comments: 
           </div>
          <div class="sidebar-module">
            <ol class="list-unstyled">
            	<c:forEach items="${post.comments}" var="comment">
					<li>${comment.content}<br/>
						${comment.commentdate}</li><br/>
				</c:forEach>
              <li>This subject is interesting</li>
            </ol>
          </div>
        </div><!-- /.blog-sidebar -->

      </div><!-- /.row -->		  		
		  		</div>
		  	</c:forEach>	
</div>


<!-- ------------------------------------remove subject---------------------------------------------- -->
<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove Post</h4>
      </div>
      <div class="modal-body">
        Really remove?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a class="btn btn-danger removeBtn" href="">Remove</a>
      </div>
    </div>
  </div>
</div>

<!-- ----------------------------------create subject-------------------------------- -->

<div class="modal fade" id="modalUpload" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
		<form id="media" action="" class="uploadForm" method="POST" enctype="multipart/form-data">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">Upload study material</h4>
		      </div>
		      <div class="modal-body">
			  
			        Upload your file please:
			        <input id="file" name="file" type="file" value=""/><br/>
			        <input id="title" name="title" type="text" value="" class="form-control" placeholder="title" /><br/>
			        <textarea class="form-control" id="description" name="description" placeholder="description" rows="3"></textarea>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
			        <button type="submit" class="btn uploadBtn btn-primary" value="Submit">Upload</button>
		      </div>
      
	    </form>
    </div>
  </div>
</div>

<!-- ----------------------------------Update Details-------------------------------- -->

<div class="modal fade" id="modalUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    <form:form commandName="user" cssClass="form-horizontal registrationForm">
	
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">Update My Detail</h4>
	
		      </div>
		      <div class="modal-body">

	 <div class="form-group">
    	<label for="name" class="col-sm-2 control-label">Name</label>
    	<div class="col-sm-10">
    		<form:input path="name" cssClass="form-control"/>
    		<form:errors path="name"/>
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
  	  	
  	<!-- <div class="form-group" >
    	<div class="col-sm-2">
    		<input type="submit" value="save" class="btn btn-lg btn-primary"/>
    	</div>
  	</div> -->
  	
     </div>
     <div class="modal-footer">
       <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button type="submit" class="btn btn-primary" value="Submit">Upload</button>
     </div>

</form:form>
    </div>
  </div>
</div>


<script type="text/javascript">


	$(document).ready(function(){
		$(".registrationForm").validate({
				rules : {
					/* name:{
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
					}, */
					email:{
						required:true,
						email:true
					},
					usn:{
						required:true,
						minlength :3,
					},
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
