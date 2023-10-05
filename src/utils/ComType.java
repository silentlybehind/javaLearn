package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ComType implements Comparable<ComType>{
    int i ;
    int j;
    private static int count=1;
    public ComType(int n1,int n2){
        i=n1;
        j=n2;
    }

    public String toString(){
        String result ="[i = " + i + ". j = "+j +" ]";
        if(count++ %3==0){
            result +="\n";
        }
        return result;
    }

    public int compareTo(ComType rv){
        return (i<rv.i ? -1: (i==rv.i ? 0 : 1 ));
    }


    public static void main(String[] args) throws UnsupportedEncodingException {


        Cal c = (a ,b ) -> {return a+b;};
        Cal c1 = (a ,b ) ->  a+b;
       System.out.println(c.add(1,2));
        Random r = new Random(100);
        ComType[] a = new ComType[10];

        for(int i=0;i<10;i++){
            a[i] = new ComType(r.nextInt(100),r.nextInt(100));
        }
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));


        String encodeStr = URLEncoder.encode("}","utf-8");// .encode("秃头程序猿", "utf-8");
        System.out.println("进行处理:" + encodeStr);



        String tmps ="https%3A%2F%2Fwww.bilibili.com%2Fblackboard%2Factivity-Wi4aRaBMyZ.html%3Fsource_from%3Dpgc.pgc-video-detail.try-tips.button.click%26order_report_params%3D%257B%2522spmid%2522%253A%2522pgc.pgc-video-detail.0.0%2522%252C%2522tips_repeat_key%2522%253A%25226154%253A14%253A1651291035%253A1337706484%2522%252C%2522ep_status%2522%253A%252213%2522%252C%2522from_out_spmid%2522%253A%2522search.search-result.0.0%2522%252C%2522exp_tag%2522%253A%2522def%2522%252C%2522season_id%2522%253A%252239433%2522%252C%2522season_status%2522%253A%252213%2522%252C%2522ep_id%2522%253A%2522474226%2522%252C%2522material_type%2522%253A%25223%2522%252C%2522season_type%2522%253A%25221%2522%252C%2522vip_type%2522%253A%25221%2522%252C%2522vip_status%2522%253A%25220%2522%252C%2522tips_id%2522%253A%25226154%2522%252C%2522request_id%2522%253A%2522821469ec16a37a88%2522%252C%2522unit_id%2522%253A%25221901%2522%252C%2522from_spmid%2522%253A%2522search.search-result.0.0%2522%252C%2522exp_group_tag%2522%253A%2522def%2522%252C%25";
        System.out.println("源码1:" + tmps);
        String decodeStr = URLDecoder.decode(tmps, "utf-8");
        System.out.println("解码1:" + decodeStr);
        //System.out.println("解码1:" + decodeStr.endsWith("%"));
        //String decodeStr1 = decodeStr+"%7D";
       // System.out.println("解码2:" + URLDecoder.decode(decodeStr1, "utf-8")  );
        //String tmps1 = tmps.substring(100,150);
        //System.out.println(URLDecoder.decode(tmps1, "utf-8"));
        //System.out.println(URLDecoder.decode(tmps.substring(150,200), "utf-8"));
        //System.out.print(URLDecoder.decode(tmps.substring(200,300), "utf-8"));
        //System.out.print(URLDecoder.decode(tmps.substring(300,400), "utf-8"));
        //System.out.print(URLDecoder.decode(tmps.substring(400,500), "utf-8"));
        //System.out.print(URLDecoder.decode(tmps.substring(500,600), "utf-8"));
        //System.out.print(URLDecoder.decode(tmps.substring(600,700), "utf-8"));
        //System.out.print(URLDecoder.decode(tmps.substring(700), "utf-8"));


        //System.out.println();
        //String decodeStr1 = bdecode(tmps, "utf-8");System.out.println("解码3:" + decodeStr1);


        int v = Integer.parseInt("2F",16);
        System.out.println("%2F:" + v);

        byte[] bytes = null;
        bytes = new byte[3];
        bytes[0]=(byte) v;  bytes[1]=(byte) (v+1);
        System.out.println("== " + new String(bytes, 0, 1,"utf-8"));


        // stream
        Set<String > w  = new HashSet<>(Arrays.asList("djfksd dsjfkjsdfj djf kdjfk".split(" ")));
        w.stream().map(x->x+" ").forEach(System.out::println);

        Random rand = new Random(47);
        System.out.println(rand.ints().boxed());
    }
    interface Cal {
        int add(int a,int b);
    }


    public static String bdecode(String s, String enc)
            throws UnsupportedEncodingException{

        boolean needToChange = false;
        int numChars = s.length();
        StringBuffer sb = new StringBuffer(1500);
        int i = 0;

        if (enc.isEmpty()) {
            throw new UnsupportedEncodingException ("URLDecoder: empty string enc parameter");
        }

        char c;
        byte[] bytes = null;
        while (i < numChars) {
            c = s.charAt(i);
            switch (c) {
                case '+':
                    sb.append(' ');
                    i++;
                    needToChange = true;
                    break;
                case '%':
                    /*
                     * Starting with this instance of %, process all
                     * consecutive substrings of the form %xy. Each
                     * substring %xy will yield a byte. Convert all
                     * consecutive  bytes obtained this way to whatever
                     * character(s) they represent in the provided
                     * encoding.
                     */

                    try {

                        // (numChars-i)/3 is an upper bound for the number
                        // of remaining bytes
                        if (bytes == null)
                            bytes = new byte[1000];
                        int pos = 0;

                        while ( ((i+2) < numChars) &&
                                (c=='%')) {
                            int v = Integer.parseInt(s.substring(i+1,i+3),16);
                            if (v < 0)
                                throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern - negative value");
                            bytes[pos++] = (byte) v;
                            i+= 3;
                            if (i < numChars)
                                c = s.charAt(i);
                        }

                        // A trailing, incomplete byte encoding such as
                        // "%x" will cause an exception to be thrown

                        if ((i < numChars) && (c=='%'))
                            throw new IllegalArgumentException(
                                    "URLDecoder: Incomplete trailing escape (%) pattern");

                        sb.append(new String(bytes, 0, pos, enc));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(
                                "URLDecoder: Illegal hex characters in escape (%) pattern - "
                                        + e.getMessage());
                    }
                    needToChange = true;
                    break;
                default:
                    sb.append(c);
                    i++;
                    break;
            }
        }

        return (needToChange? sb.toString() : s);
    }
}