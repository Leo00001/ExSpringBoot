<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>上传示例</title></head>
<body>

<div>

    <label style="display: block">单文件上传</label>
    <form action="/upload/single_file" enctype="multipart/form-data" method="post" onsubmit="return fileCheck();">
        <input name="file" type="file"> 选择文件 <br>

        <input type="submit" value="提交">

    </form>
</div>

<div>

    <label style="display: block">多文件上传</label>
    <form action="/upload/multi_file" enctype="multipart/form-data" method="post">
        <input name="file" type="file"> 选择文件1 <br>
        <input name="file" type="file"> 选择文件2 <br>
        <input name="file" type="file"> 选择文件3 <br>

        <input type="submit" value="提交">

    </form>
</div>

<script>

    String.prototype.endWith = function (str) {
        var reg = new RegExp(str + "$");
        return reg.test(this)
    }

    function fileCheck() {
        var files = $("input[name=file]")[0].files;
        if (files.length <= 0) {
            alert('请选择文件');
            return false
        }
        0
        var file = files[0];
        if (file.name.endWith(".png")) {
            alert('不支持png');
            return false
        }

        return true
    }
</script>

<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</body>
</html>