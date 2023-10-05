package test;

import java.util.ArrayList;

public class AloneNums {
    public static void main(String[] args) {
        aloneNums();
        aloneNums2();
        aloneNums3();
        int t =111^735;
        System.out.println("111^735= " + t);

    }
    // 一个孤单数
    static void aloneNums(){
        int[] a ={520, 216, 216, 520, 111};
        int  res=0;
        //System.out.println(a);
        for(int i : a) {
            res^=i;
        }
        System.out.println(res);
    }
   // 两个孤单数
    static void aloneNums2(){
        int[] a ={520, 216, 216, 520, 111,734};
        class Tmp {
            int a;
            String astr;
            void seta(int a ){ this.a = a;}
            void setastr(String astr ){ this.astr = astr;}
        };
        ArrayList<Tmp> res = new ArrayList<>();
        int alone1=0,alone2=0 ;
        for(int i : a) {
            String binaryNum = Integer.toBinaryString(i);
            Tmp t = new Tmp();
            t.seta(i);
            t.setastr(binaryNum);
            res.add(t);
        }
        for(Tmp i : res) {
            String str = i.astr;
            // 字符串最后一位  str.substring(str.length()-1)
            // 1 和 0 分类,然后分别异或
            if(str.substring(str.length()-1).equals("1")){
                alone1^=i.a;
            }else
                alone2^=i.a;
        }
        System.out.println(alone1);
        System.out.println(alone2);
    }

    // 3个孤单数
    static void aloneNums3(){
        int[] a ={520, 216, 216, 520, 111,734,735};
        class Tmp {
            int a;
            String astr;
            void seta(int a ){ this.a = a;}
            void setastr(String astr ){ this.astr = astr;}
        };
        ArrayList<Tmp> res = new ArrayList<>();
        int alone1=0,alone2=0 ;
        for(int i : a) {
            String binaryNum = Integer.toBinaryString(i);
            Tmp t = new Tmp();
            t.seta(i);
            t.setastr(binaryNum);
            res.add(t);
        }
        for(Tmp i : res) {
            String str = i.astr;
            // 字符串最后一位  str.substring(str.length()-1)
            System.out.println(i.a +" "+ i.astr);
            // 1 和 0 分类,然后分别异或
            if(str.substring(str.length()-1).equals("1")){
                alone1^=i.a;
            }else
                alone2^=i.a;
        }
        System.out.println(alone1);
        System.out.println(alone2);
    }
}
