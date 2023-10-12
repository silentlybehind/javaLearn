package leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.max;

public class AlgorithmPractices {
    static IntCall fact ;

    public static int lengthOfLongestSubstring(String s) {
        String res ="";
        int  maxL=0;
        for(int i=0; i<s.length();i++)
        {
            for(int j=i; j<s.length();j++)
            {
                String tmp =s.substring(i,j+1);
                //System.out.println(i +" "+ j + " "+tmp );
                Set<Character> con  =  new HashSet<Character>();
                for(int k=0; k<tmp.length();k++)  con.add(tmp.charAt(k));
                if( tmp.length()==con.size() && tmp.length()>=maxL ) {
                    res = tmp;
                    maxL = tmp.length();
                }
            }
        }
        return maxL;
    }

    static int repeatString(String s) {
        Set<Character> con  =  new HashSet<Character>();
        for(int i=0; i<s.length();i++)
        {
            con.add(s.charAt(i));
        }
        return con.size();
    }

    // 滑动串口
    public static int lengthOfLongestSubstringMethods(String s) {
        String res ="";
        int  maxLen=0, curLen = 0 , startIndex =0 ;
        // Set<Character> con  =  new HashSet<Character>();
        Map<Character, Integer> con = new HashMap<>();
        for(int i=0; i<s.length();i++)
        {
            //扩充窗口
            if(!con.containsKey(s.charAt(i))){
                con.put(s.charAt(i),(Integer) i);
                curLen ++;
            }
            //收缩窗口
            else{
                if(curLen > maxLen) maxLen = curLen;
                startIndex = Math.max(con.get(s.charAt(i)),startIndex) ;
                curLen = i - startIndex;
                con.put(s.charAt(i),(Integer) i);
            }
            //
            if(curLen > maxLen) maxLen = curLen;
        }
        return maxLen;
    }

    // longest substring without repeating charaters
    /*
     * 两个指针开是遍历字符串,遍历字符会有两种情况
     * 1.字符不包含set集合中，添加字符进入集合，
     * 2.字符包含在set集合中，集合移除left位置的字符串left++,right加入集合
     * */
    public static int lengthOfLongestSubstring2(String s) {
        Set<Character> substr = new HashSet();
        int maxLen =0, left=0;
        for(int right=0; right<s.length();right++){
            if(!substr.contains(s.charAt(right))){
                substr.add(s.charAt(right));
                maxLen= Math.max(maxLen,substr.size());
            }else{
                while(substr.contains(s.charAt(right))){
                    substr.remove(s.charAt(left));
                    left++;
                }
                substr.add(s.charAt(right));
            }
        }
        //substr.forEach(item -> {
        //    System.out.print(item);
        //});
        //System.out.println();
        return maxLen;
    }
    //Longest Palindromic Substring
    public static String longestPalindrome(String s) {
        if(s.length()==1) return s;
        String maxLongP ="";
        for(int i=0;i<s.length()-1;i++)
            for(int j=i+1;j<s.length();j++)
            {
                String tmp = s.substring(i,j+1);
                //System.out.println(tmp);
                int right=tmp.length()-1;
                int left =0;
                int flag= 1;
                while(left < right ){
                    if(tmp.charAt(left) !=tmp.charAt(right)){
                        flag = 0;
                    }
                    left++;
                    right--;
                }
                if(flag==1)
                    maxLongP = maxLongP.length()>tmp.length()? maxLongP :tmp;
            }
        return maxLongP;
    }

