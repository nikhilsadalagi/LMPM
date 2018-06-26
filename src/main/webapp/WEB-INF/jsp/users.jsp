<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>

<script type="text/javascript">
$(document).ready(function(){
	$(".triggerRemove").click(function (e){
		e.preventDefault();
		$("#modalRemove .removeBtn").attr("href",$(this).attr("href"));
		$("#modalRemove").modal();
	});
});
</script>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>user name</th>
			<th>Operations</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<th>
					<a href="<spring:url value="/users/${user.id}.html"/>" >
					${user.name} 
					</a><br/>
					   ${user.email}
				</th>
				<th>
				<c:if test="${user.name!='admin'}">
					<a href="<spring:url value="/users/remove/${user.id}.html"/>" class="btn btn-danger triggerRemove">Remove</a>
				</c:if>
				</th>
			</tr>
		</c:forEach>
	</tbody>
</table>


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
