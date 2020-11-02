<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Create an account</title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
 <style type="text/css">	 
body {
  margin: 0;
  padding: 0;
  background-color: #17a2b8;
  height: 100vh;
}
#login .container #login-row #login-column #login-box {
  margin-top: 120px;
  max-width: 600px;
  height: 100%;
  border: 1px solid #9C9C9C;
  background-color: #EAEAEA;
}
#login .container #login-row #login-column #login-box #login-form {
  padding: 20px;
}
#login .container #login-row #login-column #login-box #login-form #register-link {
  margin-top: -44px;
}
</style>    
  </head>

  <body>
<div class="container">
 <div id="login">
       <!--  <h3 class="text-center text-white pt-5">Login form</h3> -->
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center" style="margin-right: -67px;margin-left: 260px;">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form:form id="login-form" class="form form-signin" modelAttribute="userForm" method="post">
                            <h3 class="text-center text-info">Create your account</h3>
                            <spring:bind path="username">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label for="username" class="text-info">Username:</label><br>
                                <form:input type="text" path="username" name="username" id="username" class="form-control" autofocus="true"></form:input>
                            	<form:errors path="username"></form:errors>
                            </div>
                            </spring:bind>
                             <spring:bind path="password">
	                           <div class="form-group ${status.error ? 'has-error' : ''}">
	                                <label for="password" class="text-info">Password:</label><br>
	                                <form:input type="password" path="password" name="password" id="password" class="form-control"></form:input>
	                          		 <form:errors path="password"></form:errors>
	                            </div>
                            </spring:bind>
                            <spring:bind path="passwordConfirm">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label for="passwordConfirm" class="text-info">PasswordConfirm:</label><br>
                                <form:input type="password" path="passwordConfirm" name="passwordConfirm" id="passwordConfirm" class="form-control"></form:input>
                            <form:errors path="passwordConfirm"></form:errors>
                            </div>
                             </spring:bind>
                            <div class="form-group">
                               <!--  <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br> -->
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="submit">
                                
                            </div>
                            <div id="register-link" class="text-right">
	                                <a href="${contextPath}/login" class="text-info">Cancel</a>
	                        </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

        <%-- <form:form method="POST" modelAttribute="userForm" class="form-signin">
            <h2 class="form-signin-heading">Create your account</h2>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="passwordConfirm" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <form:errors path="passwordConfirm"></form:errors>
                </div>
            </spring:bind>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form> --%>

    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  </body>
</html>
