package huaweiod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Od003CountOoutstandingStudents {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //第一行 新员工数量N  1,100
        int count = Integer.parseInt(reader.readLine().trim());
        List<String> ActDayList = new ArrayList<>();
        //第二行 输入为30个整数
        String parts[] = reader.readLine().split(" ");
        ActDayList = Arrays.stream(parts).toList();
        //第三行 30行表示每天打开的员工id集合，id不会重复
        int Actdays = 30;
        List<List<String>> SignInDayList = new ArrayList<>();
        for (int i = 0; i < Actdays; i++) {
            String EverydayParts[] = reader.readLine().split(" ");
            SignInDayList.add(Arrays.stream(EverydayParts).toList());
        }

        System.out.println(count);
        for (String i : ActDayList) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();

        for (List<String> i : SignInDayList) {
            for (String j : i) {
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println();
        }

        Map<String,int[]> Employeemap = new HashMap<>();
        int dayId=-1;
        for (List<String> i : SignInDayList) {
            dayId++;
            for (String j : i) {
                Set<String> sKey = Employeemap.keySet();
                if(sKey.contains(j)){
                   int[]  arr = Employeemap.get(j);
                   int signDays = arr[0];
                    Employeemap.put(j,new int[]{signDays+1,arr[1]});
                }
                else{
                    Employeemap.put(j,new int[]{1,dayId});
                }
            }

        }

        //输出员工打卡次数
        Set<Map.Entry<String, int[]>> tmpEntrySet =  Employeemap.entrySet();
        Iterator<Map.Entry<String, int[]>> iter = tmpEntrySet.iterator();  //获取entrySet集合的迭代器,Map.Entry<K, V>为迭代元素的类型
        while(iter.hasNext()){
            Map.Entry<String, int[]> item = iter.next();
            String key = item.getKey();
            int[] value = item.getValue();
            System.out.println("key:" + key + "-->value:" + value[0] +" "+ value[1]);
        }

        //对hashmap的 value进行排序
        List<int[]> ResList = new ArrayList<>();
        Set<Map.Entry<String, int[]>> tmpEntrySet1 =  Employeemap.entrySet();
        Iterator<Map.Entry<String, int[]>> iter1 = tmpEntrySet1.iterator();  //获取entrySet集合的迭代器,Map.Entry<K, V>为迭代元素的类型
        while(iter1.hasNext()){
            Map.Entry<String, int[]> item = iter1.next();
            String key = item.getKey();
            int[] value = item.getValue();
            ResList.add(new int[]{value[0] , value[1], Integer.parseInt(key)});
        }
        //二元数据排序
        //第二个元素从大到小排序
        // 第一个元素从小到大
        ResList.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        int cnt =0;
        for (int[] i : ResList) {
            if(cnt++ == 5) break;
            System.out.println("id:" + i[2] +" " );
        }


    }
}
