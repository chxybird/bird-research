package com.bird.sort;

/**
 * @Author lipu
 * @Date 2021/6/15 11:22
 * @Description 选择排序
 */
public class SelectionSort {

    /**
     * @Author lipu
     * @Date 2021/6/15 11:23
     * @Description 排序实现
     */
    public static void sort(int[] array){
        //临时变量 用于交换
        int temp;
        //索引用于记录被选择的元素索引
        int index;
        //N个元素需要做N次选择
        for (int i = 0; i < array.length; i++) {
            //初始化索引
            index=i;
            //每次选择的元素集合递减
            for (int j = i; j < array.length; j++) {
                if (array[j]<array[index]){
                    index=j;
                }
            }
            //将选择到的元素进行排序
            temp=array[i];
            array[i]=array[index];
            array[index]=temp;
        }
    }
}
