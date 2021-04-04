package core.sunString;

import java.util.HashMap;

/**
 * 题目描述
 * 给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有数字都不相同)。
 *
 * https://www.nowcoder.com/practice/b56799ebfd684fb394bd315e89324fb4?tpId=117&&tqId=35074&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class MaxNotRepeatArrayLength {


    public static void main(String[] args) {
        Foo foo = new Foo();
        int[] arr = new int[]{1, 2, 2, 3, 3, 1};
        System.out.println(foo.maxLength(arr));
    }

    static class Foo {

        public int maxLength(int[] arr) {
            return maxLength1(arr);
        }

        // 方法二 **双指针+回头遍历**：
        public int maxLength1(int[] arr) {
            int tmpLength = 0;
            int maxLength = 0;
            int left = 0;
            int subLength = 0;
            //right指针往右移动
            for(int right = 0; right < arr.length ; right++){
                left = right - 1;
                subLength = 1; // 暂时保存子串长度
                //回头扫描，要是没有找到相同的，左指针一直倒退
                while(left >= 0){
                    if(arr[left] == arr[right]){
                        break;
                    }
                    left--;
                    subLength++;
                }
                //若当前子串长度比上一个字符时拥有的子串长度大，就tmp + 1，否则就设置为指针距离，方便下一步进行比较
                if(subLength > tmpLength){
                    tmpLength++;//若指针距离比上一个字符时拥有的子串长度大，就tmp + 1
                }else{
                    tmpLength = subLength;// 否则就设置为指针距离，重新计数
                }
                maxLength = Math.max(maxLength,tmpLength);// 找出最大值
            }

            return maxLength;
        }

        /**
         * 方法2 滑动窗口法：
         * <p>
         * 我们可以使用双指针模拟一个滑动窗口。
         * 初始化窗口为 (left, right]。 所以left从-1开始。
         * 窗口不断往右扩大。
         * 根据题目的要求，即遇到有重复数字的，在窗口左侧缩小。
         * 在每次滑动时，对窗口的大小进行比较，保留最大的长度。
         */
        public int maxLength2(int[] arr) {
            int tmpLength = 0;
            int maxLength = 0;
            int left = -1;
            int rightValue;
            //窗口自右向左播放，key：窗口存放过的数字 value:播放过的数字的最大索引
            HashMap<Integer,Integer> window = new HashMap<>();
            //窗口不断往右移
            for(int right = 0; right < arr.length ; right++){
                rightValue = arr[right];
                //根据题目，当遇到重复的数字时，缩小左侧窗口(最大串一定是在重复串的两侧)
                if(window.containsKey(rightValue)){
                    //因为我们有可能遇到索引比left原来还小的相同数字，
                    // 如[1,1,2,2,1],执行到最后一个arr[4]=1的时，left=2,window.get(1)=1,此时应该设置left=3.最长串=4-2=2
                    //所以这里要进行比较，目的还是为了缩小左侧窗口，确保窗口内全是不重复的数字
                    left = Math.max(left,window.get(rightValue));
                }
                //更新窗口内数字的索引
                window.put(rightValue,right);
                // right-left是窗口大小
                // 子串长度为right - left，不需要+1，因为每次for循环if判断时，k对应的v，始终是这个k上一次的v，而不是这个k的最新v，if之后才是
                //因为要找最长，所以要进行比较，
                maxLength = Math.max(maxLength,right-left);
            }
            return maxLength;
        }

    }
}
