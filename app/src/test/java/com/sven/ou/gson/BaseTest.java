package com.sven.ou.gson;

import com.google.gson.Gson;
import com.sven.ou.BaseUnitTest;

import org.junit.Test;

/**
 * Created by sven-ou on 2016/7/15.
 */
public class BaseTest extends BaseUnitTest{

    private static final String TAG = BaseTest.class.getSimpleName();

//    @Before
//    public void setUp() {
//        println("setUp");
//
//    }

//    @After
//    public void tearDown()  {
//        println("tearDown");
//    }

    @Test
    public void test1() throws Exception {
        Gson gson = new Gson();
        int i = gson.fromJson("100", int.class);
        double d = gson.fromJson("\"99.99\"", double.class);
        boolean b = gson.fromJson("true", boolean.class);
        String str = gson.fromJson("String", String.class);
        println("i = " + i);
        println("d = " + d);
        println("b = " + b);
        println("str = " + str);
    }
}
