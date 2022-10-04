package sort;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 19833
 * Date: 2022-10-04
 * Time: 14:38
 */
public class ShellSort {

    /**
     * 希尔排序
     * 不稳定的排序（跨越式交换）
     * 其实底层就是直接插入排序，然后将待排序数据分成多组
     * 注意：最后一定要分成一组（前面的分组可以看成预排序）
     * @param arr  待排序数组
     */
    public static void shellSort(int[] arr){
        int num = arr.length;
        if(num == 0 || num ==1) return;

        while(num > 1){
            shell(arr,num);
            num = num/2;
        }
        shell(arr,1);
    }

    private static void shell(int[] arr, int group) {
        int num = arr.length;

        for(int i = group; i < num; i+=group){
            // 留存一个副本
            int tmp = arr[i];
            int j = i - 1;
            for(; j >= 0; j-=group){
                // 比较
                if(arr[j] > tmp){
                    arr[j+1] = arr[j];

                }else{
                    break;
                }
            }
            arr[j+1] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] array = {5,6,7,2,0,1,1,5,4};
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }
}
