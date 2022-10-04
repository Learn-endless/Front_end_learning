package sort;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 19833
 * Date: 2022-10-04
 * Time: 11:07
 */
public class SelectSort {
    /**
     * 选择排序
     * 不稳定的
     * @param arr 待排序数组
     */
    public static void selectSort(int[] arr){
        int num = arr.length;
        if(num == 0 || num == 1) return;

        for(int i = 0; i < num; i++){
            int minIndex = i;
            for(int j = i+1; j < num; j++){
                // 每次那最当前找到的最小的值来进行比较，去最小值的下标
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {8,9,1,0,5,7,3,5};
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }
}
