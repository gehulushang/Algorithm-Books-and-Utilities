package GeekLeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargest {
    private final int k;
    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public KthLargest(int k, int[] nums) {
          this.k = k;
          for(Integer i : nums){
              minHeap.add(i);
              if(minHeap.size()>k){
                  minHeap.poll();
              }
          }
    }

    public int add(int val) {
        minHeap.add(val);
        if(minHeap.size()>k){
            minHeap.poll();
        }
        return minHeap.peek();
    }
}
