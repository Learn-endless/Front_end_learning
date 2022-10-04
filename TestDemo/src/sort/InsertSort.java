package sort; /**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 19833
 * Date: 2022-10-04
 * Time: 11:23
 */

import java.util.Arrays;

/**
 * 直接插入排序
 * 稳定的
 */
public class InsertSort {
    public static void insertSort(int[] arr){
        int num = arr.length;
        if(num == 0 || num == 1) return;

        for(int i = 1; i < num; i++){
            // 临时存放 i 下标的值
            int tmp = arr[i];

            // 遍历前面的所有值
            int j = i - 1;
            for(;j >= 0; j--){
                // 只要比 i 下标的值大,那么就往后移
                if(arr[j] > tmp){
                    arr[j+1] = arr[j];

                }else{
                    // 一旦有出现比 i 下标的值小的话
//                    arr[j+1] = tmp;
                    break;
                }
            }
            // 走到这里说明 j 来到了 -1 下标
            arr[j+1] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] array = {5,7,6,0,1,3,11,2,2};
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }
}
