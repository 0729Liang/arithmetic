package core.recursion;

/**
 * 描述：
 * 给定两个字符串str1和str2,输出两个字符串的最长公共子串，如果最长公共子串为空，输出-1。
 *
 * https://www.nowcoder.com/practice/f33f5adc55f444baa0e0ca87ad8a6aac?tpId=117&&tqId=35268&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class MaxCommonSubStr {

    public String LCS(String str1, String str2) {
        // 动态规划
        int maxLen = 0;// 最大值长度
        int maxIndex = -1;// 最大值索引
        int[][] arr = new int[str1.length()][str2.length()]; // 二维数组斜对角线，最长的即是最长公共子序列
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) != str2.charAt(j)) {
                    continue;
                }
                if (i==0 || j==0){ // 第一个行或第一列为1
                    arr[i][j]=1;
                }else {
                    arr[i][j] = arr[i-1][j-1]+1;
                }
                if (maxLen < arr[i][j]){ // 更新最大值相关信息
                    maxLen = arr[i][j];
                    maxIndex = i;
                }
            }
        }
        if (maxLen <= 0) {
            return "-1";
        }
        return str1.substring(maxIndex-maxLen+1,maxIndex+1);
    }
}
