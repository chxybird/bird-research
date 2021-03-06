package com.bird.question;

import java.util.Stack;

/**
 * @Author lipu
 * @Date 2021/3/6 17:25
 * @Description 括号的匹配
 */
public class _20括号的匹配 {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。

     * 示例 1:
     * // 输入: "()"
     * //输出: true

     * 示例 2:
     * 输入: "()[]{}"
     * 输出: true

     * 示例 3:
     * 输入: "(]"
     * 输出: false

     * 示例 4:
     * 输入: "([)]"
     * 输出: false

     * 示例 5:
     * 输入: "{[]}"
     * 输出: true
     * Related Topics 栈 字符串
     */

    /**
     * @Author lipu
     * @Date 2021/3/6 17:26
     * @Description 解决方案
     */
    public boolean solution(String s){
        //快速失败
        if (s.length()%2!=0){
            //奇数不符合条件
            return false;
        }

        //括号匹配
        Stack<Character> stack=new Stack<Character>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i <charArray.length ; i++) {
            //左括号入栈
            if ('{'==charArray[i]||'['==charArray[i]||'('==charArray[i]){
                stack.push(charArray[i]);
            }
            //右括号则出栈进行比对
            if ('}'==charArray[i]||']'==charArray[i]||')'==charArray[i]){
                //输入的直接是右括号导致栈为空
                if (stack.isEmpty()){
                    return false;
                }
                Character popItem = stack.pop();
                //{}匹配
                if (charArray[i]=='}'){
                    if (popItem!='{'){
                        return false;
                    }
                }
                //[]匹配
                if (charArray[i]==']'){
                    if (popItem!='['){
                        return false;
                    }
                }
                //()匹配
                if (charArray[i]==')'){
                    if (popItem!='('){
                        return false;
                    }
                }
            }
        }
        //最后校验
        if (stack.isEmpty()){
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {
        _20括号的匹配 client=new _20括号的匹配();
        String input="{{{{";
        boolean output = client.solution(input);
        System.out.println(output);
    }
}
