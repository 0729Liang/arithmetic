package sunString;


/**
 * 描述：
 * 对于一个字符串，请设计一个高效算法，计算其中最长回文子串的长度。
 * 给定字符串A以及它的长度n，请返回最长回文子串的长度。
 *
 * https://www.nowcoder.com/practice/b4525d1d84934cf280439aeecc36f4af?tpId=117&&tqId=35044&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class MaxRepeatStringLength {

    public static void main(String[] args) {
        Foo1 foo = new Foo1();
        String s = "baaba1234321ab";
        System.out.println(foo.getLongestPalindrome(s, s.length()));
    }

    /**
     * 法1：中心扩散
     * 动态规划 时间复杂度(O^2)
     * 回文的长度可能会出现奇偶数，分两步分别找寻奇偶最大回文字符。
     * 如果R-L+1-2 > max,重新赋值max,因为从0开始所以加1,减2是因为跳出循环时，left--和right++,用right-left会导致有个2的偏差
     * 第一步(计算奇数长度)：两个指针，L指向最左边，R指向比L多1，如果指向的字符相等分别向两边扩散继续比较， R-L+1-2 > max,重新赋值max.
     * 第二步(计算偶数长度)：两个指针，因为奇数，L指向最左边，R指向比右边，LR的起始值相同，如果指向字符相等分别向两边扩散继续比较， R-L+1-2 > max,重新赋值max.
     * 第三步：从前面两步找出最大的max
     */
    static class Foo{
        int maxLength = 0;

        public int getLongestPalindrome(String A, int n) {
            // write code here
            for (int i = 0; i < n - 1; i++) {
                int odd = centerSpread(A, n, i, i + 1);
                int even = centerSpread(A, n, i, i);
                int max = Math.max(odd, even);
                maxLength = Math.max(max, maxLength);
            }
            return maxLength;
        }

        public int centerSpread(String A, int n, int left, int right) {
            int length = 0;
            while ((left >= 0 && left <= (n - 1)) && (right >= 0 && right <= (n - 1))) {
                if (A.charAt(left) == A.charAt(right)) {
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            length = right - left + 1 - 2;
            return length;
        }
    }


    // 法2：暴力破解
    static class Foo1{
        int maxLength = 0;

        public int getLongestPalindrome(String A, int n) {
            // write code here
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j <= n; j++) {
                    String now = A.substring(i, j);
                    if (check(now) && now.length() > maxLength){
                        maxLength = now.length();
                    }
                }
            }
            return maxLength;
        }

        //判断子串是不是回文子串
        public boolean check(String now) {
            for (int i = 0; i < now.length() / 2; i++) {
                if (now.charAt(i) != now.charAt(now.length()-i-1)){
                    return false;
                }
            }
            return true;
        }
    }


}