    static Boolean isPalindNum(String s){
        if(s.length()==0) return false;
        int right=s.length()-1;
        int left =0;
        while(left < right ){
            if(s.charAt(left) !=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //dp

    static Boolean sloveDp(String s, Boolean[][] dp, int i, int j){
        // for (int j1 = 0; j1 < s.length(); j1++) {
        //     for (int k = 0; k < s.length(); k++) {
        //         System.out.print(dp[j1][k] + " ");
        //     }
        //     System.out.println();
        // }
        // return true;
        if(i==j) return dp[i][j]=true;
        if(j-i== 1 ) {
            if(s.charAt(i)== s.charAt(j))
                return dp[i][j]=true;
            else return dp[i][j]=false;
        }
        if(s.charAt(i)== s.charAt(j) && j>1 && dp[i+1][j-1]==true ) return dp[i][j]=true;
        else return dp[i][j]=false;
    }
    static String longestPalindrome2(String s){
        //Vector<Vector<Integer>> dp = new Vector<Vector<Integer>>(s.length());
        Boolean[][] dp = new Boolean[s.length()+1][s.length()+1];
        int starti=0;
        int maxLen=0;
        for(int g=0;g<s.length();g++){
            for(int i=0,j=g;j<s.length();j++,i++){
                sloveDp(s,dp,i,j);
                if(dp[i][j]== true){
                    if(j-i+1>maxLen){
                        maxLen = j-i+1;
                        starti =i;
                    }
                }
            }
        }
        return s.substring(starti,starti+maxLen);
    }

    //https://blog.csdn.net/wtswts1232/article/details/130211060

    /**
     * input
     * 3
     * 1,4
     * 2,5
     * 3,6 https://github.com/silentlybehind/littleTools.git
     * @return
     * @throws IOException
     */
    static int lineCover() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine().trim());
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String[] parts = reader.readLine().split(",");
            intervals.add(new int[] { Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) });
        }
        intervals.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        Stack<int[]> stack = new Stack<>();
        stack.push(intervals.get(0));

