package com.sven.ou.module.lol.hackdaiwan;

import android.app.Activity;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import com.sven.ou.R;
import com.sven.ou.common.config.Config;
import com.sven.ou.common.utils.Logger;


import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table(name = "token_info")
public class TokenInfo extends Model {

    public static final String TAG = TokenInfo.class.getSimpleName();

    public static final String DAIWAN_LOL_ACCOUNT_NAME = "SvenOu";
    public static final String DAIWAN_LOL_ACCOUNT_PASSWORD = "l123456";
    public static final String JS_VAR_NAME = "control";
    public static final String DAIWAN_LOLBASE_URL = "http://user.games-cube.com";
    //相对路径
    public static final String DAIWAN_LOL_LOGIN_PAGE_URL = "/login.aspx";
    public static final String DAIWAN_LOL_LOGIN_SUCCESS_URL = "/index.aspx";
    public static  final String DAIWAN_LOL_DATA_TOKEN_PAGE_URL = "/api/LoLToken.aspx";
    public static final  String DAIWAN_LOL_VIDEO_TOKEN_PAGE_URL = "/api/VideoToken.aspx";
    public static final  String DAIWAN_LOL_TENTACLE_TOKEN_PAGE_URL ="/api/LolTentacleToken.aspx";
    public static final  String DAIWAN_LOL_REFRESH ="refresh";
    private static String currentPage;
    private static final SimpleDateFormat expiredDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static WeakReference<Activity> activityWeakReference;

    public static final String TOKEN_TYPE_DATA = "data";
    public static final String TOKEN_TYPE_VIDEO = "video";
    public static final String TOKEN_TYPE_TENTACLE = "tentacle";

    private static String mDataToken;
    private static String mVideoToken;
    private static String mTentacleToken;
    private static String mExpiredDate;
    private static GetTokenSCallBack callback;

    private static boolean isRequestingToken = false;


    @Column(name = "token", unique = true)
    private String token;
    /**
     *  data, video, tentacle
     */
    @Column(name = "type")
    private String type;

    @Column(name = "expire_date")
    private Date expireDate;

    public TokenInfo(){
        super();
    }

