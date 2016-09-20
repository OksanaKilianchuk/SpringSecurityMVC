<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div align="center">
    
    <form action="/registration/addUser" method="POST">
        Login:<br/><input type="text" name="login"><br/>
        Password:<br/><input type="password" name="password"><br/>
        Email:<br/><input type="text" name="email"><br/>
        Phone:<br/><input type="text" name="phone"><br/>
        <input type="submit" />
        
    </form>
</div>
</body>
</html>