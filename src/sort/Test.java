package sort;



public class Test {
    static int bb =1 ;

    public static void main(String[] args){

        int[] a = {1,2,3,7};
        int[] b = {4,5,6,8};
        Test test = new Test();
        int[] c = test.mergeSort2(a,b);
        for(int f : c){
            System.out.println(f);
        }

    }



    //设计一个方法 将“100000”变成“100.000”
    public String test1(String str){
        if(str.length() <= 3) return str;
        StringBuilder stringBuilder = new StringBuilder(str);
        for(int i= 1;i <= str.length()/3;i++){
            //此处需要注意插入元素的位置+1
            stringBuilder.insert((str.length() - 3*i),".");
        }
        return new String(stringBuilder);
    }


    //-------------------------------------------------------------
    //排序的本质是判断优先级 交换位置
    public static boolean compare(int a ,int b){//从小到大排序
        if(a > b) return true;
        else return false;
    }

    //注意此处所传的参数处理
    public static void changePosition(int[] array,int index1,int index2){
        if(array == null) return;
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    //--------------------------冒泡排序-------------------------------

    public static void bubbleSort(int[] array){
        if(array == null) return;
        for(int j = 0 ;j < array.length ; j ++){
            for(int i = 0 ;i < array.length - 1 - j; i ++ ){
                if(compare(array[i],array[i+1])){
                    changePosition(array ,i,i+1);
                }
            }
        }
    }

    //------------------------------选择排序----------------------------------------

    public static void selectSort(int[] array ){
        //选择排序可以理解为是以位置为主的方法  数据通过比较数据 放置到对应的位置上
        //创建一个变量用来记录最小值的索引（此处排序的方式是由小到大）
        if(array == null) return;
        for(int j = 0; j < array.length - 1 ;j ++){
            int minIndex = j+ 1 ;
            for( int i =  j ;i < array.length ; i ++){
                if( ! compare(array[i] ,array[minIndex])){
                    minIndex = i ;
                }
            }
            changePosition(array,minIndex ,j );
        }
    }

    public static void selectSort2(int[] array ){
        //选择排序可以理解为是以位置为主的方法  数据通过比较数据 放置到对应的位置上
        //创建一个变量用来记录最大值的索引（此处排序的方式是由小到大）
        if(array == null) return;
        for(int j = 0; j < array.length ;j ++){
            int maxIndex = j ; //此处的变量也相当于指针
            for( int i =  0 ;i < array.length - j ; i ++){
                if(compare(array[i] ,array[maxIndex])){
                    maxIndex = i ;
                }
            }
            changePosition(array, maxIndex ,array.length -1-j );

        }
    }


    //-----将两个有序的数组进行合并  要求合并后数组也是有序的-----------------------------------------
    public  int[] mergeSort1(int[] a,int[] b){
        if(a == null) return b;
        if(b == null) return a;
        int[] newArray = new int[a.length + b.length];
        //分别创建三个指针 用来记录索引的位置
        int pointerA = 0;
        int pointerB = 0;
        int pointerNewArray = 0;
        while(pointerA < a.length && pointerB < b.length){
            if(a[pointerA] <= b[pointerB] || pointerB == b.length){
                newArray[pointerNewArray] = a[pointerA];
                pointerA ++;
            }else if(a[pointerA] > b[pointerB]){
                newArray[pointerNewArray] = b[pointerB];
                pointerB ++;
            }
            pointerNewArray ++;
        }
        if(pointerA == a.length &&  pointerB <= b.length){
            for(int i = pointerB;i < b.length ; i++){
                newArray[pointerNewArray] = b[i];
                pointerNewArray ++;
            }
        }
        if(pointerA <= a.length  &&  pointerB == b.length){
            for(int i = pointerA; i < a.length ; i++){
                newArray[pointerNewArray] = a[i];
                pointerNewArray ++;
            }
        }
        return newArray;
    }

    public  int[] mergeSort2(int[] a,int[] b){
        if(a == null) return b;
        if(b == null) return a;
        int[] newArray = new int[a.length + b.length];
        //分别创建三个指针 用来记录索引的位置
        int pointerA = 0;
        int pointerB = 0;
        int pointerNewArray = 0;
        //当pinter等于length时  表示该数组已经循环完毕
        //指针的位置可以理解为当前索引位置的下一个位置
        while(pointerA < a.length || pointerB < b.length){
            //下面的短路或的两个条件的先后顺序会影响最终的结果，一定要注意先后顺序
            if(pointerB == b.length || pointerA < a.length  &&  a[pointerA] <= b[pointerB] ){
                newArray[pointerNewArray] = a[pointerA];
                pointerA ++;
            }else {
                newArray[pointerNewArray] = b[pointerB];
                pointerB ++;
            }
            pointerNewArray ++;
        }
        return newArray;
    }
}