    /**
     * 获取所有token，并保存到数据库
     * 此操作属于耗时操作，需要在线程内执行
     */
    public static void loginAndSaveAllToken(Activity activity, GetTokenSCallBack getTokenSCallBack) throws IOException, ParseException {
        activityWeakReference = new WeakReference<>(activity);
        callback = getTokenSCallBack;
        WebView hackDaiWanWebview = (WebView) activity.findViewById(R.id.hackDaiWanWebview);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Config.isDevelopMode()) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        WebSettings settings = hackDaiWanWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        hackDaiWanWebview.addJavascriptInterface(new JsInteration(), JS_VAR_NAME);
        hackDaiWanWebview.setWebChromeClient(new WebChromeClient() {
        });
        hackDaiWanWebview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(DAIWAN_LOL_LOGIN_PAGE_URL.equals(currentPage)){
                    loginDaiWan();
                } else if(DAIWAN_LOL_LOGIN_SUCCESS_URL.equals(currentPage)){
                    gotoGetDataTokenPage();
                }else if((DAIWAN_LOL_DATA_TOKEN_PAGE_URL + DAIWAN_LOL_REFRESH).equals(currentPage)){
                    refreshToken(DAIWAN_LOL_DATA_TOKEN_PAGE_URL);
                }else if(DAIWAN_LOL_DATA_TOKEN_PAGE_URL.equals(currentPage)){
                    getDataToken();
                }else if((DAIWAN_LOL_VIDEO_TOKEN_PAGE_URL + DAIWAN_LOL_REFRESH).equals(currentPage)){
                    refreshToken(DAIWAN_LOL_VIDEO_TOKEN_PAGE_URL);
                }else if(DAIWAN_LOL_VIDEO_TOKEN_PAGE_URL.equals(currentPage)){
                    getVideoToken();
                }else if((DAIWAN_LOL_TENTACLE_TOKEN_PAGE_URL + DAIWAN_LOL_REFRESH).equals(currentPage)){
                    refreshToken(DAIWAN_LOL_TENTACLE_TOKEN_PAGE_URL);
                }else if(DAIWAN_LOL_TENTACLE_TOKEN_PAGE_URL.equals(currentPage)){
                    getTentacleToken();
                }
            }

        });
        currentPage = DAIWAN_LOL_LOGIN_PAGE_URL;
        hackDaiWanWebview.loadUrl(DAIWAN_LOLBASE_URL + DAIWAN_LOL_LOGIN_PAGE_URL);
    }

    private static void loginDaiWan() {
        runJs("$('input[name=txt_username]').val('" + DAIWAN_LOL_ACCOUNT_NAME +
                "'); $('input[name=txt_password]').val('" + DAIWAN_LOL_ACCOUNT_PASSWORD + "');" +
                "__doPostBack('btn_ok','');" +
                "window.control.onSetCurrentPage('" + DAIWAN_LOL_LOGIN_SUCCESS_URL + "');", 100);
    }

    private static void gotoGetDataTokenPage() {
        runJs("window.control.onSetCurrentPage('" + DAIWAN_LOL_DATA_TOKEN_PAGE_URL + DAIWAN_LOL_REFRESH + "');" +
                "window.location.href = '" + DAIWAN_LOL_DATA_TOKEN_PAGE_URL + "'; ", 100);
    }

    public static void getDataToken() {
        runJs("var dataToken = $('div[class~=\"alert-success\"] h4').text().trim(); " +
                "window.control.onGetDataToken(dataToken); " +
                "window.location.href = '" + DAIWAN_LOL_VIDEO_TOKEN_PAGE_URL + "';" +
                "window.control.onSetCurrentPage('" + DAIWAN_LOL_VIDEO_TOKEN_PAGE_URL + DAIWAN_LOL_REFRESH + "');", 500);
    }

    public static void refreshToken(String currentPage) {
        runJs("window.control.onSetCurrentPage('" + currentPage + "');" +
                "try{__doPostBack('lnk_request','');}catch(err){}", 100);
        //"window.location.href = '" + currentPage + "'; "
    }

    private static void getVideoToken() {
        runJs("var videoToken = $('div[id=wrapper] section h4').text().trim();" +
                "window.control.onGetVideoToken(videoToken); " +
                "window.location.href = '" + DAIWAN_LOL_TENTACLE_TOKEN_PAGE_URL + "';" +
                "window.control.onSetCurrentPage('" + DAIWAN_LOL_TENTACLE_TOKEN_PAGE_URL + DAIWAN_LOL_REFRESH + "');", 500);
    }
    private static void getTentacleToken() {
        runJs("var tentacleToken = $('div[id=wrapper] section h4').text().trim(); " +
                "var str = $($('div[id=wrapper] div[class=form-group]')[3]).text().trim(); " +
                "var expiredDate = str.substring(str.length-20, str.length).trim(); " +
                "window.control.onGetTentacleToken(tentacleToken, expiredDate); ",500);
    }

    public static class JsInteration {
        @JavascriptInterface
        public void onSetCurrentPage(String curPage) {
            currentPage = curPage;
        }

        @JavascriptInterface
        public void onGetDataToken(String dataToken) {
            mDataToken = dataToken;
        }

        @JavascriptInterface
        public void onGetVideoToken(String videoToken) {
            mVideoToken = videoToken;
        }

        @JavascriptInterface
        public void onGetTentacleToken(String tentacleToken, String expiredDate) throws ParseException {
            mTentacleToken = tentacleToken;
            mExpiredDate = expiredDate;
            Date expDate = expiredDateFormat.parse(expiredDate);
            ActiveAndroid.beginTransaction();
            try {
                saveToken(mDataToken, TOKEN_TYPE_DATA, expDate);
                saveToken(mVideoToken, TOKEN_TYPE_VIDEO, expDate);
                saveToken(mTentacleToken, TOKEN_TYPE_TENTACLE, expDate);
                ActiveAndroid.setTransactionSuccessful();
            }
            finally {
                ActiveAndroid.endTransaction();
            }
            callback.onGetAllToken(mDataToken, mVideoToken, mTentacleToken, expDate);
        }
    }

    public interface GetTokenSCallBack{
        void onGetAllToken(String dataToken, String videoToken, String tentacleToken, Date expiredDate);
    }

    private static void runJs(final String jsScript, final int timeout) {
        final Activity act = activityWeakReference.get();
        if(null == act){
            Logger.e(TAG, "Activity is null, cannot runJs: " + jsScript);
            return;
        }
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String call = "javascript:setTimeout(function(){window.callFromJava = function () {" + jsScript + "}; window.callFromJava();}, "+ timeout +");";
                WebView hackDaiWanWebview = (WebView) act.findViewById(R.id.hackDaiWanWebview);
                hackDaiWanWebview.loadUrl(call);
            }
        });
    }

    /**
     * 通过type获取数据库里最新的token
     * 此操作属于耗时操作，需要在线程内执行
     */
    public static TokenInfo findAvalableTokenByType(String type){;
        TokenInfo tokenInfo =
                new Select().from(TokenInfo.class).
                        where("type = ?", type)
                        .orderBy("expire_date DESC")
                        .executeSingle();
        if(null == tokenInfo){
            Logger.e(TAG, "cannot find tokenInfo !");
        }
        return tokenInfo;
    }

    public static void saveToken(String token, String type, Date expiredDate){
        TokenInfo tokenInfo =
                new Select().from(TokenInfo.class).
                        where("token = ?", token).executeSingle();
        if(null == tokenInfo){
            tokenInfo = new TokenInfo();
        }
        tokenInfo.setToken(token);
        tokenInfo.setType(type);
        tokenInfo.setExpireDate(expiredDate);
        tokenInfo.save();
    }

    public boolean tokenIsExpired(){
        return expireDate.before(new Date());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", expireDate=" + expireDate +
                '}';
    }
}