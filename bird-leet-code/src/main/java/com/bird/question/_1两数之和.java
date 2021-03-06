package com.bird.question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2021/3/6 18:40
 * @Description 两数之和
 */
public class _1两数之和 {
    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * 你可以按任意顺序返回答案。

     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]

     * 示例 3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]

     * 提示：
     * 2 <= nums.length <= 103
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案

     * Related Topics 数组 哈希表
     */


    /**
     * @Author lipu
     * @Date 2021/3/6 18:40
     * @Description 解决方案
     */
    public int[] twoSum(int[] nums, int target){
        //初始化数组将元素放入到哈希表中提高查询效率
        Map<Integer,Integer> map=new HashMap<>();
        //循环遍历数组中的元素,寻找与之匹配的另外一个元素 目标元素=target-当前遍历的元素
        for (int i = 0; i <nums.length ; i++) {
            int other=target-nums[i];
            if (map.containsKey(other)){
                return new int[]{i,map.get(other)};
            }else {
                map.put(nums[i],i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        _1两数之和 client=new _1两数之和();
        int[] nums=new int[]{3,3};
        int target=6;
        int[] twoSum = client.twoSum(nums, target);
        System.out.println(Arrays.toString(twoSum));
    }
}
