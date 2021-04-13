<#--
    freemarker的常用指令:
    1.list              用于循环遍历集合
    2.if elseif else    用于条件判断
    3.assign            用于自定义变量或者替换变量(与执行脚本类似)
    4.macro+nested      用于自定义指令和占位(类似于Vue的插槽)
    5.import            用于引入其他的命名空间(.ftl文件)
    6.include           用于插入其他文件
-->

<h1>list指令</h1>
<#list arrayList as item>
    ${item_index}:${item}
</#list>

<h1>if elseif else 指令</h1>
<#if flag=0>A
<#elseif flag=1>B
<#elseif flag=2>C
<#else>D
</#if>
<h1>assign指令</h1>
<#-- 定义变量 -->
<#assign e='张三' list=['科目一','科目二','科目三','科目四'] map={'age':22,'id':16036024}>
<#-- 替换变量 -->
<#assign out>
    <h3>列表</h3>
    <#list ['小鸟','青蛙','鬣狗','海豹'] as item>
        ${item}
    </#list>
</#assign>
${e}
<#list list as item>
    ${item}
</#list>
<#list map? keys as key>
    ${key}:${map[key]}
</#list>
${out}

<h1>macro+nested</h1>
<#-- 自定义指令 -->
<#macro print i>
<#-- 占位指令一般结合macro指令使用 -->
    <#nested>
    <#list 0..i as item>
        ${item}
    </#list>
</#macro>
<#-- 使用指令 -->
<@print i=5><h4>标题</h4></@print>

<h1>import</h1>
<#-- 导入命名空间 -->
<#import 'common.ftl' as common>
<#-- 使用导入的命名空间下的资源 -->
<@common.out></@common.out>
${common.key}

<h1>include</h1>
<#include 'header.ftl'>