package com.sven.ou.module.test.model.impl;

import android.content.Context;

import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.test.model.RecusionTest;

/**
 * Created by sven-ou on 2016/6/8.
 */
public class RecusionTestImpl implements RecusionTest {
    private Context applicationContext;
    public RecusionTestImpl(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public long test1(long n) {
        if (n == 1) {
            return 1;
        }
        return test1(n - 1) + n;
    }

    @Override
    public long test2(long[] a, int left, int right) {
        if(left == right){
            return a[right];
        }
        int middle = (left + right)/2;
        long leftMax = test2(a, left,middle);
        long rightMax = test2(a, middle + 1, right );
        return leftMax > rightMax ? leftMax: rightMax;
    }

    @Override
    public int test3(long[] a, long target, int left, int right) {
        if(left == right){
            if(target == a[left]){
                return left;
            }else{
                return -1;
            }
        }
        int middle = (left + right) / 2;
        int leftTargetPosition = test3(a, target, left, middle);
        if(leftTargetPosition != -1 && target == a[leftTargetPosition]){
            return leftTargetPosition;
        }
        int rightTargetPosition = test3(a, target, middle + 1, right);
        if(rightTargetPosition != -1 && target == a[rightTargetPosition]){
            return rightTargetPosition;
        }
        return -1;
    }

    @Override
    public void testAll(){
        Logger.e(TAG, "求1+2+3+……+100的值: " + this.test1(100));
        Logger.e(TAG, "分治法找最大值: " + this.test2(new long[]{ 3, 7, 5, 51, 787, 654 , 87, 7, 7, 88}, 0 , 9));
        Logger.e(TAG, "假定在A[1..9]中顺序存放这九个数：-7,-2,0,5,16,43,57,102,291 要求检索N是否在数组中: " + this.test3(new long[]{ -7,-2,0,5,16,43,57,102,291},16, 0 , 8));
    }
}
