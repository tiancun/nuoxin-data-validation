<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/><!--页面视窗比例声明-->
    <link rel="stylesheet" type="text/css" href="/adapt.css">
    <link rel="stylesheet" type="text/css" href="/index.css">
    <!-- Link Swiper's CSS -->
    <link rel="stylesheet" href="/swiper.min.css">
    <!-- Demo styles -->

</head>
<body>
    <form method="post" enctype="multipart/form-data" action="eapp/file">
        <input type="file" name="file" id="file"/>

        <input type="submit" value="提交">
    </form>
    <div>
        <table>
        <tr>
            <td style="width: 100px;">医生id</td>
            <td style="width: 100px;">医生姓名</td>
            <td style="width: 100px;">分数</td>
            <td>推荐文章</td>
            <#--<td>文章类型</td>-->
        </tr>
    <#list list as bean>
        <tr>
            <td>${bean.doctorId!''}</td>
            <td>${bean.doctorName!''}</td>
            <td>${bean.source!''}</td>
            <td>${bean.title!''}</td>
            <#--<td>文章类型</td>-->
        </tr>
    </#list>
        </table>
    </div>
</body>
<script type="text/javascript" src="/jquery-1.12.1.min.js"></script>
</html>
