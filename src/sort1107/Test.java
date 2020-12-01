package sort1107;

public class Test {
    public static void main(String[] args) {
        String a = "1111111111111111111";
        String b = "16463161316461313131466313";

        String result = add(a,b);
        System.out.println(result);

    }


    //大数据相加的方法，此类型的数据远超long的取值范围，非常非常的大
    //前提是这样的字符串中只有纯数字，不含任何其他的
    public static String add(String a,String b){
        if(a == null || a == "") return b;
        if(b == null || b == "") return a;

        //因为加法是从低分位开始加的，所以创建指针指向当前的索引位置
        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;

        //创建一个变量表示进位的值
        int flag = 0;

        //创建一个变量用来表示a和b中最长的length值
        int times = (a.length() > b.length()) ? a.length() : b.length();

        //创建一个新的字符串用来存储计算的结果
        StringBuilder result = new StringBuilder();

        int aCode;
        int bCode;
        //循环遍历a ，b 的每一个位置上的值
        for(int i = 0 ; i < times ; i++){
            if(aIndex < 0 && bIndex >= 0){
                aCode = 0;
                bCode = b.codePointAt(bIndex) - '0';
            }else if(aIndex >= 0 && bIndex < 0){
                aCode = a.codePointAt(aIndex) - '0';
                bCode =0;
            }else{
                aCode = a.codePointAt(aIndex) - '0';
                bCode = b.codePointAt(bIndex) - '0';
            }
//            System.out.println("这是第"+i+"此循环,此时aCode为："+aCode+"----"+"bCode为："+ bCode);
            flag = (aCode + bCode + flag) / 10;
            result.append((aCode + bCode + flag) % 10);
            aIndex --;
            bIndex --;
        }
         return new String(result.reverse());
    }
}