        for(int i =1 ; i< count; i++){
            int [] curInterval = intervals.get(i);
            int [] topInterval = stack.isEmpty()?null : stack.peek();

            while(true){
                if(topInterval == null){
                    stack.push(curInterval);
                    break;
                }

                int curStart = curInterval[0];
                int curEnd = curInterval[1];
                int topStart = topInterval[0];
                int topEnd = topInterval[1];

                // 当前线段在栈顶左侧没有交集
                if(curEnd <= topStart) break;
                    // 当前线段在栈顶左侧有交集
                else if( topStart >= curEnd && curStart <= topEnd ) break;
                    // 当前线段和栈顶线段完全覆盖
                else if( curStart <= topStart && curEnd > topEnd ) {
                    stack.pop();
                    // stack.push(new int[]{curStart,curEnd});
                }
                // 当前线段在栈顶线段右侧有部分重叠
                else if( curStart > topStart && curEnd > topEnd )
                {stack.push(new int[]{topEnd,curEnd});break;}
                // 当前线段在栈顶线段右侧没有交集
                else //if( curStart > topEnd)
                {stack.push(curInterval);break;}
                topInterval =  stack.isEmpty()?null : stack.peek();
            }

        }
        int stacksize = stack.size();
        // 遍历堆栈中的每个整数数组
        while (!stack.isEmpty()) {
            int[] array = stack.pop(); // 弹出堆栈顶部的整数数组
            for (int element : array) {
                System.out.print(element + " "); // 打印数组元素
            }
            System.out.println(); // 换行以分隔不同的数组
        }
        return stacksize;
    }


    static void lambdaExpressed(){
        fact = n -> n ==0 ? 1 : n * fact.call(n-1);
        for(int i=0 ;i<10;i++)
            System.out.println(fact.call(i));
    }

    // 6. Zigzag Conversion
    static String convert(String s, int numRows){
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i=0;i< numRows;i++) rows.add(new StringBuilder());
        int i=0, flag=-1;
        for(char c :s.toCharArray()){
            rows.get(i).append(c);
            if(i==0 || numRows-1 ==i ) flag= -flag;
            i+=flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) res.append(row);
        return res.toString();
    }
    //7. Reverse Integer
    static int reverse(int x) {
        int  MAX_INT = (2<<31)-1 ;
        long  MIN_INT = -2<<31;
        //System.out.println(MAX_INT);
        //System.out.println(MIN_INT);
        int flag =0;
        if(x<0)
            flag =-1;
        StringBuilder res = new StringBuilder();
        res.append(Integer.valueOf(Math.abs(x)).toString());
        String ress = res.reverse().toString();
        //ystem.out.println(res);
        if(ress.length()>=10) {
            int c1 = Integer.parseInt(ress.substring(0,5));
            int c2 = Integer.parseInt(ress.substring(5,10));
            if(c1 >21474 || c2 >83648 )
                return 0;
        }
        int resInt = Integer.parseInt(ress);
        //System.out.println(1<<32);
        return flag == 0 ? resInt : -resInt;
    }

    //8. String to Integer (atoi)
    static int myAtoi(String s) {
        int i = 0;
        int len = s.length();
        while(i<len && s.charAt(i) == ' ')// white space
            i++;
        // sign
        int positive =0;
        int negitive =0;
        if(i<len && s.charAt(i)=='+'){
            positive++;
            i++;
        }
        if(i<len && s.charAt(i)=='-'){
            negitive++;
            i++;
        }
        double ans=0;
        while(i<len && s.charAt(i)>='0' && s.charAt(i)<='9'){
            ans= ans*10+ (s.charAt(i)-'0');
            i++;
        }
        //System.out.println(ans);
        if(negitive>0  )
            ans = - ans;
        if(negitive>0  && positive>0) return 0;
        //System.out.println(ans);
        // bounding
        double MAX_INT = (long) 2<<31 -1;
        double MIN_INT = (int) Math.pow(-2, 31)  ;//(long) 2<<31;
        //System.out.println(MAX_INT);
        //System.out.println(MIN_INT);
        if(ans>MAX_INT ) ans = (int) MAX_INT;
        if(ans<MIN_INT ) ans = (int) MIN_INT;
        return (int) ans;
    }

    //9. Palindrome Number
    static boolean isPalindrome(int x) {
        if(x<0) return false;
        StringBuilder res = new StringBuilder();
        int ans=x;
        while(ans>0){
            res.append(ans%10);
            ans = ans/10;
        }
        //System.out.println(res.toString());
        //System.out.println(res.reverse().toString());
        String s1=res.toString();
        String s2=res.reverse().toString();
       if(s1.equals(s2)) return true;
       else return false;
    }
    // 11. 盛最多水的容器
    static int maxArea(int[] height) {
        int maxContainer =0;
        for(int g=0 ; g<height.length; g++)
            for(int i=0 ,j = g ; j<height.length; i++,j++){
                int len = j-i;
                int hei = Math.min(height[i],height[j]);
                if(maxContainer<  len*hei) {
                    maxContainer = len * hei;

                }
            }
        return maxContainer;
    }

    public static void main(String[] args) throws IOException {
        //String s="babad";
        // "boqylncwfahjzvawrojyhqiymirlkfzkhtvmbjnbfjxzewqqqcfnximdnrxtrbafkimcqvuprgrjetrecqkltforcudmbpofcxqdcirnaciggflvsialdjtjnbrayeguklcbdbkouodxbmhgtaonzqftkebopghypjzfyqutytbcfejhddcrinopynrprohpbllxvhitazsjeyymkqkwuzfenhphqfzlnhenldbigzmriikqkgzvszztmvylzhbfjoksyvfdkvshjzdleeylqwsapapduxrfbwskpnhvmagkolzlhakvfbvcewvdihqceecqhidvwecvbfvkahlzlokgamvhnpkswbfrxudpapaswqlyeeldzjhsvkdfvyskojfbhzlyvmtzzsvzgkqkiirmzgibdlnehnlzfqhphnefzuwkqkmyyejszatihvxllbphorprnyponircddhjefcbtytuqyfzjpyhgpobektfqznoatghmbxdouokbdbclkugeyarbnjtjdlaisvlfggicanricdqxcfopbmducroftlkqcertejrgrpuvqcmikfabrtxrndmixnfcqqqwezxjfbnjbmvthkzfklrimyiqhyjorwavzjhafwcnlyqob";//"pwwkew";
        //System.out.println(lengthOfLongestSubstring2(s) );
        // System.out.println(lengthOfLongestSubstring(s) );
        //System.out.println(lengthOfLongestSubstringMethods(s) );

        // System.out.println("res： " +longestPalindrome(s));
        //Boolean[][] dp = new Boolean[s.length()+1][s.length()+1];
        //System.out.println("res： " +sloveDp(s,dp,0,0));

        //System.out.println("res： " +longestPalindrome2(s));
        // lambda表达式
        //lambdaExpressed();

        //线段填充
        //System.out.println(lineCover());

        // 6. Zigzag Conversion
        //String ss ="PAYPALISHIRING"; //PAHNAPLSIIGYIR
        //int numRows =3;
        //System.out.println(convert(ss,  numRows));

        // 7
        //System.out.println(1<<30);
       // System.out.println(reverse(1534236469));

        //8. String to Integer (atoi)
//words and 987
        //   42
       // System.out.println(myAtoi("21474836460") );

        //9. Palindrome Number
        System.out.println(isPalindrome(123) );

        //
        int[] height = new int [] {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height) );
    }
}
