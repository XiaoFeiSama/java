<%--
  Created by IntelliJ IDEA.
  User: MECHREVO
  Date: 2019/12/19
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<form action="/register" method="post" onsubmit="return checkForm()" onreset="show()">
    用户名：<input type="text" name="username" id="uname" value="" placeholder="请输入用户名6-16位字母" onblur="checkUserName()" /><span
        id="sname"></span><br>
    密码：<input type="password" name="password" id="ppassword" placeholder="请输入密码6-16位数字" onblur="checkPassword()" /><span
        id="ppwd"></span><br>
    请确认密码：<input type="password" name="rpassword" id="rppassword" placeholder="请输入密码6-16位数字" onblur="rcheckPassword()" /><span
        id="rppwd"></span><br>
    <img src="/code" id="myimg">
    <input type="text" name="checkCode" placeholder="请输入验证码">
    <a href="#" onclick="changeImg()">换一张</a>
    <input type="submit" value="提交" >
    <input type="reset" value="重置" />
</form>
</body>

<script type="text/javascript">
    function changeImg() {

        var img=document.getElementById("myimg");
        //304 读取缓存 拼接个随机参数，然后让服务器每次都得响应
        img.src="/code?time="+new Date().getTime();

    }
    function rcheckPassword() {

        var text = document.getElementById("rppassword").value;
        var text1 = document.getElementById("ppassword").value;

        var sname = document.getElementById("rppwd");
        if (text == "") {
            sname.innerHTML = "<span style='color:red'>密码不能为空</span>"
        } else if (text == text1) {
            sname.innerHTML = "<span style='color:green'>✔密码一致</span>"
        } else {
            sname.innerHTML = "<span style='color:red'>✘密码不一致</span>"
        }
        return flag;
    }

    function checkForm() {
        //alert("表单要提交");
        //校验每个表单项是否符合规则返回true  不符合规则返回false
        return checkUserName() && checkPassword() && rcheckPassword();
    }
    function checkPassword() {
        var regx = /^[0-9]{6,16}$/;
        var text = document.getElementById("ppassword").value;
        var flag = regx.test(text);
        var sname = document.getElementById("ppwd");
        if (text == "") {
            sname.innerHTML = "<span style='color:red'>密码不能为空</span>"
        } else if (flag) {
            sname.innerHTML = "<span style='color:green'>✔密码规则正确</span>"
        } else {
            sname.innerHTML = "<span style='color:red'>✘密码规则不正确</span>"
        }
        return flag;
    }
    function checkUserName() {
        var regx = /^[a-z]{6,16}$/i;
        var text = document.getElementById("uname").value;
        var flag = regx.test(text);
        var sname = document.getElementById("sname");
        if (text == "") {
            sname.innerHTML = "<span style='color:red'>用户名不能为空</span>"
        } else if (flag) {
            sname.innerHTML = "<span style='color:green'>✔用户名规则正确</span>"
        } else {
            sname.innerHTML = "<span style='color:red'>✘用户名规则不正确</span>"
        }
        return flag;
    }

</script>
</html>
