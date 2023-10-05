package leetcode;

import java.util.*;

import static java.lang.Math.max;

public class AlgorithmPractices {

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

    public static void main(String[] args) {
        String s="babad";
                // "boqylncwfahjzvawrojyhqiymirlkfzkhtvmbjnbfjxzewqqqcfnximdnrxtrbafkimcqvuprgrjetrecqkltforcudmbpofcxqdcirnaciggflvsialdjtjnbrayeguklcbdbkouodxbmhgtaonzqftkebopghypjzfyqutytbcfejhddcrinopynrprohpbllxvhitazsjeyymkqkwuzfenhphqfzlnhenldbigzmriikqkgzvszztmvylzhbfjoksyvfdkvshjzdleeylqwsapapduxrfbwskpnhvmagkolzlhakvfbvcewvdihqceecqhidvwecvbfvkahlzlokgamvhnpkswbfrxudpapaswqlyeeldzjhsvkdfvyskojfbhzlyvmtzzsvzgkqkiirmzgibdlnehnlzfqhphnefzuwkqkmyyejszatihvxllbphorprnyponircddhjefcbtytuqyfzjpyhgpobektfqznoatghmbxdouokbdbclkugeyarbnjtjdlaisvlfggicanricdqxcfopbmducroftlkqcertejrgrpuvqcmikfabrtxrndmixnfcqqqwezxjfbnjbmvthkzfklrimyiqhyjorwavzjhafwcnlyqob";//"pwwkew";
        //System.out.println(lengthOfLongestSubstring2(s) );
        // System.out.println(lengthOfLongestSubstring(s) );
        //System.out.println(lengthOfLongestSubstringMethods(s) );

        // System.out.println("res： " +longestPalindrome(s));
        //Boolean[][] dp = new Boolean[s.length()+1][s.length()+1];
        //System.out.println("res： " +sloveDp(s,dp,0,0));

        System.out.println("res： " +longestPalindrome2(s));
    }
}
