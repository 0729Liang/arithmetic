package core.other;

import java.util.Arrays;

public class FindMinPositive {


    public static void main(String[] args) {
        FindMinPositive findMinPositive = new FindMinPositive();
        System.out.println(8 >> 2);
        System.out.println(3 >> 1);
//        System.out.println(findMinPositive.firstMissingPositive(new int[]{-1,4,2,1,9,10}));

    }
    public int firstMissingPositive(int[] nums) {
        int length = nums.length;

        // 原地hash
        for(int i = 0; i < length; i++){
            while (nums[i] > 0 && nums[i] < length && nums[i] != nums[nums[i]-1]){
                System.out.println("i:"+i+" num[i]-1:"+(nums[i]-1)+ " arr:"+ Arrays.toString(nums));
                swap(nums,nums[i]-1,i);
                System.out.println("res:"+Arrays.toString(nums));
            }
        }

        // 查找
        for(int i = 0; i < length; i++){
            if (nums[i] != i+1){
                return i+1;
            }
        }

        return length+1;
    }

    private void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j]=tmp;
    }

}
