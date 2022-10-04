package sort;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 19833
 * Date: 2022-10-04
 * Time: 14:57
 */
public class HeapSort {
    /**
     * 堆排序
     * 不稳定的排序
     * 主要就是向下调整的过程
     * @param arr 待排序数据
     */
    public static void heapSort(int[] arr){
        int num = arr.length-1;
        if(num == 0 || num == 1) return;

        // 先要建堆
        createHeap(arr);

        // 排序
        while(num >= 0){
            // 交换堆顶元素和最后的叶子节点的值
            int tmp = arr[0];
            arr[0] = arr[num];
            arr[num] = tmp;
            // 再调整
            shiftDown(arr,0,num);
            num--;
        }
    }

    /**
     * 建堆（建一个完全二叉树）
     * @param arr 原始数据
     */
    private static void createHeap(int[] arr) {
        for(int parent = (arr.length-1-1)/2; parent >= 0; parent--){
            shiftDown(arr,parent,arr.length);
        }
    }

    /**
     * 向下调整（以 child < arr.length 为界限）
     * 这个方法用来调整 根节点 为 parent 的完全二叉树
     * @param arr        完全二叉树
     * @param parent     根节点
     * @param num        最后一个节点的下标
     */
    private static void shiftDown(int[] arr, int parent, int num) {
        int child = 2*parent+1;
        // 调整
        while(child < num){
            // 找到左右孩子最小值
            if(child+1 < num && arr[child+1] > arr[child]){
                child = child+1;
            }

            if(arr[parent] < arr[child]){
                int tmp = arr[parent];
                arr[parent] = arr[child];
                arr[child] = tmp;
                parent = child;
                child = 2*parent+1;
            }else{
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {5,6,7,2,0,1,1,5,4};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
