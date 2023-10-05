

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class ArraySortLearning implements Comparable<ArraySortLearning>{
    String startDate ;
    String endDate;
    private static int count=1;
    public ArraySortLearning(String s1,String s2){
        startDate=s1;
        endDate =s2;
    }

    public int compareTo(ArraySortLearning rv){
        return (startDate.compareTo(rv.startDate)<0 ? -1: (startDate.compareTo(rv.startDate)==0 ? 0 : 1 ));
    }

    public String toString(){
        return startDate+" " +endDate;
    }
    public static void main(String[] args){

        Random r = new Random(100);
        ArrayList<ArraySortLearning> a = new ArrayList<ArraySortLearning>();

        //for(int i=0;i<10;i++){
        //    int x= r.nextInt(100);
        //    int len= r.nextInt(10);
        //    a[i] = new ArraySortLearning(String.valueOf(x),String.valueOf(x+len));
        //}

        a.add(new ArraySortLearning("2019-06-15","2019-09-16"));
        a.add(new ArraySortLearning("2019-09-16","2019-12-18"));
        a.add(new ArraySortLearning("2020-11-20","2021-11-21"));
        a.add(new ArraySortLearning("2021-11-21","2021-11-22"));
        a.add(new ArraySortLearning("2021-11-22","2022-11-23"));
        a.add(new ArraySortLearning("2021-11-22","2021-11-22"));
        a.add(new ArraySortLearning("2021-11-22","2021-12-23"));
        a.add(new ArraySortLearning("2021-12-23","2022-01-23"));
        a.add(new ArraySortLearning("2022-01-23","2023-01-24"));
        a.add(new ArraySortLearning("2023-01-24","2023-01-30"));
        a.add(new ArraySortLearning("2022-01-29","2022-01-29"));
        a.add(new ArraySortLearning("2022-01-29","2022-02-02"));
        a.add(new ArraySortLearning("2022-02-02","2023-02-03"));
        a.add(new ArraySortLearning("2022-02-02","2022-02-02"));
        a.add(new ArraySortLearning("2022-02-02","2022-03-05"));
        a.add(new ArraySortLearning("2022-03-05","2022-04-05"));
        a.add(new ArraySortLearning("2022-04-05","2022-05-06"));
        a.add(new ArraySortLearning("2022-05-06","2022-05-20"));
        a.add(new ArraySortLearning("2022-05-20","2022-06-20"));
        a.add(new ArraySortLearning("2022-06-20","2022-07-05"));
        a.add(new ArraySortLearning("2022-07-05","2022-08-05"));

        System.out.println(a);
        //Arrays.sort(a);
        //System.out.println(Arrays.toString(a));

        System.out.println(pointTomerge(a));

    }
    public static Stack<ArraySortLearning> pointTomerge(ArrayList<ArraySortLearning> a){

        //Arrays.sort(a);
        Stack<ArraySortLearning> res = new Stack<ArraySortLearning>();
        String ss = a.get(0).startDate;
        String es = a.get(0).endDate;
        res.push(new ArraySortLearning(ss, es));
        for(int i=1;i<a.size();i++){
            String curx = a.get(i).startDate;
            String cury = a.get(i).endDate;
            // 1 相交
            if(ss.compareTo(curx)<=0 && curx.compareTo(es)<=0&&es.compareTo(cury)<=0) {
                es = cury;
                ArraySortLearning tmp = res.peek();
                res.pop();
                res.push(new ArraySortLearning(ss, cury));
            }
            //2 相交包含
            if(ss.compareTo(curx)<=0 && cury.compareTo(es)<=0) {
                continue;
            }
            // 3 不相交
            if(es.compareTo(curx) <0){
                res.push(new ArraySortLearning(curx, cury));
                ss=curx;
                es=cury;
            }

        }
        return res;
    }

}
