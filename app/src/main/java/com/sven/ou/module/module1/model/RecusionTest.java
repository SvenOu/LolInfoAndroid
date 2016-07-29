package com.sven.ou.module.module1.model;

/**
 * Created by sven-ou on 2016/6/8.
 */
public interface RecusionTest {

    public static final String TAG = RecusionTest.class.getName();

    /**
     * 求1+2+3+……+n的值
     * @param n
     * @return
     */
    long test1(long n);

    /**
     * 分治法找最大值
     * @param a
     * @param left
     * @param right
     * @return
     */

    long test2(long[] a, int left, int right);

    /**
     * 假定在A[1..9]中顺序存放这九个数：-7,-2,0,5,16,43,57,102,291 要求检索291是否在数组中。
     * @param a
     * @param left 左边的索引
     * @param right 右边的索引
     * @return 找不到返回-1
     */
    int test3(long[] a, long target, int left, int right);

    /**
     * 测试所有的用例
     */
    public void testAll();
}
