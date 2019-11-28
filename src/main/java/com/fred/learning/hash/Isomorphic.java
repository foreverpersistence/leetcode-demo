package com.fred.learning.hash;

import java.util.HashMap;

/**
 * License  :  Copyright @2019 learning Technology. All rights reserved.
 * Software :  todo
 * Author   :  zzf
 * Time     :  2019-11-28
 * Desc     :  给定两个字符串 s 和 t，判断它们是否是同构的。

 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

 示例 1:

 输入: s = "egg", t = "add"
 输出: true
 示例 2:

 输入: s = "foo", t = "bar"
 输出: false
 示例 3:

 输入: s = "paper", t = "title"
 输出: true
 说明:
 你可以假设 s 和 t 具有相同的长度

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/isomorphic-strings
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Isomorphic {


    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.println(isIsomorphic("",""));
        System.out.printf("hash counsumer: %s ms \n", (System.currentTimeMillis() - start));

        long start1 = System.currentTimeMillis();
        System.out.println(isIsomorphic2("",""));
        System.out.printf("counsumer: %s ms", (System.currentTimeMillis() - start1));


    }

    /**
     * Hashmap
     * @param s
     * @param t
     * @return
     */
    public static boolean isIsomorphic(String s, String t) {

//        if (s.length() == 0 || t.length() == 0) {
//            return false;
//        }

        HashMap<Character, Character> map = new HashMap<>();
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();

        for(int i = 0; i < c1.length; i++) {
            if (map.containsKey(c1[i])) {
                if (!(map.get(c1[i]) == c2[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(c2[i])) {//会进入吗
                    System.out.println("containValue");
                    return false;
                }
                map.put(c1[i], c2[i]);
            }
        }

        return true;
    }

    /**
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isIsomorphic2(String s, String t) {

        HashMap<Character, Character> map = new HashMap<>();
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();

        for(int i = 0; i < c1.length; i++) {
            if (!(s.indexOf(c1[i]) == t.indexOf(c2[i]))) {
                return false;
            }
        }
        return true;
    }


}
