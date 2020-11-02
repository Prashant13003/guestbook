<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>welcome</title>
      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
      
      <link href="${contextPath}/resources/css/font-awesome.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/jquery.dataTables.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/main.css" rel="stylesheet">
      
       <link href="${contextPath}/resources/css/owl.carousel.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/responsive.css" rel="stylesheet">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      
      <style type="text/css">
      .logout-navbar-right{
         margin:0px 0px 0px 761px !important;
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
                    <li class="active"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                     <c:if test="${roleName =='ROLE_USER'}">
                    	<li><a href="${pageContext.request.contextPath}/booking">Entry</a></li>
                    </c:if>
                    <c:if test="${roleName =='ROLE_ADMIN'}">
                    <li><a href="${pageContext.request.contextPath}/viewBooking">ViewEntry</a></li>
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


  <div class="container">
 
  <p style="text-align: center;font-size:30px;margin-top:100px">welcome to User Entry </p>
    
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  
  
</body>
</html>
