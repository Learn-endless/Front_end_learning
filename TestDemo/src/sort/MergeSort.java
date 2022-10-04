package sort;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 19833
 * Date: 2022-10-04
 * Time: 21:10
 */
public class MergeSort {


    /**
     * 归并排序
     * 稳定的排序
     * @param array 待排序数组
     */
    public static void mergeSort(int[] array){
        int num = array.length;
        if(num == 0 || num == 1) return;

        mergePart(array,0,num-1);
    }


    private static void mergePart(int[] array, int left, int right) {

        if(left >= right){
            return;
        }

        int mid = left+((right-left)>>>1);

        mergePart(array,left,mid);
        mergePart(array,mid+1,right);

        merge(array,left,mid,right);
    }


    private static void merge(int[] array, int left, int mid, int right) {
        int leftStart = left;
        int leftEnd = mid;
        int rightStart = mid+1;
        int rightEnd = right;

        int[] tmp = new int[right-left+1];
        int index = 0;
        while(leftStart <= leftEnd && rightStart <= rightEnd){
            if(array[leftStart] <= array[rightStart]){
                tmp[index++] = array[leftStart++];
            }else{
                tmp[index++] = array[rightStart++];
            }
        }

        while(leftStart <= leftEnd){
            tmp[index++] = array[leftStart++];
        }
        while(rightStart <= rightEnd){
            tmp[index++] = array[rightStart++];
        }

        // 重新赋值给 array
        for(int i = 0; i < (right-left+1); i++){
            array[left+i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        int[] array = {3,4,1,1,5,7,6};
        mergeCom(array);
        System.out.println(Arrays.toString(array));
    }

    public static void mergeCom(int[] array){
        int num = 1;
        while(num < array.length){
            for(int i = 0; i < array.length; i += num * 2){
                int left = i;
                int mid = left + num - 1;
                int right = mid + num;

                if(mid >= array.length){
                    mid = array.length-1;
                }

                if(right >= array.length){
                    right = array.length-1;
                }

                // 合并
                merge(array,left,mid,right);
            }
            num*=2;
        }
    }
}
