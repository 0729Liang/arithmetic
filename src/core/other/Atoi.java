package core.other;

/**
 * 描述：
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 *
 * 函数 myAtoi(string s) 的算法如下：
 *
 *     读入字符串并丢弃无用的前导空格
 *     检查第一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 *     读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 *     将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 *     如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 *     返回整数作为最终结果。
 *
 * 注意：
 *
 *     本题中的空白字符只包括空格字符 ' ' 。
 *     除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Atoi {

    public static void main(String[] args) {
        System.out.println(atoi("    -11333713950"));
        System.out.println(atoi("2147483646"));
        System.out.println(atoi("2147483647"));
        System.out.println(atoi("2147483648"));
        System.out.println(atoi("2147483650"));
        System.out.println(atoi("-2147483646"));
        System.out.println(atoi("-2147483647"));
        System.out.println(atoi("-2147483648"));
        System.out.println(atoi("-2147483649"));
        System.out.println(atoi("-2147483650"));
    }
    /**
     *
     * @param str string字符串
     * @return int整型
     */
    public static int atoi (String str) {
        if (str == null || str.length() <= 0){
            return 0;
        }
        str = str.trim(); // 去掉前后空格
        // 去掉空格后需要在判断一次长度，防止越界
        if(str.length() <= 0){
            return 0;
        }
        int result = 0;
        int sign = 1; // 标记正负
        int index = 0; // 下标索引
        // 判断正负
        if(str.charAt(index) == '-'){
            sign = -1;
            index++;
        }else if (str.charAt(index) == '+'){
            sign = 1;
            index++;
        }

        while(index < str.length()){
            char c = str.charAt(index);
            if(c >= '0' && c <= '9'){ // 字符需要在0-9之间
                // max:2147483647 min:-2147483648
                // 判断边界：注意此处需要先判断，再求和，因为可能出现求和的过程中溢出
                // 1. 10位数之前相同，且个位数>=7,不论正负，一定出界
                // 2. 10位数之前如果小于result,则出界
                if ((result == Integer.MAX_VALUE / 10 && str.charAt(index)-'7' > 0) || result > Integer.MAX_VALUE/10){
                    return sign == 1?Integer.MAX_VALUE:Integer.MIN_VALUE;
                }
                result = result*10+(c-'0'); // 计算每轮的结果
            }else{
                // 非字符直接跳出
                break;
            }
            index++;
        }
        return result*sign;
    }
}
