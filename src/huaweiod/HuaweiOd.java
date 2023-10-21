package huaweiod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class HuaweiOd {

    //001_AI-Processor-Combination.md
    static List<List<Integer>> aiProcessorCombination(int[] arr, int num){
        List<List<Integer>> res = new ArrayList<>();
        // lineOne lineTwo
        int lineOneInt =0, lineTwoInt =0;
        for(int i=0 ; i < arr.length; i++){
            if(arr[i] <=3) lineOneInt++;
            else lineTwoInt++;
        }
        int[] lineOne = new int[lineOneInt] ;
        int[] lineTwo = new int[lineTwoInt] ;
        int tmpi=0,tmpj=0;
        for(int i=0 ; i < arr.length; i++){
            if(arr[i] <=3) lineOne[tmpi++] = arr[i];
            else lineTwo[tmpj++] = arr[i];
        }
        Stack<Integer> stack = new Stack<>(); // 每一个组合临时存放位置
        if(num ==1 ) {
            int pority[] = new int[]{1, 3, 2, 4};// 优先级
            Boolean flag = false;
            for(int p : pority){
                if(p == lineOne.length) {
                    selectNum(lineOne, num , 0 ,0, stack, res );
                    flag = true;
                }
                if(p == lineTwo.length) {
                    selectNum(lineTwo, num , 0 ,0, stack, res );
                    flag = true;
                }
                if(flag == true ) break;
            }
        }
        else if(num ==2 ) {
            int pority[] = new int[]{ 2, 4, 3};// 优先级
            Boolean flag = false;
            for(int p : pority){
                if(p == lineOne.length) {
                    selectNum(lineOne, num , 0 ,0, stack, res );
                    flag = true;
                }
                if(p == lineTwo.length) {
                    selectNum(lineTwo, num , 0 ,0, stack, res );
                    flag = true;
                }
                if(flag == true ) break;
            }
        }
        else if(num == 4  ) {
                if(num == lineOne.length) {
                    res.add( new ArrayList<>(){
                        {   add(lineOne[0]);
                            add(lineOne[1]);
                            add(lineOne[2]);
                            add(lineOne[3]);}
                    });
                }
                if(num == lineTwo.length) {
                    res.add( new ArrayList<>(){
                        {   add(lineTwo[0]);
                            add(lineTwo[1]);
                            add(lineTwo[2]);
                            add(lineOne[3]);}
                    });
                }

        }
        else if(num== 8) {
            if(4 == lineOne.length && 4 == lineTwo.length  )
                res.add( new ArrayList<>(){
                    {   addAll(Arrays.stream(arr).boxed().collect(Collectors.toList()));
                    }
                });

        }
        return res;
    }

    // 在数组nums中选取n的数的集合
    static void selectNum(int[] nums, int n, int i,int iter, Stack<Integer> stack, List<List<Integer>> res){
        if(i == n){
            if(!stack.isEmpty()){
                res.add(stack.stream().toList());
                return ;
            }
        }
        for(int j=iter ; j< nums.length; j++){
            stack.push(nums[j]);
            selectNum(nums, n , i+1 , j+1, stack, res );
            stack.pop();
            }
    }
    public static void main(String[] args) {
        //001_AI-Processor-Combination.md

/*
        int[] nums = new int[]{0,1,2,3}; // 目标数组
        int n=3; // 选中数字个数
        Stack<Integer> stack = new Stack<>(); // 每一个组合临时存放位置
        List<List<Integer>> res = new ArrayList<>();  // 结果数据存放
        selectNum(nums, n , 0 ,0, stack, res );

         for(List<Integer> i : res) {
             for (Integer j : i) {
                 System.out.print(j);
                 System.out.print(" ");
             }
             System.out.println();
         }
*/

        //001_AI-Processor-Combination.md
        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6,7 } ;//{0, 1, 4, 5, 6, 7};
        int num=8;
        List<List<Integer>> res =  aiProcessorCombination(arr,num );
        for(List<Integer> i : res) {
            for (Integer j : i) {
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
