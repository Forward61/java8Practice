/**
 * Created by freedom on 2019/2/17.
 */
public class SortClass {
    private int[] array;
    private int length;

    public SortClass(int[] array){
        this.array = array;
        this.length = array.length;
    }

    public void display(){
        for(int a: array){
            System.out.print(a+" ");
        }
        System.out.println();
    }

    /**
     * 插入排序方法
     */
    public void doInsertSort(){
        for(int index = 1; index<length; index++){//外层向右的index，即作为比较对象的数据的index
            int temp = array[index];//用作比较的数据
            int leftindex = index-1;
            while(leftindex>=0 && array[leftindex]>temp){//当比到最左边或者遇到比temp小的数据时，结束循环
                array[leftindex+1] = array[leftindex];
                leftindex--;
            }
            array[leftindex+1] = temp;//把temp放到空位上
        }
    }

    public static void main(String[] args){
        int[] array = {38,65,97,76,13,27,49};
        SortClass is = new SortClass(array);
        System.out.println("排序前的数据为：");
        is.display();
        is.doInsertSort();
        System.out.println("排序后的数据为：");
        is.display();
    }
//    public static void main(String[] args) {
//        int [] originArray = new int[]{5,3,4,2,1,6};
//
//        int length = originArray.length;
//        int [] sortedArray = new int[length];
//        sortedArray[0] = originArray[0];
//        for (int i = 1; i < length; i++){
//            int [] tempArray = new int[i+1];
//
//            for (int j = 0; j< i; j++){
//                if (j == 0){
//                    if ( originArray[i] < sortedArray[j]) {
//                        int temp = sortedArray[j];
//                        sortedArray[j] = originArray[i];
//                        sortedArray[j + 1] = temp;
//
//                    }
//                }else {
//                    if (originArray[i] < sortedArray[j]) {
//
//                        int temp = sortedArray[j];
//                        sortedArray[j] = originArray[i];
//                        sortedArray[j + 1] = temp;
//                    }
//                }
//            }
//
//        }
//
//        for (int k = 0 ;k < sortedArray.length;k++){
//            System.out.println(sortedArray[k]);
//
//        }
//
//    }
}
