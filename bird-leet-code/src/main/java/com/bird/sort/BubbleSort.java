package com.bird.sort;

/**
 * @Author lipu
 * @Date 2021/6/15 10:54
 * @Description 冒泡排序
 */
public class BubbleSort {

    /**
     * @Author lipu
     * @Date 2021/6/15 10:55
     * @Description 排序实现
     */
    public static void sort(int[] array){
        //临时变量用于交换
        int temp;
        //N个元素需要N-1次冒泡
        for (int i = 0; i < array.length-1; i++) {
            //每一轮冒泡比较次数递减
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j]>array[j+1]){
                    temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
    }

}
