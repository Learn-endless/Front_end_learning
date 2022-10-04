import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 19833
 * Date: 2022-10-03
 * Time: 9:01
 */

public class TestDemo {
    public static void main1(String[] args) {

        int[] array = {27,15,19,18,28,34,65,49,25,37};

        MyHeap myHeap = new MyHeap();
        myHeap.createHeap(array);

        System.out.println(Arrays.toString(myHeap.getElem()));
    }


    public static boolean Find(int target, int [][] array) {

        if(array[0].length == 0) return false;

        int row = 0;
        int col = array[0].length - 1;

        while(row < array[0].length && col >= 0){

            if(array[row][col] > target){
                col--;

            } else if(array[row][col] < target){
                row++;
            }else{
                return true;
            }
        }

        return false;
    }

    public static int findPeakElement (int[] nums) {
        // write code here
        if(nums.length == 0) return -1;
        if(nums.length == 1) return 0;

        for(int i = 0; i < nums.length; i++){
            if(i == 0 && nums[i] > nums[i+1]){
                return 0;
            }else if(i + 1 == nums.length && nums[i] > nums[i-1]){
                return i;
            }else if(i != 0 && nums[i] > nums[i-1] && nums[i] > nums[i+1]){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] array = {{1,2,8,9}, {2,4,9,12},{4,7,10,13},{6,8,11,15}};
        boolean find = Find(7, array);
        System.out.println(find);

//        int[] array = {2,4,1,2,7,8,4};
//        int arr[] = {1,2,3};
//        System.out.println(Arrays.toString(arr));
//        int peakElement = findPeakElement(array);
//        System.out.println(peakElement);
    }
}
