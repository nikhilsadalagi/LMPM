<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>
<c:forEach items="${posts}" var="post">
				
		  		 <h2>${post.title}</h2>
		  		<div role="tabpanel" class="tab-pane" id="post_${post.id}">
		  		<div class="row">

        <div class="col-sm-8 blog-main">

          <div class="blog-post">
		  		<br/>  		
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
			<form id="comment" action="/commentsub.html?post=${post.id}&user=${user.id}" class="commentClass" method="POST" enctype="multipart/form-data">
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