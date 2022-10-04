import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 19833
 * Date: 2022-10-03
 * Time: 9:05
 */


// 手动创建一个优先级队列(大根堆)
public class MyHeap {
    private int[] elem;
    private int userSize;

    // 初始大小为10个元素空间
    public MyHeap(){
        this.elem = new int[10];
    }

    public int[] getElem() {
        return elem;
    }

    public void setElem(int[] elem) {
        this.elem = elem;
    }

    public int getUserSize() {
        return userSize;
    }

    public void setUserSize(int userSize) {
        this.userSize = userSize;
    }

    // 建堆
    public void createHeap(int[] array){
        for(int i = 0; i < array.length; i++){
            this.elem[i] = array[i];
            this.userSize++;
        }

        // 进行调整
        for(int parent = (this.userSize-1-1)/2; parent >= 0; parent--){
            shiftDown((parent));
        }
    }


    private void shiftDown(int parent){
        //计算左孩子节点的下标
        int child = 2*parent+1;

        while(child < this.userSize){
            //使child指向左右孩子最大值
            if(child+1 < this.userSize && this.elem[child+1] > this.elem[child]){
                child = child+1;
            }
            if(this.elem[parent] < this.elem[child]){
                // 交换
                int tmp = this.elem[child];
                this.elem[child] = this.elem[parent];
                this.elem[parent] = tmp;
                parent = child;
                child = 2 * parent + 1;
            }else{
                break;
            }
        }
    }

    // 判断队列是否满了
    public boolean isFull(){
        return this.userSize == this.elem.length;
    }

    // 入队列
    public void offer(int val){
        if(isFull()){
            this.elem = Arrays.copyOf(this.elem, this.elem.length/2+this.elem.length);
        }
        this.elem[this.userSize++] = val;

        // 向上调整
        shiftUp(this.userSize-1);
    }

    // 向上调整一次
    private void shiftUp(int child) {
        int parent = (child - 1) / 2;
        while (parent >= 0) {
            if (this.elem[child] > this.elem[parent]) {
                int tmp = this.elem[parent];
                this.elem[parent] = this.elem[child];
                this.elem[parent] = tmp;

                child = parent;
                parent = (child-1)/2;
            }else{
                break;
            }
        }
    }
}
