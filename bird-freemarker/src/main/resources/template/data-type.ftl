<#--
    --freemarke的注释
    数据类型:
        1.布尔类型
            freemarker解析的时候不能直接解析布尔类型,需要进行字符串转换解析。
            方式一: ${flag?c}或者${flag?string}
            方式二: ${flag?string('yes','no')}
        2.日期类型
        3.数值类型
        4.字符串类型
        5.空值类型
        6.数组类型
        7.哈希类型
-->

<h1>布尔类型</h1>
<h3>${flag?c}</h3>
<h3>${flag?string}</h3>
<h3>${flag?string('yes','no')}</h3>

