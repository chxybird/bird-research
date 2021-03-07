package com.bird.sort;

import java.util.Arrays;

/**
 * @Author lipu
 * @Date 2021/3/6 23:31
 * @Description 排序算法
 */
public class BirdSort {

    /**
     * @Author lipu
     * @Date 2021/3/6 23:32
     * @Description 冒泡排序
     */
    public static int[] bubble(int[] array) {
        //临时变量用于交换
        int temp = 0;
        //N个元素,需要N-1轮冒泡
        for (int i = 0; i < array.length - 1; i++) {
            //每一轮冒泡比较次数逐渐减少
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    /**
     * @Author lipu
     * @Date 2021/3/7 17:58
     * @Description 选择排序
     */
    public static int[] selectSort(int[] array) {
        //临时变量用于交换
        int temp;
        //索引 用于记录所选择的最小元素的索引
        int index = 0;
        //N个元素总共进行N次选择
        for (int i = 0; i <= array.length - 1; i++) {
            //每次选择默认以第一个元素为最小元素
            int sentinel = array[i];
            //每次选择的集合数量依次减少
            for (int j = i; j <= array.length - 1; j++) {
                //每次选择一个最小的数
                if (array[j] <= sentinel) {
                    index = j;
                    sentinel = array[j];
                }
            }
            //将选择的最小数加入到当前轮次的集合中
            temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return array;
    }

    /**
     * @Author lipu
     * @Date 2021/3/7 17:58
     * @Description 插入排序
     */
    public static int[] insertSort(int[] array) {
        //当前要插入的元素
        int sentinel = 0;
        //当前要插入的位置
        int index=0;
        //第一个元素默认有序
        for (int i = 1; i <= array.length - 1; i++) {
            //当前要插入的元素
            sentinel = array[i];
            //确定插入的位置
            for (int j = i; j > 0; j--) {
                if (sentinel < array[j-1]) {
                    //元素后移
                    array[j]=array[j-1];
                }else {
                    //记录位置
                    index=j;
                    break;
                }
            }
            //插入元素
            array[index]=sentinel;
        }
        return array;
    }

    /**
     * @Author lipu
     * @Date 2021/3/7 17:58
     * @Description 快速排序
     */
    public static void quickSort(int[] array, int start, int end) {
        //设置高低指针位
        int low = start;
        int high = end;
        //设置基准和临时变量
        int sentinel = array[0];
        int temp;
        //小于基准的数字在左 大于基准的数字在右 左右指针相遇一次快排结束
        while (low < high) {
            //从右往左筛选一个小于基准的数
            while (array[high] >= sentinel && low < high) {
                high--;
            }
            //从左往右筛选一个大于基准的数
            while (array[low] <= sentinel && low < high) {
                low++;
            }

            //交换两数位置 交换之前保证指针有没有相遇 否则不交换
            if (low < high) {
                temp = array[low];
                array[low] = array[high];
                array[high] = temp;
            }
        }

        //将基准放入到指针相遇的位置此时小于基准的数字在左 大于基准的数字在右
        array[0] = array[high];
        array[high] = sentinel;
        //相同的方法递归分治左右数组
        quickSort(array, 0, low - 1);
        quickSort(array, low + 1, array.length - 1);
    }

    public static void main(String[] args) {
        int[] array = new int[]{10, 5, 25, 4, 1, 56, 48, 99, 2, 12};
//        BirdSort.quickSort(array, 0, array.length - 1);
        int[] selectSort = BirdSort.selectSort(array);
        int[] insertSort = BirdSort.insertSort(array);
        System.out.println(Arrays.toString(insertSort));
    }
}
