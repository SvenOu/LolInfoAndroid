package com.sven.ou.module.lol.hackdaiwan;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.util.Log;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.launch.db.SearchHistory;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table(name = "token_info")
public class TokenInfo extends Model {

    public static final String TAG = TokenInfo.class.getSimpleName();

    public static final String USER_AGENT = "Mozilla";
    public static final String DAIWAN_LOL_LOGIN_PAGE_URL = "http://www.games-cube.com/central/User/login";
    public static final String DAIWAN_LOL_DATA_TOKEN_PAGE_URL = "http://www.games-cube.com/central/UserCenter/LOLTokenShow";
    public static final String DAIWAN_LOL_VIDEO_TOKEN_PAGE_URL = "http://www.games-cube.com/central/UserCenter/VideoTokenShow";
    public static final String DAIWAN_LOL_TENTACLE_TOKEN_PAGE_URL = "http://www.games-cube.com/central/UserCenter/LolTentacleTokenShow";
    public static final String DAIWAN_LOL_ACCOUNT_NAME = "SvenOu";
    public static final String DAIWAN_LOL_ACCOUNT_PASSWORD = "l123456";

    public static final String TOKEN_TYPE_DATA = "data";
    public static final String TOKEN_TYPE_VIDEO = "video";
    public static final String TOKEN_TYPE_TENTACLE = "tentacle";

    private static final SimpleDateFormat expiredDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

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
    public static void loginAndSaveAllToken() throws IOException, ParseException {

        Connection.Response loginPageForm = Jsoup.connect(DAIWAN_LOL_LOGIN_PAGE_URL)
                .userAgent(USER_AGENT)
                .method(Connection.Method.GET)
                .execute();

        String __RequestVerificationToken = loginPageForm.parse().select("input[name=__RequestVerificationToken]").get(0).attr("value");

        Connection.Response loginSuccessPage = Jsoup.connect(DAIWAN_LOL_LOGIN_PAGE_URL)
                .userAgent(USER_AGENT)
                .data("username", DAIWAN_LOL_ACCOUNT_NAME)
                .data("password", DAIWAN_LOL_ACCOUNT_PASSWORD)
                .data("__RequestVerificationToken", __RequestVerificationToken)
                .cookies(loginPageForm.cookies())
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .method(Connection.Method.POST)
                .execute();

        Document dataTokenPage = Jsoup.connect(DAIWAN_LOL_DATA_TOKEN_PAGE_URL)
                .cookies(loginSuccessPage.cookies())
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .get();

        String dataToken = dataTokenPage.select("h4").first().select("span").first().text();

        Document videoTokenPage = Jsoup.connect(DAIWAN_LOL_VIDEO_TOKEN_PAGE_URL)
                .cookies(loginSuccessPage.cookies())
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .get();

        String videoToken = videoTokenPage.select("h4").first().select("span").first().text();

        Document tentacleTonePage = Jsoup.connect(DAIWAN_LOL_TENTACLE_TOKEN_PAGE_URL)
                .cookies(loginSuccessPage.cookies())
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .get();

        String tentacleToken = tentacleTonePage.select("h4").first().select("span").first().text();
        String str = tentacleTonePage.select("h4").first().select("span").last().text();
        Date expiredDate = expiredDateFormat.parse(str.substring(0,str.length()-3));

        ActiveAndroid.beginTransaction();
        try {
            saveToken(dataToken, TOKEN_TYPE_DATA, expiredDate);
            saveToken(videoToken, TOKEN_TYPE_VIDEO, expiredDate);
            saveToken(tentacleToken, TOKEN_TYPE_TENTACLE, expiredDate);
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
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

    private static void saveToken(String token, String type, Date expiredDate){
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