package com.fred.learning.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * License  :  Copyright @2019 learning Technology. All rights reserved.
 * Software :  todo
 * Author   :  zzf
 * Time     :  2019-11-28
 * Desc     :  给定一个整数数组和一个整数 k，判断数组中是否[存在]两个不同的索引 i 和 j，[使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k]。
    注意题意： 满足即返回！！！！
 示例 1:

 输入: nums = [1,2,3,1], k = 3
 输出: true
 示例 2:

 输入: nums = [1,0,1,1], k = 1
 输出: true
 示例 3:

 输入: nums = [1,2,3,1,2,3], k = 2
 输出: false

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContainsNearbyDuplicate {

    public static void main(String[] args) {

        int[] arr = new int[]{1,0,1,1};
        System.out.println(containsNearbyDuplicate(arr, 1));
    }


    /**
     * 题意理解有误
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean exit = false;//区分存在重复的数据的下标
        boolean flag = true;
        for (int i = 0; i < nums.length; i++) {
            //包含，说明前面已经有 下标 等于 nums[i]的 下标存在
            if (map.containsKey(nums[i])) {
                exit = true;//说明存在 重复的数据的下标
                Integer before = map.get(nums[i]);
                //进行index 比较, 有大于 就 false
                if (i - before > k) {
                    flag = false;
                }
            } else {
                //保存最小的下标
                map.put(nums[i], i);
            }
            
        }

        return exit & flag;
    }

    /**
     * 满足条件，即退出
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //包含，说明前面已经有 下标 等于 nums[i]的 下标存在
            if (map.containsKey(nums[i])) {
                Integer before = map.get(nums[i]);
                //进行index 比较, 有大于 就 false
                if (i - before <= k) {
                    return true;
                } else {//把下标更新
                    map.put(nums[i], i);
                }
            } else {
                //保存最小的下标
                map.put(nums[i], i);
            }

        }

        return false;
    }


    /**
     * 通过自平衡二叉搜索树来维护一个 kk 大小的滑动窗口。
     * 复杂度分析

     时间复杂度：O(n \log (\min(k,n)))O(nlog(min(k,n)))
     我们会做 nn 次 搜索，删除，插入 操作。每次操作将耗费对数时间，即为 \log (\min(k, n))log(min(k,n))。注意，虽然 kk 可以比 nn 大，但滑动窗口大小不会超过 nn。

     空间复杂度：O(\min(n,k))O(min(n,k))
     只有滑动窗口需要开辟额外的空间，而滑动窗口的大小不会超过 O(\min(n,k))O(min(n,k))。

     作者：LeetCode
     链接：https://leetcode-cn.com/problems/contains-duplicate-ii/solution/cun-zai-zhong-fu-yuan-su-ii-by-leetcode/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }


    /**
     * 用散列表来维护这个kk大小的滑动窗口
     * 复杂度分析

     时间复杂度：O(n)O(n)
     我们会做 nn 次 搜索，删除，插入 操作，每次操作都耗费常数时间。

     空间复杂度：O(\min(n, k))O(min(n,k))
     开辟的额外空间取决于散列表中存储的元素的个数，也就是滑动窗口的大小 O(\min(n,k))O(min(n,k))。

     作者：LeetCode
     链接：https://leetcode-cn.com/problems/contains-duplicate-ii/solution/cun-zai-zhong-fu-yuan-su-ii-by-leetcode/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate4(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

}
