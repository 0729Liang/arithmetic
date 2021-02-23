package hash;

import java.util.HashMap;

/**
 * 描述：
 * 给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
 *  你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
 *  假设给出的数组中只存在唯一解
 *  例如：
 *
 *  给出的数组为 {20, 70, 110, 150},目标值为90
 *  输出 index1=1, index2=2
 *  https://www.nowcoder.com/practice/20ef0972485e41019e39543e8e895b7f?tpId=117&&tqId=34983&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class FindTwoNumSum {

    /**
     *
     * @param numbers int整型一维数组
     * @param target int整型
     * @return int整型一维数组
     */
    public int[] twoSum (int[] numbers, int target) {
        HashMap<Integer,Integer> map = new HashMap<>(numbers.length); // key=num value=index
        for (int i = 0; i < numbers.length; i++) {
            // 如果map中存在一个(target-numbers[i]),则说明找到一组数据了
            if (map.containsKey(target- numbers[i])){
                return new int[]{map.get(target-numbers[i])+1,i+1};
            }
            map.put(numbers[i],i);// 保存每个数的索引
        }
        return null;
    }
}
