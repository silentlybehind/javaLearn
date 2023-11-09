package huaweiod;


import java.util.ArrayList;
import java.util.List;

public class Od002TaskExecuteTotalTime {
    // docs/choice100/002_task-execute-total-time.md

    static void dp(int[] tasks, int nums, int ans, List<Integer> res){
        if(nums == 0 ) {
            res.add(ans);
            //if(res.size()>=0)
            return;
        }
        for(int i=0; i< tasks.length; i++){
            ans += tasks[i];
            dp(tasks, nums-1 , ans, res);
            ans -= tasks[i];
        }
    }
    static List<Integer> taskExecuteTotalTime(int taskA, int taskB, int nums){
        int[] tasks = new int[] {taskA, taskB};
        List<Integer> res = new ArrayList<>();
        dp(tasks, nums , 0, res);
        return res;
    }

    /**
     b[0] =a[1]  a[n-1]
     b[1] =a[0]*a[2]* a[n-1]

     b[i] =  a[0] a[i-1] a[i+1] a[n-1]

     b[n-1]
     */
    static int[] fun(int[] a){
        int b[] = new int[a.length] ;
        int multarr=1;
        for(int i=0; i<a.length; i++)
            multarr*=a[i];
        for(int i=0;i<a.length;i++)
            b[i]= multarr/a[i];
        return b;
    }
    /**
    1 23 45
    i = 3

    for(int i= 0 )

    // f(i) = f(i-1,a,1) * f(len-(i+1),a,0)
    //  b[] = bleft[] * bright[]
        bleft 1*2
    1
    2
    ..
    i-1

    // { (i-1)/2 + (i-1)/2 }* n
    // n/2 *n

   int fun1(int i ,int[] a , int flag , int j ){

        for()
*/


   


    public static void main(String[] args) {

        //int taskA=1,  taskB=2 , nums=3;
        //List<Integer>  res = taskExecuteTotalTime( taskA,  taskB , nums);
        //for(Integer i : res) {
        //        System.out.print(i);
        //        System.out.print(" ");
//
        //}
        //System.out.println();

        int a[] = new int[]{1,2,3,4,5};
        int b[] = fun(a );
        for(int i=0;i<a.length;i++) {
            System.out.print(b[i]);
            System.out.print(" ");
        }
    }







}
