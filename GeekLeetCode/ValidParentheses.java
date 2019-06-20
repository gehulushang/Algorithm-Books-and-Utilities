package GeekLeetCode;

import java.util.ArrayList;
import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(stack.empty()&&(c==')'||c==']'||c=='}')){
                return false;
            }
            switch(c){
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')' :
                    if (stack.peek() == '(') {
                        stack.pop();
                        break;
                    }
                    else return false;
                case (']') :
                    if (stack.peek() == '[') {
                        stack.pop();
                        break;
                    }
                    else return false;
                case ('}') :
                    if (stack.peek() == '{') {
                        stack.pop();
                        break;
                    }
                    else return false;
            }
        }
        if(stack.empty()){
            return true;
        }else{
            return false;
        }

    }
}
