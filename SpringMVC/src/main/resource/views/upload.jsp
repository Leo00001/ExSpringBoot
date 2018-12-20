<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>上传示例</title>
    <style>
        .progress{
            width: 300px;
            height: 30px;
            border:1px solid #ccc;
            border-radius: 15px;
            overflow: hidden;  /*注意这里*/
            box-shadow: 0 0 5px 0px #ddd inset;
        }
        .progress span {
            display: inline-block;
            width: 0;
            height: 100%;
            background: linear-gradient(45deg, #2989d8 30%,#7db9e8 31%,#7db9e8 58%,#2989d8 59%);
            background-size: 60px 30px;
            text-align: center;
            color:#fff;
        }
    </style>
</head>
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

<div>
    <label style="display: block">文件上传进度展示</label>
    <input type="file" id="upload_file" name="uploadFile"> 上传文件

    <div id="upload_with_progress" class="progress">
        <span>10%</span>
    </div>
    <button onclick="uploadFileWithProgress();">提交</button>
</div>

<script>

    function uploadFileWithProgress() {
        var fileObj = document.getElementById("upload_file").files[0]; // js 获取文件对象
        var FileController = "/upload/single_file"; // 接收上传文件的后台地址
        // FormData 对象---进行无刷新上传
        var form = new FormData();
        form.append("author", "hooyes"); // 可以增加表单数据
        form.append("file", fileObj); // 文件对象
        // XMLHttpRequest 对象
        var xhr = new XMLHttpRequest();
        xhr.open("post", FileController, true);
        xhr.onload = function () {
            alert("上传完成!");
            //$('#myModal').modal('hide');
        };
        //监听progress事件
        xhr.upload.addEventListener("progress", progressFunction, false);
        xhr.send(form);
    }

    function progressFunction(evt) {
        if (evt.lengthComputable) {

            var progress = Math.round(evt.loaded / evt.total * 100);
            $('#upload_with_progress span').css('width', progress + '%');
            console.log(Math.round(evt.loaded / evt.total * 100)
                + "%");
        }
    }


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

<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
</body>
</html>