package com.bird.sort;

/**
 * @Author lipu
 * @Date 2021/6/15 11:57
 * @Description 插入排序
 */
public class InsertSort {

    /**
     * @Author lipu
     * @Date 2021/6/15 11:57
     * @Description 排序实现
     */
    public static void sort(int[] array) {
        //N个元素默认第一个元素有序 从第二个元素开始
        for (int i = 1; i < array.length; i++) {
            //记录当前插入的元素
            int guard = array[i];
            //定义 要插入的索引
            int index=0;
            //插入元素
            for (int j = i - 1; j >= 0; j--) {
                if (guard <= array[j]) {
                    //元素后移
                    array[j + 1] = array[j];
                } else {
                    //找到位置
                    index = j + 1;
                    break;
                }
            }
            //插入元素
            array[index]=guard;
        }
    }
}
