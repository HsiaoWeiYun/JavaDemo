package com.hsiaoweiyun.leetcode;

import java.util.*;

public class ValidParentheses {

//    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//    An input string is valid if:
//
//    Open brackets must be closed by the same type of brackets.
//    Open brackets must be closed in the correct order.
//    Every close bracket has a corresponding open bracket of the same type.
//
//
//    Example 1:
//
//    Input: s = "()"
//    Output: true
//    Example 2:
//
//    Input: s = "()[]{}"
//    Output: true
//    Example 3:
//
//    Input: s = "(]"
//    Output: false
//
//
//    Constraints:
//
//            1 <= s.length <= 104
//    s consists of parentheses only '()[]{}'.

    //我自己的答案, 概念上差不多, 但別人寫的比較好, 也比較省記憶體
    public static boolean isValid1(String s) {

        Map<Character, Character> endMapping = new HashMap<Character, Character>(3){{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        char[] characters = s.toCharArray();
        //不是偶數一定不對
        if(characters.length % 2 != 0){
            return false;
        }

        char[] stack = new char[characters.length];
        int stackIdx = 0;

        for (int i = 0; i < characters.length; i++) {

            //該進
            if(Objects.isNull(endMapping.get(characters[i]))){
                stack[stackIdx++] = characters[i];
            }else {
                if(stackIdx == 0){
                    return false;
                }
                //該彈
                char tmp = stack[--stackIdx];
                if(Objects.nonNull(endMapping.get(tmp))){
                    return false;
                }else {
                    if(endMapping.get(characters[i]) != tmp){
                        return false;
                    }
                }
            }
        }

        return stackIdx == 0;
    }


    //別人的答案, 比較簡潔
    public static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<Character>(); // create an empty stack
        for (char c : s.toCharArray()) { // loop through each character in the string
            if (c == '(') // if the character is an opening parenthesis
                stack.push(')'); // push the corresponding closing parenthesis onto the stack
            else if (c == '{') // if the character is an opening brace
                stack.push('}'); // push the corresponding closing brace onto the stack
            else if (c == '[') // if the character is an opening bracket
                stack.push(']'); // push the corresponding closing bracket onto the stack
            else if (stack.isEmpty() || stack.pop() != c) // if the character is a closing bracket
                // if the stack is empty (i.e., there is no matching opening bracket) or the top of the stack
                // does not match the closing bracket, the string is not valid, so return false
                return false;
        }
        // if the stack is empty, all opening brackets have been matched with their corresponding closing brackets,
        // so the string is valid, otherwise, there are unmatched opening brackets, so return false
        return stack.isEmpty();
    }

    public static void main(String[] args){
        System.out.println(isValid1("()"));
        System.out.println(isValid1("()[]{}"));
        System.out.println(isValid1("(]"));
        System.out.println(isValid1("()))"));
        System.out.println(isValid1("())"));
        System.out.println(isValid1("(()){[{}]}()"));
    }

}
