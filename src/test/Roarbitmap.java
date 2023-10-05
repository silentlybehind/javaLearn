package test;


import static java.lang.Math.floor;

public class Roarbitmap {

    public static void main(String[] args) {
    //    RoaringBitmap rr = RoaringBitmap.bitmapOf(1,2,3,1000);
    //    RoaringBitmap rr2 = new RoaringBitmap();
    //    rr2.add(4000L,4255L);
    //    rr.select(3); // would return the third value or 1000
    //    rr.rank(2); // would return the rank of 2, which is index 1
    //    rr.contains(1000); // will return true
    //    rr.contains(7); // will return false
//
    //    RoaringBitmap rror = RoaringBitmap.or(rr, rr2);// new bitmap
    //    rr.or(rr2); //in-place computation
    //    boolean equals = rror.equals(rr);// true
    //    if(!equals) throw new RuntimeException("bug");
    //    // number of values stored?
    //    long cardinality = rr.getLongCardinality();
    //    System.out.println(cardinality);
    //    // a "forEach" is faster than this loop, but a loop is possible:
    //    for(int i : rr) {
    //        System.out.println(i);
    //    }
        //String s= "System";

        //System.out.println(s.substring(0,2));
        // 1h=60′，1′=60″，即1h=60′=3600″
        double timeNums = (4*60+05)*42.195;

        System.out.println(timeNums + "= " + (int) Math.floor(timeNums/3600) +":" +  (int) Math.floor(timeNums%3600/60) +":"+  (int) Math.floor(timeNums%3600%60));




    }
    // public int lengthOfLongestSubstring(String s){
    //     String tmp ="";
    //   for(int i=0; i<s.length()-1;i++){
    //       for(int j=i+1;j<s.length();j++){
    //           s.substring(i,j+1);
    //       }
    //   }
    //   return 0;
    // }


    // 定义10进制转2进制的方法。
    public static String C10T2(int numb) {
        String result = "";
        for (int i = numb; i > 0; i /= 2)
            result = i % 2 + result;
        return result;
    }

    // 定义10进制转60进制的方法。
    public static String C10T60(int numb) {
        String result = "";
        for (int i = numb; i > 0; i /= 60)
            result = i % 60 + result;
        return result;
    }

}
