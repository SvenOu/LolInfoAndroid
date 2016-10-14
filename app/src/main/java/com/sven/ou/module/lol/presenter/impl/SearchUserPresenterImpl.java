package com.sven.ou.module.lol.presenter.impl;

import android.content.Context;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.lol.db.Area_;
import com.sven.ou.module.lol.entity.Area;
import com.sven.ou.module.lol.entity.Author;
import com.sven.ou.module.lol.entity.UserHotInfo;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.module.lol.presenter.SearchUserPresenter;
import com.sven.ou.network.Network;

import java.util.ArrayList;
import java.util.List;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SearchUserPresenterImpl implements SearchUserPresenter{
    private static final String TAG = SearchUserPresenterImpl.class.getSimpleName();

    private Context appContext;

    public SearchUserPresenterImpl(Context appContext) {
        this.appContext = appContext;
    }

    @Override
    public void loadUserArea(Observer observer) {
        Network.getDaiWanLolDataApi().getArea().
                subscribeOn(Schedulers.io()).
                observeOn(Schedulers.io()).
                map(new Func1<DaiWanLolResult<List<Area>>, DaiWanLolResult<List<Area>>>() {
                    @Override
                    public DaiWanLolResult<List<Area>> call(DaiWanLolResult<List<Area>> listDaiWanLolResult) {
                        List<Area> areaList = listDaiWanLolResult.getData();
                        if(null != areaList &&  areaList.size() > 0){
                            ActiveAndroid.beginTransaction();
                            try {
                                for(Area area: areaList){
                                    saveArea(area);
                                }
                                ActiveAndroid.setTransactionSuccessful();
                            }
                            finally {
                                ActiveAndroid.endTransaction();
                            }
                        }

                        List<Area_> area_s = new Select().from(Area_.class).execute();
                        List<Area> result = new ArrayList<Area>();
                        for(Area_ area_: area_s){
                            result.add(area_.toArea());
                        }
                        listDaiWanLolResult.setData(result);

                        return listDaiWanLolResult;
                    }
                }).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(observer);
    }

    @Override
    public void getUserArea(String keyword, Observer observer) {
        Network.getDaiWanLolDataApi().getUserArea(keyword).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(observer);
    }

    @Override
    public void getUserBaseInfo(String qquin,  int vaidObserver, Observer observer) {
        Network.getDaiWanLolDataApi().getUserHotInfo(qquin, vaidObserver).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(observer);
    }

    private void saveArea(Area area) {
        Area_ dbArea_ =
                new Select().from(Area_.class).
                        where("area_id = ?", area.getAreaId()).executeSingle();
        if(null == dbArea_){
            dbArea_ = new Area_();
        }
        dbArea_.setAreaId(area.getAreaId());
        dbArea_.setStrid(area.getStrid());
        dbArea_.setIsp(area.getIsp());
        dbArea_.setName(area.getName());
        dbArea_.setIdc(area.getIdc());
        dbArea_.setTcls(area.getTcls());
        dbArea_.setOb(area.getOb());
        dbArea_.save();
    }
}
