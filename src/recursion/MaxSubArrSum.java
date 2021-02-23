package recursion;

import java.util.Arrays;

/**
 * 描述：
 * 给定一个数组arr，返回子数组的最大累加和
 * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
 * 题目保证没有全为负数的数据
 * [要求]
 * 时间复杂度为O(n)O(n)O(n)，空间复杂度为O(1)O(1)O(1)
 * https://www.nowcoder.com/practice/554aa508dd5d4fefbf0f86e5fe953abd?tpId=117&&tqId=35068&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 *
 */
public class MaxSubArrSum {

    public static void main(String[] args) {
        int[] arr = new int[]{1, -2, 3, 5, -2, 6, -1};
        System.out.println(maxsumofSubarray(arr));
    }

    /**
     * max sum of the subarray
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public static int maxsumofSubarray(int[] arr) {
        int max = 0; // 记录历史最大值
        int subSum = 0; // 临时记录子数组之和与arr[i]之间的最大值
        for (int i = 0; i < arr.length; i++) {
            subSum = Math.max(subSum+arr[i],arr[i]);
            max = Math.max(subSum,max);
        }
        return max;
    }
}
