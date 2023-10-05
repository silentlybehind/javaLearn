package test;
import java.util.BitSet;


// bitmap基础 https://cloud.tencent.com/developer/article/1630137?areaSource=103001.1&traceId=iFsZFs9owR4_LBpHyDhuc
// 创建 查询是否存在
public class BitMapBasic {
    // 一个 byte占用8bit，那么可以表示 0~7 的数;byte[10] 占用80bit，那么可以表示 0~79的数
    private byte[] data;

    private int capacity;

    public BitMapBasic(int capacity){
        this.capacity=capacity;
        data = new byte[capacity/8 +1]; // 数组容量  capacity >> 3
    }

    public void bitMapAdd(int num){
        //数组下标
        int arrIndex = num/8;
        //数组下标对应的bit位置
        int position = num%8;
        data[arrIndex] |= 1 << position ; //  将1向左移动position位,存储在对应的bit位置
    }
    public void bitMapRemove(int num){
        //数组下标
        int arrIndex = num/8;
        //数组下标对应的bit位置
        int position = num%8;
        data[arrIndex] &= ~(1 << position) ; //  将1向左移动position位,取反
    }
    public Boolean isContain(int num){
        int arrIndex = num/8;
        int position = num%8;
        return (data[arrIndex] & 1 << position) != 0;  // 将1向左移动position位，对应位置需要和原数一致
    }

    public static void main(String[] args) {
        BitMapBasic bitmap = new BitMapBasic(100);
        bitmap.bitMapAdd(1);
        System.out.println("是否存在10:"+ bitmap.isContain(10));

        System.out.println(bitmap.data[0]);

        bitmap.bitMapRemove(10);
        System.out.println("是否存在10:"+ bitmap.isContain(10));


        BitSet  bitset = new BitSet();
        bitset.set(10);
        bitset.get(0, 3);
        System.out.println(bitset.toString());

    }
}
