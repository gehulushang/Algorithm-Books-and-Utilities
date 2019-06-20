package GeekLeetCode;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n==0){
            return new int[]{};
        }
        int[] result = new int[n-k+1];
        int max = findMax(nums,0,k-1);
        for(int i = 0,j=k;j<n;i++,j++){
            result[i] = max;
            max = findMax(nums,i+1,j);
        }
        result[n-k] = max;
        return result;

    }
    public int findMax(int[] nums,int i,int j){
        int max = Integer.MIN_VALUE;
        for(int m = i;m<=j;m++){
            if(nums[m]<max){
                max = nums[m];
            }
        }
        return  max;
    }
}
