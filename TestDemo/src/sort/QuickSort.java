package sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 19833
 * Date: 2022-10-04
 * Time: 15:24
 */
public class QuickSort {

    /**
     * 快排
     * 不稳定排序
     *
     * @param arr 待排序数组
     */
    public static void quickSort(int[] arr){
        int num = arr.length;
        if(num == 0 || num == 1) return;
        quick(arr,0,num-1);
    }

    /**
     * 用来进行递归的方法
     * @param arr       待排序数据
     * @param left      左边界
     * @param right     右边界
     */
    private static void quick(int[] arr, int left, int right) {
        // 递归的结束条件
        if(left >= right){
            return;
        }

        // 可以采用的优化方式
        if(right - left + 1 < 1200){
            // 直接使用直接插入排序进行优化
            insertSort(arr,left,right);
            return;
        }

        // 拿到基准值并交换到数组的第一个元素
        int midValIndex = getMidValIndex(arr,left,right);
        // 交换
        swap(arr,left,midValIndex);

        // 找基准
        int midIndex = partition(arr,left,right);
        // 递归处理左边和右边
        quick(arr,left,midIndex-1);
        quick(arr,midIndex+1,right);

    }

    /**
     * 直接插入排序
     * @param arr    待排序数组
     * @param left   左边界
     * @param right  右边界
     */
    private static void insertSort(int[] arr, int left, int right) {
        for(int i = left+1; i <= right; i++){
            int tmp = arr[i];
            int j = i-1;
            for(;j >= 0; j--){
                if(arr[j] > tmp){
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }

            arr[j+1] = tmp;
        }
    }

    // 找基准采用挖坑法
    private static int partition(int[] arr, int left, int right) {
        int tmp = arr[left];
        while(left < right){
            // 从后往前找一个小于基准的值
            while(left < right && arr[right] >= tmp){
                right--;
            }
            // 找到了，就填充到左边
            arr[left] = arr[right];

            //从前往后找一个大于基准的值
            while(left < right && arr[left] <= tmp){
                left++;
            }

            // 找到了就填充进去
            arr[right] = arr[left];
        }
        arr[left] = tmp;
        return left;
    }

    private static int getMidValIndex(int[] arr, int left, int right) {
        int midIndex = left+(right-left)/2;

        if(arr[left] > arr[midIndex] && arr[right] > arr[left]
        || arr[left] < arr[midIndex] && arr[right] < arr[left]
        ){
            return left;
        }

        if(arr[left] > arr[midIndex] && arr[midIndex] > arr[right]
        || arr[left] < arr[midIndex] && arr[midIndex] < arr[right]
        ){
            return midIndex;
        }
        return right;
    }

    public static void swap(int[] arr,int left, int mid){
        int tmp = arr[left];
        arr[left] = arr[mid];
        arr[mid] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {8,9,1,0,5,7,3,5};
        quickSortCom(array);
        System.out.println(Arrays.toString(array));
    }


    // 快排：非递归版本（通过栈）
    public static void quickSortCom(int[] array){
        int num = array.length;
        if(num == 0 || num == 1) return;

        Stack<Integer> stack = new Stack<>();
        int left = 0;
        int right = num-1;
        // 找基准
        int partition = partition(array, left, right);

        //往栈里面放
        if(partition-1 > left){
            stack.push(left);
            stack.push(partition-1);
        }

        if(partition+1 < right){
            stack.push(partition+1);
            stack.push(right);
        }

        while(!stack.isEmpty()){
            right = stack.pop();
            left = stack.pop();
            // 找基准
            partition = partition(array, left, right);

            //往栈里面放
            if(partition-1 > left){
                stack.push(left);
                stack.push(partition-1);
            }

            if(partition+1 < right){
                stack.push(partition+1);
                stack.push(right);
            }
        }
    }
}
