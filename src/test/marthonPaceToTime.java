package test;

// 马拉松每公里配速转换成总时间
public class marthonPaceToTime {
    // 马拉松长度
    static double marthon =42.195;
    static double halfMarthon =21.0975;
    public static void main(String[] args) {

        // 1h=60′，1′=60″，即1h=60′=3600″
        // double timeNums = (4 * 60 + 06) * 42.195;
        String pace ="3:20";
        System.out.println("每公里配速 " + pace + " = 全马时间 " + marthonPaceToTime(pace));
        System.out.println("每公里配速 " + pace + " = 半马时间 " + halfMarthonPaceToTime(pace));

    }
    static String marthonPaceToTime(String PerKmPace) {
        String res ="";
        double timeNums=0;
        String[] buff =PerKmPace.split(":");
        for(int i=0;i<buff.length;i++){
            // System.out.println(Integer.valueOf(buff[i]).intValue());
            timeNums+= Integer.valueOf(buff[i]).intValue()*Math.pow(60, 1-i);
        }
        // System.out.println(timeNums);
        timeNums =timeNums * marthon;
        // System.out.println(timeNums);
        res =  (int) Math.floor(timeNums / 3600) + ":" + (int) Math.floor(timeNums % 3600 / 60) + ":" + (int) Math.floor(timeNums % 3600 % 60);
        return res;
    }

    static String halfMarthonPaceToTime(String PerKmPace) {
        String res ="";
        double timeNums=0;
        String[] buff =PerKmPace.split(":");
        for(int i=0;i<buff.length;i++){
            // System.out.println(Integer.valueOf(buff[i]).intValue());
            timeNums+= Integer.valueOf(buff[i]).intValue()*Math.pow(60, 1-i);
        }
        // System.out.println(timeNums);
        timeNums =timeNums * halfMarthon;
        // System.out.println(timeNums);
        res =  (int) Math.floor(timeNums / 3600) + ":" + (int) Math.floor(timeNums % 3600 / 60) + ":" + (int) Math.floor(timeNums % 3600 % 60);
        return res;
    }

}
