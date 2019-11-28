package com.fred.learning.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * License  :  Copyright @2019 learning Technology. All rights reserved.
 * Software :  todo
 * Author   :  zzf
 * Time     :  2019-11给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

 说明：

 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

 示例 1:

 输入: [2,2,1]
 输出: 1
 示例 2:

 输入: [4,1,2,1,2]
 输出: 4

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/single-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。 todo
 */
public class SingleNumberFind {

    public static void main(String[] args) {

    }


    /**
     * 时间复杂度O(2N)
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            Integer integer = map.get(nums[i]);
            if (integer == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], i + 1);
            }
        }

        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return 0;
    }

    /**
     * 异或操作
     * @param nums
     * @return
     */
    public static int singleNumber2(int nums[]) {
        int result = 0;
        for (int i = 0; i < nums.length; i++)  {
            // ^ 为C#提供的按位异或操作符，而 ^= 相似 += ,其效果等价于 result = result ^ nums[i]
            result ^= nums[i];
        }
        return result;

    }
}
