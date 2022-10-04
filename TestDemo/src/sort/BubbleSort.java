package sort;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 19833
 * Date: 2022-10-04
 * Time: 10:12
 */
public class BubbleSort {
    /**
     * 冒泡排序
     * 稳定的
     * @param arr  接收的整形数组
     */
    public static void bubSort(int[] arr){
        // 没有元素或元素个数为1,就不需要排序
        if(arr.length == 0 || arr.length == 1) return;
        // 多数情况
        int num = arr.length;

        for(int i = 0; i < num-1; i++){
            // 优化
            boolean flg = false;
            for(int j = 0; j < num-1-i;j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    flg = true;
                }
            }
            if(!flg){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {3,4,1,1,5,7,6};
        bubSort(array);
        System.out.println(Arrays.toString(array));
    }
}
