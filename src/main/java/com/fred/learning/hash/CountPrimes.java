package com.fred.learning.hash;

import java.util.Arrays;

/**
 * License  :  Copyright @2019 learning Technology. All rights reserved.
 * Software :  todo
 * Author   :  zzf
 * Time     :  2019-11-26
 * Desc     : https://leetcode-cn.com/problems/count-primes/solution/qiu-zhi-shu-fang-fa-zong-jie-by-biggrass/
 统计所有小于非负整数 n 的质数的数量。

 示例:

 输入: 10
 输出: 4
 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。

 思路： 质数是一定的，前提 过滤出 质数
 */
public class CountPrimes {

    public static void main(String[] args) {
//        System.out.println(countPrimes(2));
//        System.out.println(countPrimes(3));
//        System.out.println(countPrimes(10));

        System.out.println(Euler(10));
    }

    /**
     * 时间复杂度 O(n-1) * O(n/2) = O(n^2)
     * 最uilow，  超时
     * @param n
     * @return
     */
    public  static int countPrimes(int n) {
        long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 2; i < n; i++) {
            int prime = i;
            boolean isPrime = true;
            for (int j = 2; j <  + 1; j++) {
                if (i % j == 0) {
                    isPrime = false;
//                    continue;//有问题
                    break;
                }
            }
            if (isPrime) {
                count++;
            }
        }
        System.out.println("consumer: " + (System.currentTimeMillis() - start));

        return count;
    }


    /**
     * 暴力优化
     * @param n
     * @return
     */
    public int CountPrimes(int n)
    {
        if(n < 3)
            return 0;;
        //从3开始验算，所以初始值为1（2为质数）。
        int count = 1;
        for (int i = 3; i < n; i++)
        {
            //当某个数为 2 的 n 次方时（n为自然数），其 & (n - 1) 所得值将等价于取余运算所得值
            //*如果 x = 2^n ，则 x & (n - 1) == x % n
            //if(i % 2 == 0)
            if ((i & 1) == 0)
                continue; ;
            boolean sign = true;
            //用 j * j <= i 代替 j <= √i 会更好。
            //因为我们已经排除了所有偶数，所以每次循环加二将规避偶数会减少循环次数
            for (int j = 3; j * j <=i; j+=2)
            {
                if (i % j == 0)
                {
                    sign = false;
                    break;
                }
            }
            if (sign)
                count++; ;
        }
        return count;
    }


    /**
     * 埃拉托斯特尼筛法 or 厄拉多塞筛法？
     * 时间复杂度：O(n * loglog n)
     空间复杂度：O(n)

     第一个质数为2，在2上画圈，其倍数4/6/8不是质数，划掉4/6/8，继续遍历
     下一个质数为3，在3上画圈，其倍数6/9不是质数，划掉6/9，继续遍历
     下一个质数为5，在5上画圈，没有倍数，继续遍历
     下一个质数为7，在7上画圈，没有倍数，继续遍历。

     存在 有些元素重复判断！！

     作者：mmmmmJCY
     链接：https://leetcode-cn.com/problems/count-primes/solution/e-la-duo-sai-shai-fa-qiu-zhi-shu-java-by-zxy0917/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    int countPrimes2(int n) {
        boolean[] isPrim = new boolean[n];
        // 将数组都初始化为 true
        Arrays.fill(isPrim, true);

        for (int i = 2; i < n; i++)
            if (isPrim[i])
                // i 的倍数不可能是素数了
                for (int j = 2 * i; j < n; j += i)
                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }

    private static final int MAX_LENGTH_CHECK = 100000000;          // 亿
    private static final int MAX_LENGTH_PRIMELIST = 10000000;       // 千万

    private static boolean[] check = new boolean[MAX_LENGTH_CHECK]; // 存储标记
    private static int[] primeList = new int[MAX_LENGTH_PRIMELIST]; // 存储素数
    /**
     * 欧拉筛法：保证每个合数只会被它的最小质因数筛掉，时间复杂度降低到O(n)。
     * 每一个数都去乘以当前素数表里面已有的数，当 indexI 是合数，且 indexI % primeList[indexJ] == 0 时，只能乘以第一个素数 2
     * 参考：  https://blog.csdn.net/tinyDolphin/article/details/75208771
     */
    private static int Euler(int num) {
        int count = 0;
        for (int indexI = 2; indexI <= num; indexI++) {
            if (!check[indexI]) {
                primeList[count++] = indexI;
            }
            // 每一个数都去乘以当前素数表里面已有的数，如果 indexI 是合数，且 indexI % primeList[indexJ] == 0，那么它只能乘以第一个素数 2
            // 如：2×2、3×(2、3)、4×(2)、5×(2、3、5)、6×(2)、7×(2、3、5、7)、8×(2)、9×(2、3)、10×(2)
            for (int indexJ = 0; indexJ < count; indexJ++) {
                // 过大的时候跳出
                if (indexI * primeList[indexJ] > num) {
                    break;
                }
                check[indexI * primeList[indexJ]] = true;
                // 如果 indexI 是一个合数，而且 indexI % primeList[indexJ] == 0
                // 保证了每个合数只会被它的最小素因子筛掉
                if (indexI % primeList[indexJ] == 0) {
                    break;
                }
            }
        }
        return count;
    }


}
