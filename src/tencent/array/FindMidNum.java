package tencent.array;

import core.been.TreeNode;

import java.util.Arrays;

public class FindMidNum {

    private int getNodeSize(TreeNode root){
        if (root == null){
            return 0;
        }
        return getNodeSize(root.left)+getNodeSize(root.right)+1;
    }

    /**
     * 描述：返回二叉树的深度
     */
    public int getTreeNodeDeep(TreeNode root) {
        if (root == null){
            return 0;
        }
        return Math.max(getTreeNodeDeep(root.left),getTreeNodeDeep(root.right))+1;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,3};
        int[] arr2 = new int[]{2};
        System.out.println(findMedianSortedArrays(arr1,arr2));
    }
    /**
     * 思路 1： 先排序，后中中间位置（分奇偶情况）
     * 思路 2：动态，三个数据中一定有一个中等大小的数
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] arr = merge(nums1,nums2);
        System.out.println("merge arr="+ Arrays.toString(arr));
        int len = arr.length;
        if (len%2==0){
            return (arr[len/2-1]+arr[len/2])*1.0d/2;
        }else{
            return arr[len/2]*1.0d;
        }
    }


    public static int[] merge(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length+nums2.length];
        int p1=0,p2=0;
        int cur;
        while(p1 < nums1.length || p2 < nums2.length){
            if(p1 == nums1.length){
                cur = nums2[p2++];
            }else if(p2 == nums2.length){
                cur = nums1[p1++];
            }else if (nums1[p1] < nums2[p2]){
                cur = nums1[p1++];
            }else{
                cur = nums2[p2++];
            }
            arr[p1+p2-1] = cur;
        }
        return arr;
    }


    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

}
