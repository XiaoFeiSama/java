<%--
  Created by IntelliJ IDEA.
  User: MECHREVO
  Date: 2019/12/19
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
      String username="";
      String password="";
      Cookie[] cookies = request.getCookies();
      if(cookies!=null){
          for (Cookie cookie : cookies) {
              if(cookie.getName().equals("username")){
                  username = cookie.getValue();
              }
              if (cookie.getName().equals("password")) {
                  password = cookie.getValue();
              }
          }
      }
  %>
      <form action ="/login" method="post">
      用户名：<input type="text" name="username" placeholder="请输入用户名" value="<%=username%>"/><br>
      密码：<input type="password" name="password" placeholder="请输入密码" value="<%=password%>"/><br>
          记住密码：<input type="radio" name="rember" value="7" />记住一周
          <input type="radio" name="rember" value="30" />记住30天
          <input type="radio" name="rember" value="0"/>不记住
      <input type="submit" value="登录"> <button id="myButton" type="button" onclick="register()">注册</button>
      </form>
  </body>
  <script>
    function register(){
      window.location.href = "/Register.jsp";
    }
  </script>
</html>
