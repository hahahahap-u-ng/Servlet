<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/26/2024
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Đăng nhập</h1>
<form action="login" method="post">
    <table>
        <tr><td>Tên đăng nhập:</td><td><input type="text" name="username"/></td></tr>
        <tr><td>Mật khẩu:</td><td><input type="text" name="password"/></td></tr>
        <tr><td colspan="2"><input type="submit" value="Login"/></td></tr>
    </table>
</form>

</body>
</html>
