package com.sven.ou;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Created by sven-ou on 2016/7/15.
 */
public class BaseUnitTest {
    @BeforeClass
    public static void beforeClass() {
        System.out.println(new StringBuilder().append("--- in before class ---").append("\n").toString());
    }

    @AfterClass
    public static void  afterClass() {
        System.out.println("--- in after class ---");
    }

    protected void println(String s){
        System.out.println(s);
    }
}
