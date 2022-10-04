import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 19833
 * Date: 2022-10-04
 * Time: 20:22
 */
public class TestDemo2 {

    public static void quickSort(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int left = 0;
        int right = array.length-1;
        int pivot = partition(array,left,right);
        if(pivot > left+1) {
            //左边有2个元素
            stack.push(left);
            stack.push(pivot-1);
        }
        if(pivot < right-1) {
            //右边有2个元素
            stack.push(pivot+1);
            stack.push(right);
        }

        while (!stack.isEmpty()) {
            right = stack.pop();
            left = stack.pop();

            pivot = partition(array,left,right);

            if(pivot > left+1) {
                //左边有2个元素
                stack.push(left);
                stack.push(pivot-1);
            }
            if(pivot < right-1) {
                //右边有2个元素
                stack.push(pivot+1);
                stack.push(right);
            }
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

}
