package recursion;

/**
 * 描述：
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * https://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4?tpId=117&&tqId=34990&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class DanceSteps {

    public static void main(String[] args) {
        DanceSteps danceSteps = new DanceSteps();
        System.out.println(danceSteps.JumpFloor(4));
    }

    public int JumpFloor(int target) {
        //动态规划 自底向上循环求解
        int[] arr = new int[target+1];
        arr[0]=1;
        arr[1]=1;
//        arr[2]=2;
        for (int i = 2; i <= target; i++) {
            arr[i]=arr[i-1]+arr[i-2];
        }
        return arr[target];
    }

    public int JumpFloor2(int target) {
        // 递归
        if (target<=1){
            return 1;
        }
        return JumpFloor2(target-1)+JumpFloor2(target-2);
    }
}
