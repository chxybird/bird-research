<#--
    --freemarke的注释
    数据类型:
        1.布尔类型
            freemarker解析的时候不能直接解析布尔类型,需要进行字符串转换解析。
            方式一: ${flag?c}或者${flag?string}
            方式二: ${flag?string('yes','no')}
        2.日期类型
            freemarker解析日期类型有四种形式
            date 年月日                   ${date?date}
            time 时分秒                   ${date?time}
            datetime 年月日时分秒          ${date?datetime}
            自定义类型                     ${date?string('yyyy-MM-dd HH:mm:ss')}
        3.数值类型
            freemarker解析数字类型有三种方式
            方式一:直接输出
                ${num}
            方式一:转字符串
                普通模式
                ${num?string}
                货币型模式
                ${num?string.currency}
                百分比模式
                ${num?string.percent}
            方式二:保留浮点型,指定小数位     每个#表示一位小数位
                ${num?string('0.##')}
        4.字符串类型     可以使用java程序处理字符串,不一定使用freemaker处理字符串
            常见的字符串类型输出方式
            直接
            ${str}
            截取(左闭右开) 不写直接截取到末尾
            ${str?substring(5)}
            首字母小写
            ${str?uncap_first}
            首字母大写
            ${str?cap_first}
            字母小写
            ${str?lower_case}
            字母大写
            ${str?upper_case}
            获取字符串长度输出
            ${str?length}
            去除字符串前后空格
            ${str?trim}
            替换指定字符串
            ${str?replace('.','-')}
        5.空值类型
            freemarker对于空值的处理会报异常,提供以下避免空值
            ${e!}                               如果为空输出空字符串
            ${e!'null'}                         如果为空输出指定格式字符串
            ${(e??)?string('有数据','无数据')}    判断是否为空，返回布尔值
        6.数组类型
            需要借助指令
        7.哈希类型
            需要借助指令
-->

<h1>布尔类型</h1>
<h3>${flag?c}</h3>
<h3>${flag?string}</h3>
<h3>${flag?string('yes','no')}</h3>

<h1>日期类型</h1>
<h3>${date?date}</h3>
<h3>${date?time}</h3>
<h3>${date?datetime}</h3>
<h3>${date?string('yyyy-MM-dd HH:mm:ss')}</h3>

<h1>数字类型</h1>
<h3>${num}</h3>
<h3>${num?string}</h3>
<h3>${num?string.currency}</h3>
<h3>${num?string.percent}</h3>
<h3>${num?string('0.##')}</h3>

<h1>字符串类型</h1>
<h3>${str}</h3>
<h3>${str?substring(5)}</h3>
<h3>${str?uncap_first}</h3>
<h3>${str?cap_first}</h3>
<h3>${str?lower_case}</h3>
<h3>${str?upper_case}</h3>
<h3>${str?length}</h3>
<h3>${str?trim}</h3>
<h3>${str?replace('.','-')}</h3>

<h1>空值类型</h1>
<h3>${e!}</h3>
<h3>${e!'null'}</h3>
<h3>${(e??)?string('有数据','无数据')}</h3>

<h1>列表类型(数组、集合)</h1>
<ul>
    <#list stringList as item>
        <li>${item}</li>
    </#list>
</ul>

<h1>哈希类型</h1>
<#list map? keys as key>
    ${key}:${map[key]}
</#list>




