import java.util.*;

public class Test {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("aba"));
    }

    public static  String longestPalindrome(String s) {
        String res="";
        for(int i=0; i< s.length(); i++){
            // 比较以i为中心扩散的回文子串 和 以i和i+1为中心扩散的回文子串， 哪个子串长取哪个
            String odd = centerSpread(s,i,i+1); // 偶数回文串,abba
            String even = centerSpread(s,i,i); // 奇数回文串,aba
            // 找出本轮最长的字符串
            String str = (odd.length() > even.length())?odd:even;
            // 记录历史最长的字符串
            if (str.length() > res.length()){
                res = str;
            }
        }
        return res;
    }

    // 中心扩散法
    //若left==right 则扩散中点为一个，此时的回文子串为奇数
    //若left!=right，则扩散的中点为 left和right，此时的回文子串为偶数
    public static String centerSpread(String s,int left,int right){
        int length = s.length();
        // 控制指针范围，防止越界
        while((left >= 0 && left < length ) && (right >= 0 && right < length )){
            if (s.charAt(left) == s.charAt(right)){
                left--;
                right++;
            }else{
                break;
            }
        }
        //上面while循环终止了，此时s.charAt(left) != s.charAt(right)
        //所以此时的回文子串的左右边界其实是  l-1，  r-1
        //substring左闭右开
        // int length = right-left-2+1;
        System.out.println("left:"+left+" right:"+right);
        return s.substring(left+1,right);
    }
}
