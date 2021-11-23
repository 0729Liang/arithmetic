package tencent.str;

public class MaxLengthRepeatStr {

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(s.substring(0,1));
        System.out.println(s.substring(2,3));
        System.out.println(s.substring(3,3));
        System.out.println(longestPalindrome("cdd"));
    }
    public static String longestPalindrome(String s) {
        String maxStr="";
        for(int i=0; i < s.length(); i++){
//            System.out.println(i);
            // 偶数回文串
            String even = centerFinder(s,i,i+1);
            // 奇数回文串
            String odd = centerFinder(s,i,i);
            // 临时保存本轮最长回文串
            String str = even.length() > odd.length() ? even : odd;
            // 记录最长回文串
            maxStr = maxStr.length() > str.length() ? maxStr : str;
        }
        return maxStr;
    }

    public static String centerFinder(String s,int left,int right){
//        System.out.println("s: l:"+left+" r:"+right);
        // 注意指针越界问题
        while ((left >= 0 && left < s.length()) && (right >= 0 && right < s.length())){
            if (s.charAt(left) == s.charAt(right)){
                System.out.println("xxx");
                left--;
                right++;
            }else{
                break;
            }
        }
//        System.out.println("e: l:"+left+" r:"+right);
        /**
         * 注意：截取位置应该是[left+1,right-1]
         * 由于substring是左闭右开截取，所以是substring(left+1,right)
         */
        return s.substring(left+1,right);
    }

}
