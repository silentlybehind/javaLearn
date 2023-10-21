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

    public static void main(String[] args) {

        int taskA=1,  taskB=2 , nums=3;
        List<Integer>  res = taskExecuteTotalTime( taskA,  taskB , nums);
        for(Integer i : res) {
                System.out.print(i);
                System.out.print(" ");

        }
        System.out.println();
    }

}
