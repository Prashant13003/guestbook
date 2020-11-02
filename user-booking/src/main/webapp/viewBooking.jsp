<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>viewBooking</title>
      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
      
     <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
      <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   
      <style>
body {
    color: #404E67;
    background: #F5F7FA;
    font-family: 'Open Sans', sans-serif;
}
 .logout-navbar-right{
         margin:0px 0px 0px 761px !important;
      }
.table-wrapper {
    width: 100%;
    margin: 30px auto;
    background: #fff;
    padding: 20px;	
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.table-title {
    padding-bottom: 10px;
    margin: 0 0 10px;
}
.table-title h2 {
    margin: 6px 0 0;
    font-size: 22px;
}
.table-title .add-new {
    float: right;
    height: 30px;
    font-weight: bold;
    font-size: 12px;
    text-shadow: none;
    min-width: 100px;
    border-radius: 50px;
    line-height: 13px;
}
.table-title .add-new i {
    margin-right: 4px;
}
table.table {
    table-layout: fixed;
}
table.table tr th, table.table tr td {
    border-color: #e9e9e9;
}
table.table th i {
    font-size: 13px;
    margin: 0 5px;
    cursor: pointer;
}
table.table th:last-child {
    width: 144px;
}
table.table td a {
    cursor: pointer;
    display: inline-block;
    margin: 0 5px;
    min-width: 24px;
}    
table.table td a.add {
    color: #27C46B;
}
table.table td a.edit {
    color: #FFC107;
}
table.table td a.reject {
    color: #FFC107;
}
table.table td a.approve {
    color: #07ffd1;
}
table.table td a.delete {
    color: #E34724;
}
table.table td i {
    font-size: 19px;
}
table.table td a.add i {
    font-size: 24px;
    margin-right: -1px;
    position: relative;
    top: 3px;
}    
table.table .form-control {
    height: 32px;
    line-height: 32px;
    box-shadow: none;
    border-radius: 2px;
}
table.table .form-control.error {
    border-color: #f50000;
}
table.table td .add {
    display: none;
}
</style>
</head>
<body>

<!-- End site branding area -->
<div class="mainmenu-area">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <c:if test="${roleName =='ROLE_USER'}">
                    	<li><a href="${pageContext.request.contextPath}/booking">Entry</a></li>
                    </c:if>
                    <c:if test="${roleName =='ROLE_ADMIN'}">
                    <li class="active"><a href="${pageContext.request.contextPath}/viewBooking">ViewEntry</a></li>
                    </c:if>
                   <li style="cursor: pointer;font-size:48px;color:red">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
				        <form id="logoutForm" method="POST" action="${contextPath}/logout">
				            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				        </form>
				
				        <%-- <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()" class="text-center" style="cursor: pointer;">Logout</a></h2> --%>
				     <a onclick="document.forms['logoutForm'].submit()" class="text-center logout-navbar-right fa fa-sign-out">Logout</a>
				    </c:if>
                    </li>
                   <%--  <li><a href="${pageContext.request.contextPath}/contact">Contacts</a></li> --%>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- End mainmenu area -->


<div class="container-lg">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><h2>Entry <b>Details</b></h2></div>
                    <!-- <div class="col-sm-4">
                        <button type="button" class="btn btn-info add-new"><i class="fa fa-plus"></i> Add New</button>
                    </div> -->
                </div>
            </div>
             <table class="table table-bordered">
                <thead>
                    <tr>
                   		<th>Id</th>
                        <th>File</th>
                        <!-- <th>Email</th> -->
                        <th>Comments</th> -->
                        <th>Entry Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                
                <tbody>
                <c:forEach items="${bookinglist}" var="item">
					<tr>
						<td>${item.id}</td>
						
						<c:choose>
						    <c:when test="${item.downloadUrl=='EXIST'}">
						       <td><a href="${pageContext.request.contextPath}/download/${item.id}" target="_blank">Download file!!!</a></td>
						        <br />
						    </c:when>    
						    <c:otherwise>
						       <td><a href="#">file not available!!!</a></td>
						        <br />
						    </c:otherwise>
						</c:choose>
						<!--  <td><input type="file" name="file" id="file"/></td> -->
                       <%-- <td><img src="data:image/jpeg;base64,${item.file}"/></td> --%>
                       <%--  <td>${item.email}</td> --%>
                      <td>${item.comments}</td>
                        <td>${item.bookingDate}</td>
                        <td>
                            <!-- <a class="add" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a> -->
                            <a class="reject" title="Reject" data-toggle="tooltip"><i class="material-icons">&#xE8DB;</i></a>
                            <a class="approve" title="Approve" data-toggle="tooltip"><i class="material-icons">&#xE8DC;</i></a>
                            <a class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                        </td>
                    </tr>    
					    
				</c:forEach>
                
                    
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  
 <script>
$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
	var actions = $("table td:last-child").html();
	// Append table with add row form on add new button click
    $(".add-new").click(function(){
		$(this).attr("disabled", "disabled");
		var index = $("table tbody tr:last-child").index();
        var row = '<tr>' +
            '<td><input type="text" class="form-control" name="name" id="name"></td>' +
            '<td><input type="text" class="form-control" name="department" id="department"></td>' +
            '<td><input type="text" class="form-control" name="phone" id="phone"></td>' +
			'<td>' + actions + '</td>' +
        '</tr>';
    	$("table").append(row);		
		$("table tbody tr").eq(index + 1).find(".add, .edit").toggle();
        $('[data-toggle="tooltip"]').tooltip();
    });
	// Add row on add button click
	$(document).on("click", ".add", function(){
		var empty = false;
		var input = $(this).parents("tr").find('input[type="text"]');
        input.each(function(){
			if(!$(this).val()){
				$(this).addClass("error");
				empty = true;
			} else{
                $(this).removeClass("error");
            }
		});
		$(this).parents("tr").find(".error").first().focus();
		if(!empty){
			input.each(function(){
				$(this).parent("td").html($(this).val());
			});			
			$(this).parents("tr").find(".add, .edit").toggle();
			$(".add-new").removeAttr("disabled");
		}		
    });
	// Edit row on edit button click
	$(document).on("click", ".edit", function(){		
        $(this).parents("tr").find("td:not(:last-child)").each(function(){
			$(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
		});		
		$(this).parents("tr").find(".add, .edit").toggle();
		$(".add-new").attr("disabled", "disabled");
    });
	
	// aprove button click
	$(document).on("click", ".approve", function(){
		var currentRow=$(this).closest("tr"); 
        var id=currentRow.find("td:eq(0)").html(); // get current row 1st table cell TD value
        var tempUrl=getContextPath1()+"/bookingAction/"+id+"?action=approve";
         $.ajax({
             url: tempUrl,
             type: "GET",
             contentType: 'application/json; charset=UTF-8',
             dataType: 'json',
             success: function(msg){
            	 location.reload();
             },
             error:function(msg){
            	 location.reload();
             }
         });
        
         
    });
	
	// aprove button click
	$(document).on("click", ".reject", function(){
		
		var currentRow=$(this).closest("tr"); 
        var id=currentRow.find("td:eq(0)").html(); // get current row 1st table cell TD value
        var tempUrl=getContextPath1()+"/bookingAction/"+id+"?action=reject";
         $.ajax({
             url: tempUrl,
             type: "GET",
             contentType: 'application/json; charset=UTF-8',
             dataType: 'json',
             success: function(msg){
            	 location.reload();
             },
             error:function(msg){
            	 location.reload();
             }
         });
    });
	
	// Delete row on delete button click
	$(document).on("click", ".delete", function(){
		var currentRow=$(this).closest("tr"); 
        var id=currentRow.find("td:eq(0)").html(); // get current row 1st table cell TD value
        var tempUrl=getContextPath1()+"/bookingAction/"+id+"?action=delete";
         $.ajax({
             url: tempUrl,
             type: "GET",
             contentType: 'application/json; charset=UTF-8',
             dataType: 'json',
             success: function(msg){
            	 location.reload();
             },
             error:function(msg){
            	 location.reload();
             }
         });
    });
	
	function getContextPath1() {
		   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
		}
});
</script>
</body>
</html>
