package structure;

public class BinarySearch {

    public static int upper_bound_ (int size, int exceptValue, int[] arrays) {

        if (arrays[size-1] < exceptValue){
            return size+1;
        }

        int left = 0;
        int right = size - 1;
        int mid = 0;

        while (left <= right) { // #如果，left==right了，证明左右两边重叠了，如果有这个值，就是left==right时，如果没有那么，直接跳出循环走后面的else就可以了
            mid = (left + right) / 2;
            if (arrays[mid] > exceptValue) {
                // 如果中间值的值，大于想要查找的值，那么想要的值在左侧，所以把右边的right要向左移动。
                // 移动到哪呢？中间值，都比n大，所以移动到middle的左边right=middle-1
                right = mid - 1;
            } else if (arrays[mid] < exceptValue) {
                // #中间值小于n，那么n就在右侧，所以向右移动left，移动到哪呢？中间值都小于n,所以left= middle+1
                left = mid + 1;
            }else{
                while(mid >=0 && arrays[mid] == exceptValue){
                    mid--;
                }
                return mid + 2;
            }
        }

        return mid+1;
    }


    public static void main(String[] args) {
        int[] arrays = new int[]{1,1,2,3,7,7,7,9,9,10};
//        int[] arrays = new int[]{1,2,3,4,4,5};
        System.out.println(upper_bound_(arrays.length,2,arrays));
    }

    /**
     * 描述：循环
     */
    int binarySearchByWhile(int size, int exceptValue, int[] arrays) {
        int left = 0;
        int right = size - 1;
        int mid = 0;

        while (left <= right) { // #如果，left==right了，证明左右两边重叠了，如果有这个值，就是left==right时，如果没有那么，直接跳出循环走后面的else就可以了
            mid = (left + right) / 2;
            if (arrays[mid] > exceptValue) {
                // 如果中间值的值，大于想要查找的值，那么想要的值在左侧，所以把右边的right要向左移动。
                // 移动到哪呢？中间值，都比n大，所以移动到middle的左边right=middle-1
                right = mid - 1;
            } else if (arrays[mid] < exceptValue) {
                // #中间值小于n，那么n就在右侧，所以向右移动left，移动到哪呢？中间值都小于n,所以left= middle+1
                left = mid + 1;
            }else {
                // 找到了
                break;
            }
        }

        return -1; // 未找到
    }


    /**
     * 描述：递归
     */
    private int binarySearchByRecursion(int left,int right,int[] arrays,int exceptValue){
        int mid=(left+right)/2;
        if (left > right){
            return mid;
        }

        if(arrays[mid] > exceptValue){
            right = mid-1;
            return binarySearchByRecursion(left,right,arrays,exceptValue);
        }else if(arrays[mid] < exceptValue){
            left = mid+1;
            return binarySearchByRecursion(left,right,arrays,exceptValue);
        }else{
            return mid;
        }
    }

}
