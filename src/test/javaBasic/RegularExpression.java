package test.javaBasic;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {


    //
    public static void main(String args[]) throws UnsupportedEncodingException {

        // 正则表达式提取双引号中的字符串
        //regfun0();
        // 返回字符串
       // regfun();
        String s ="\\xe6\\x94\\xb6\\xe9\\x93\\xb6\\xe5\\x8f\\xb0";
        String s1 = s.replaceAll("\\\\x", "%");
        String decode = URLDecoder.decode(s1, "utf-8");
         System.out.println(decode);

    }
    static void regfun0(){
        String t = "\"world\"";
        String p = "\"([^\"]*)\"" ;
        Pattern P=Pattern.compile(p);
        Matcher matcher1=P.matcher(t);
        if(matcher1.find())
        {
            System.out.println(matcher1.group(0)); // "world"
        }
    }

    static void regfun(){

        String t = "\"world\"";
        String p = "\"([^\"]*)\"" ;
        Pattern P=Pattern.compile(p);
        Matcher matcher1=P.matcher(t);
        if(matcher1.find())
        {
            System.out.println(matcher1.group(0).replaceAll(p, "$1"));
        }
    }
}
