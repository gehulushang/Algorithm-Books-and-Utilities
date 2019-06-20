package GeekLeetCode;

import java.util.*;

public class MyStack {
    private Queue<Integer> input ;
    private Queue<Integer>  output ;
    public MyStack(){
        input = new  LinkedList<>();
        output = new LinkedList<>();
    }
    public void push(int x ){
        input.offer(x);
    }
    public int pop(){
        while(input.size()>1){
            output.offer(input.poll());
        }
        int ans = input.poll();
        while(!output.isEmpty()){
            input.offer(output.poll());
        }
        return ans;
    }

    public int top(){
        while(input.size()>1){
                output.offer(input.poll());
        }
        int ans = input.peek();
        output.offer(input.poll());
        while(!output.isEmpty()){
            input.offer(output.poll());
        }
        return ans;
    }
    public boolean empty(){
        return input.isEmpty()&&output.isEmpty();
    }
}
