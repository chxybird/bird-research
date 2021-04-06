<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
<h1>
    <#if flag=1>
        系统架构师
    <#elseif flag=2>
        软件设计师
    <#elseif flag=3>
        网络工程师
    <#else>
        程序员
    </#if>
</h1>
<table>
    <tr>
        <td>序号</td>
        <td>编号</td>
        <td>姓名</td>
        <td>年龄</td>
    </tr>
    <#list studentList as student>
        <tr>
            <td>${student_index+1}</td>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
        </tr>
    </#list>
</table>
</body>
</html>