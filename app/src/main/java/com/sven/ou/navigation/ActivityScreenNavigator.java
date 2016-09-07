package com.sven.ou.navigation;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sven.ou.R;
import com.sven.ou.common.base.BaseActivity;
import com.sven.ou.common.base.BaseFragment;
import com.sven.ou.module.lol.view.AboutUSFragment;
import com.sven.ou.module.lol.view.FoundFragment;
import com.sven.ou.module.lol.view.FriendFragment;
import com.sven.ou.module.lol.view.HomeFragment;
import com.sven.ou.module.lol.view.MyFragment;
import com.sven.ou.module.lol.view.SettingFragment;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 针对activity内的跳转
 * 通过dagger与activtiy的生命周期绑定
 */
public class ActivityScreenNavigator {
    public static final String KEY_HOME = "info";
    public static final String KEY_FRIEND = "friend";
    public static final String KEY_DISCOVER = "discover";
    public static final String KEY_MY = "my";
    public static final String KEY_SETTING = "setting";
    public static final String KEY_ABOUT_US = "about_us";

    private FragmentManager fragmentManager;
    /**
     * null 表示已经在home界面
     */
    private String CURRENT_TAG = null;
    /**
     * 仅记录CURRENT_TAG层后面的历史
     */
    //界面跳转的历史栈
    private Stack<String> historyStack;
    private Map<String, BaseFragment> baseFragmentMap;

    private BaseActivity baseActivity;

    private int fragmentContainerId = R.id.fra_container;

    public ActivityScreenNavigator(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
        this.fragmentManager = baseActivity.getSupportFragmentManager();
        this.baseFragmentMap = new HashMap<>();
    }

    public void swicthScreenByKey(String key){
        BaseFragment targetFragment = getFragmentByKey(key);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, targetFragment, key);
        fragmentTransaction.commit();
//        if(this.historyStack.contains(key)){
//
//        }else{
//
//        }
    }

    private BaseFragment getFragmentByKey(String key){
        BaseFragment fragment = this.baseFragmentMap.get(key);
        if(null == fragment){
            switch (key){
                case KEY_HOME:
                    fragment = new HomeFragment(key);
                    break;
                case KEY_FRIEND:
                    fragment = new FriendFragment(key);
                    break;
                case KEY_DISCOVER:
                    fragment = new FoundFragment(key);
                    break;
                case KEY_MY:
                    fragment = new MyFragment(key);
                    break;
                case KEY_SETTING:
                    fragment = new SettingFragment(key);
                    break;
                case KEY_ABOUT_US:
                    fragment = new AboutUSFragment(key);
                    break;
                default:
                    break;
            }
        }
        this.baseFragmentMap.put(key, fragment);
        return fragment;
    }



//    private void swicthScreenForMenu(BaseFragment targetFragment) {
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        String key = targetFragment.getFragmentId();
//        if(KEY_HOME.equals(key)){
//            if(!this.fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//                    || this.fragmentManager.getBackStackEntryCount() == 0){
//                fragmentTransaction.replace(fragmentContainerId, targetFragment, key);
//                fragmentTransaction.addToBackStack(key);
//                historyStack.clear();
//                historyStack.push(key);
//            };
//        }else{
//            Fragment cacheFragment =  this.fragmentManager.findFragmentByTag(key);
//            boolean hasPoppedBackStack = this.fragmentManager.popBackStackImmediate(key, 0);
//            if(!hasPoppedBackStack && (null == cacheFragment)){
//                fragmentTransaction.replace(fragmentContainerId, targetFragment, key);
//                fragmentTransaction.addToBackStack(key);
//                fragmentTransaction.
//                while (historyStack.size() > 1){
//                    historyStack.pop();
//                }
//                historyStack.push(key);
//            }
//        }
//        fragmentTransaction.commit();
//    }

}
