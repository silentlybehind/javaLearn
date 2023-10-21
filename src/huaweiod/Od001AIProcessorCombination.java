package huaweiod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Od001AIProcessorCombination {

    //001_AI-Processor-Combination.md

    /**
     * 将输入的数组分成两组，第一组数字小于4，第二组数字大于4
     * 列出相关逻辑
     * 当num为1时，根据题意传入优先级[1,3,2,4]，遍历优先级，返回子序列列表
     * 当num为2时，根据题意传入优先级[2, 4, 3]，考虑到排列组合，使用递归函数selectNum，获取子序列之后，整理成list格式返回结果列表
     * 当num为4时，根据题意如果有一组数字满足条件，返回该组的所有处理器编号
     * 当num为8时，根据题意如果两组数字都满足条件，返回所有处理器编号

     */
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
                    // list 初始化 https://blog.csdn.net/qq_44695727/article/details/106517147
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
        int num=1;
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
