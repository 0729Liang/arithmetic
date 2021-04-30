package core.sort;

import java.util.*;

/**
 * 描述：
 * <p>
 * Java的几种常见排序算法:https://www.cnblogs.com/ll409546297/p/10956960.html
 * 常见的内部排序算法有：冒泡排序、选择排序、插入排序、希尔排序、快速排序、归并排序等。
 * <p>
 * 链接：https://leetcode-cn.com/problems/sort-an-array/solution/pai-xu-shu-zu-by-leetcode-solution/
 */
public class Sort {

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] arr1 = new int[]{3, 2, 6, 5, 1, 8, 7};
        int[] arr2 = new int[]{3, 2, 6, 5, 1, 8, 7};
        int[] arr3 = new int[]{3, 2, 6, 5, 1, 8, 7};
        int[] arr4 = new int[]{3, 2, 6, 5, 1, 8, 7};
        int[] arr = new int[]{6,5,4,3, 2, 1};
        int[] arrr = new int[]{6,5,4,3, 2, 1};
        System.out.println(Arrays.toString(sort.maopao(arrr)));
    }

    private void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    /**
     * 冒泡排序
     * 两层for循环
     * 外层循环控制循环次数i，内层循环进行第0到第length-i个元素比较，
     * 每次比较将最大值放到length-i-1处，当外层循环结束时，数组便排好了顺序。
     * 说明：两层循环，外层控制循环次数，内层循环用于比较，数组尾部存放最大值。
     */
    private int[] maopao(int[] arr) {
        //外层循环，遍历次数
        for (int i = 0; i < arr.length; i++) {
            // 注意：内层循环的结束位置是arr.length - i，此处是每次循环后的最大值，arr.length - i及之后是已经排序好的数组
            for (int j = 1; j < arr.length - i; j++) {
                //内层循环，如果当前值比前一个值大，则交换
                //内层循环一次，获取一个最大值,放到队尾(arr.length - i)
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j - 1, j);
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序
     * 两层for循环
     * 外层循环控制循环次数i，每次循环的i处默认是最小值
     * 内层循环用于找出第i+1到length之间的最小值和索引
     * 在外层循环最后将i与最小值索引交换位置，当外层循环结束时，数组便排好了顺序。
     * 说明：每次遍历的时候，将前面找出的最小值，看成一个有序的列表，后面的看成无序的列表，然后每次遍历无序列表找出最小值，然后交换到有序列表末尾。
     */
    private int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i]; //默认第一个是最小的。
            int minIndex = i; //记录最小的下标
            //通过与后面的数据进行比较得出，最小值和下标
            for (int j = i + 1; j < arr.length; j++) { // 注意：内层循环的起始位置是i+1，i及之前是已经排序好的数组
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);  //然后将最小值与本次循环的，开始值交换
            // 说明：将i前面的数据看成一个排好的队列，i后面的看成一个无序队列。每次只需要找无需的最小值，做替换
        }
        return arr;
    }

    /**
     * 插入排序
     * 两层for循环
     * 外层控制比较次数i，默认第一个元素认为是有序的，从第二个元素开始，
     * 内层循环将i与前i-1个元素进行比较，将其插入到合适的位置。
     * 说明：默认将第一数据看成有序列表，后面无序的列表循环每一个数据，如果比前面的数据小则插入（交换）。否则退出。
     */
    private int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //外层循环，从第二个开始比较
            for (int j = i; j > 0; j--) {
                //内存循环，与前面排好序的数据比较，如果后面的数据小于前面的则交换
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j - 1, j);
                } else {
                    //如果不小于，说明插入完毕，退出内层循环
                    break;
                }
            }
        }
        return arr;
    }


    /**
     * 快速排序(Quick Sort)：是对冒泡排序的一种改进方法，
     * 在冒泡排序中，进行元素的比较和交换是在相邻元素之间进行的，元素每次交换只能移动一个位置，所以比较次数和移动次数较多，效率相对较低。
     * 而在快速排序中，元素的比较和交换是从两端向中间进行的，较大的元素一轮就能够交换到后面的位置，而较小的元素一轮就能交换到前面的位置，元素每次移动的距离较远，所以比较次数和移动次数较少，y速度较快，故称为“快速排序”。
     *
     * 快速排序的主要思想是:
     * 在待排序的元素一个元素作为基准元素，通常选第一个元素；
     * 将待排序的元素进行分区，比基准元素大的元素放在它的右边，比其小的放在它的左边；
     * 对左右两个分区重复以上步骤直到所有元素都是有序的
     *
     * 操作步骤
     * a、确认列表第一个数据为中间值，第一个值看成空缺（低指针空缺）。
     * b、然后在剩下的队列中，看成有左右两个指针（高低）。
     * c、开始高指针向左移动，如果遇到小于中间值的数据，则将这个数据赋值到低指针空缺，并且将高指针的数据看成空缺值（高指针空缺）。然后先向右移动一下低指针，并且切换低指针移动。
     * d、当低指针移动到大于中间值的时候，赋值到高指针空缺的地方。然后先高指针向左移动，并且切换高指针移动。重复c、d操作。
     * e、直到高指针和低指针相等时退出，并且将中间值赋值给对应指针位置。
     * f、然后将中间值的左右两边看成行的列表，进行快速排序操作。
     */
    private void quickSort(int[] arr, int left, int right) {
        // 递归结束的条件
        if (left >= right) {
            return;
        }
        int baseValue = arr[left];// 设置低指针第一个数为基准值
        int low = left; // 循环时的低指针
        int high = right; // 循环时的高指针
        while (low < high) { // 注意是小于，如果等于时，会进入死循环
            // a:从高向低找，直到找到第一个小于基准值的数，并与低指针交换
            while (high > low && arr[high] >= baseValue) {
                high--;
            }

            swap(arr, low, high);

            // b:从低向高找，直到找到第一个大于基准值的数，并与高指针交换
            while (low < high && arr[low] <= baseValue) {
                low++;
            }

            swap(arr, low, high);
            // 重复ab操作，直到low>=high
        }
        quickSort(arr, left, low - 1);  // 递归调用，对低子数组进行快速排序
        quickSort(arr, low + 1, right);  // 递归调用，对高子数组进行快速排序
    }

    /**
     * 归并排序的主要思想是:
     * 对一个长为 n 的待排序的序列，我们将其分解成两个长度为 n/2 的子序列。
     * 每次先递归调用函数使两个子序列有序，然后我们再线性合并两个有序的子序列使整个序列有序。
     *
     * 操作步骤
     * a、将列表按照对等的方式进行拆分
     * b、拆分小最小快的时候，在将最小块按照原来的拆分，进行合并
     * c、合并的时候，通过左右两块的左边开始比较大小。小的数据放入新的块中
     * d、说明：简单一点就是先对半拆成最小单位，然后将两半数据合并成一个有序的列表。
     */
    public static void mergerSort(int[] arr, int left, int right) {
        if (left >= right) { // 递归结束的条件
            return;
        }
        int mid = (left + right) / 2; // 找到中间位置
        mergerSort(arr, left, mid); // 左区间
        mergerSort(arr, mid + 1, right); // 右区间

        int start = left;
        int end = mid + 1;
        int tmpIndex = 0; // 记录临时数组的指针
        int[] tempArr = new int[right - left + 1];
        // 核心：同时比较左右指针上的数，将较小的数放到tmp数组，同时较小数的指针加一，继续循环，直到一方超过边界
        while (start <= mid && end <= right) {
            if (arr[start] > arr[end]) {
                tempArr[tmpIndex++] = arr[end++];
            } else {
                tempArr[tmpIndex++] = arr[start++];
            }
        }

        // 如果左指针没出界，则将左指针剩余的数，放到tmp数组
        while (start <= mid) {
            tempArr[tmpIndex++] = arr[start++];
        }
        // 如果右指针没出界，则将右指针剩余的数，放到tmp数组
        while (end <= right) {
            tempArr[tmpIndex++] = arr[end++];
        }

        // 最后将tmp数组的数，还给原数组
        for (int i = 0; i < tmpIndex; i++) {
            arr[left + i] = tempArr[i];
        }
    }


}
