package com.hsiaoweiyun.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author VictorHsiao on 2019/11/5.
 */
public class ValidParentheses {

    private final static Map<Character, Character> BRACKETS = new HashMap<>(3);

    static {
        BRACKETS.put('(', ')');
        BRACKETS.put('{', '}');
        BRACKETS.put('[', ']');
    }

    @Test
    public void test(){
        Assert.assertEquals(true, isValid("()"));
        Assert.assertEquals(true, isValid("()[]{}"));
        Assert.assertEquals(false, isValid("(]"));
        Assert.assertEquals(false, isValid("([)]"));
        Assert.assertEquals(true, isValid("{[]}"));
    }

    public boolean isValid(String s) {
        char[] brackets  = s.toCharArray();
        List<Character> stack = new ArrayList<>(brackets.length);
        for(char bracket : brackets){
            if(Objects.nonNull(BRACKETS.get(bracket))){
                stack.add(bracket);
            }else {
                if(stack.isEmpty()){
                    return false;
                }

                int lastIndex = stack.size() - 1;
                Character target = stack.get(lastIndex);
                stack.remove(lastIndex);
                if(!BRACKETS.get(target).equals(bracket)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
