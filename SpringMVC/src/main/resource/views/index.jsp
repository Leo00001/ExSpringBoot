<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="../assets/css/common.css">
</head>
<body>
<h1>测试页面</h1>

<form action="/anno/submit_json" enctype="application/x-www-form-urlencoded" method="post">
    <div>
        <label>ID</label>
        <input name="id">
    </div>

    <div>
        <label>姓名</label>
        <input name="name">
    </div>

    <input type="submit" value="提交">
</form>


<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</body>
</html>