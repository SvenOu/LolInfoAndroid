package com.sven.ou.module.test.model.impl;

import android.content.Context;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.entity.Page;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.lol.entity.Area;
import com.sven.ou.module.lol.entity.Author;
import com.sven.ou.module.lol.entity.Champion;
import com.sven.ou.module.lol.entity.Combat;
import com.sven.ou.module.lol.entity.OnlineStatus;
import com.sven.ou.module.lol.entity.SkillRank;
import com.sven.ou.module.lol.entity.UserHotInfo;
import com.sven.ou.module.lol.entity.Video;
import com.sven.ou.module.lol.entity.thisweek.Hero;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.module.lol.entity.UserArea;
import com.sven.ou.module.test.model.LolApiTest;
import com.sven.ou.network.Network;

import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sven-ou on 2016/6/15.
 */
public class LolApiTestImpl implements LolApiTest {
    private static final String TAG = LolApiTestImpl.class.getName();


    private Context applicationContext;

    public LolApiTestImpl(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void testDaiWaiLolVideoApi() {
        Network.getDaiWanLolVideoApi().getAuthorVideos("1",1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Video>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Video>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
        Network.getDaiWanLolVideoApi().getNewstVideos(1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Video>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Video>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
        Network.getDaiWanLolVideoApi().getHeroVideos(1,1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Video>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Video>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolVideoApi().findVideos("盖伦",1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Video>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Video>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

    }

    @Override
    public void testDaiWaiLolDataApi() {
        Network.getDaiWanLolDataApi().getUserArea("SvenOu").
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<UserArea>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<UserArea>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
        Network.getDaiWanLolDataApi().getUserHotInfo("U17347121168407285161", 5).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<UserHotInfo>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<UserHotInfo>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }

                });

        Network.getDaiWanLolDataApi().getSkillRank("1",1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Page<List<SkillRank>>>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Page<List<SkillRank>>>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }

                });
        Network.getDaiWanLolDataApi().getFree().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map<String, Hero>>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map<String, Hero>>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolVideoApi().getAuthors().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Author>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Author>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
        Network.getDaiWanLolDataApi().getChampionDetail("1").
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
        Network.getDaiWanLolDataApi().getOnline("miss", 14).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<OnlineStatus>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<OnlineStatus>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getCombatList("U592732592618235552", 14, 1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Combat>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Combat>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getGameDetail("U592732592618235552", 14, "2013153794").
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getChampion().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Champion>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Champion>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getArea().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Area>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Area>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getUserIcon(552).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getChampionIconById(64).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getSummonSpellIcon(1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getChampionCNName(1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });

        Network.getDaiWanLolDataApi().getChampionENName(1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
        Network.getDaiWanLolDataApi().getMapName(1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
        Network.getDaiWanLolDataApi().getJudgement(1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
        Network.getDaiWanLolDataApi().getWin(1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
        Network.getDaiWanLolDataApi().getGameType(1).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolObserver<DaiWanLolResult<List<Map>>>(applicationContext) {
                    @Override
                    public void onNext(DaiWanLolResult<List<Map>> daiWanLolResult) {
                        Logger.e(TAG, daiWanLolResult.toString());
                    }
                });
    }
}
