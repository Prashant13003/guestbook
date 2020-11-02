<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>booking</title>
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link href="${contextPath}/resources/css/style.css" rel="stylesheet">

<link href="${contextPath}/resources/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/main.css" rel="stylesheet">

<link href="${contextPath}/resources/css/owl.carousel.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/responsive.css"
	rel="stylesheet">
	
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
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
						<c:if test="${roleName =='ROLE_USER'}">
							<li class="active"><a href="${pageContext.request.contextPath}/booking">Entry</a></li>
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
	<div class="container my-4" style="margin-top:30px">
		 <!--Section: Contact v.2-->
		 <h2 style="color:#0040ff; margin-bottom: 25px;">${successmessage}</h2>
		<section class="mb-4">
		 <div class="row"> <!--Grid column-->
        <div class="col-md-9 mb-md-0 mb-5">
            <form:form name="contactForm" method="post" modelAttribute="bookingForm"
				action="${pageContext.request.contextPath}/booking"
				id="contact-form" class="contactClass" enctype="multipart/form-data">
				<!--Grid row-->
                <div class="row">
                    <div class="col-md-6">
                        <div class="md-form mb-0">
                           <label for="file" class="text-info">Please upload file:</label><br>
                           <!--  <input type="text" id="subject" name="subject" class="form-control"> -->
                            <form:input type="file" path="file" name="file" id="file"
								class="form-control" autofocus="true" size="50"></form:input>
								<%--  <form:errors path="file" style="color:red;"/> --%>
								<p style="color:red;">${errormessage}</p>
							<!--  <label for="subject" class="">Subject</label> -->
                        </div>
                    </div>
                    <!--Grid column-->
                    <div class="col-md-6">
						<div class="md-form mb-0">
						<label for="comments" class="text-info">Your message:</label><br>
                           <form:textarea path="comments" name="comments" id="comments"
								class="form-control" autofocus="true" placeholder="comments"></form:textarea >
                           <!--  <label for="comments"></label> -->
                        </div>

                    </div>
                </div>
                <!--Grid row-->
			</form:form>

            <div class="text-center text-md-left" style="margin-top:30px">
                <a class="btn btn-primary" onclick="document.getElementById('contact-form').submit();">Submit</a>
            </div>
            <div class="status"></div>
        </div>
        <!--Grid column-->
	</div>
	
	</section>
	<!--Section: Contact v.2-->
</div>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>


</body>
</html>
