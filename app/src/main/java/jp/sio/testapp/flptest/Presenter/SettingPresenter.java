package jp.sio.testapp.flptest.Presenter;

import android.app.Activity;

import jp.sio.testapp.flptest.Activity.SettingActivity;
import jp.sio.testapp.flptest.L;
import jp.sio.testapp.flptest.R;
import jp.sio.testapp.flptest.Usecase.SettingUsecase;

/**
 * Created by NTT docomo on 2017/05/24.
 * SettingActivityとSettingUsecaseの橋渡し
 */

public class SettingPresenter {
    SettingActivity activity;
    SettingUsecase settingusecase;

    public SettingPresenter(SettingActivity activity){
        this.activity = activity;
        settingusecase = new SettingUsecase(activity);
    }

    /**
     * 現在Activityに入力されている値を保存する
     */
    public void commitSetting(){
        String locationTyep = activity.getResources().getString(R.string.locationFlpBalancedPowerAccuracy);
        if(activity.isRadioButtonFlpBalancedPower()){
            locationTyep = activity.getResources().getString(R.string.locationFlpBalancedPowerAccuracy);
        }else if(activity.isRadioButtonFlpHighAccuracy()) {
            locationTyep = activity.getResources().getString(R.string.locationFlpHighAccuracy);
        }else if(activity.isRadioButtonFlpLowPower()) {
            locationTyep = activity.getResources().getString(R.string.locationFlpLowPower);
        }
        else if(activity.isRadioButtonFlpNoPower()) {
            locationTyep = activity.getResources().getString(R.string.locationFlpNoPower);
        }
        settingusecase.setLocationType(locationTyep);

        settingusecase.setCount(activity.getCount());
        settingusecase.setInterval(activity.getInterval());
        settingusecase.setTimeout(activity.getTimeout());
        settingusecase.setIsCold(activity.isColdCheck());
        settingusecase.setDelAssistDataTime(activity.getDelAssistDataTime());
        settingusecase.setSuplEndWaitTIme(activity.getSuplEndWaitTime());
        settingusecase.commitSetting();
    }
    /**
     * 現在保存されている値をActivityに表示する
     */
    public void loadSetting(){
        String locationType = settingusecase.getLocationType();
        if(locationType.equals(activity.getResources().getString(R.string.locationFlpBalancedPowerAccuracy))) {
            activity.enableRadioButtonFlpBalancedPowerAccuracy();
        }else if(locationType.equals(activity.getResources().getString(R.string.locationFlpHighAccuracy))) {
            activity.enableRadioButtonFlpHighAccuracy();
        }else if(locationType.equals(activity.getResources().getString(R.string.locationFlpLowPower))){
            activity.enableRadioButtonFlpLowPower();
        }else if(locationType.equals(activity.getResources().getString(R.string.locationFlpNoPower))) {
            activity.enableRadioButtonFlpNoPower();
        }
        activity.setCount(settingusecase.getCount());
        activity.setInterval(settingusecase.getInterval());
        activity.setTimeout(settingusecase.getTimeout());
        if(settingusecase.getIsCold()) {
            activity.enableIsCold();
        }else {
            activity.disableIsCold();
        }
        activity.setDelAssistDataTime(settingusecase.getDelAssistDataTime());
        activity.setSuplEndWaitTime(settingusecase.getSuplEndWaitTime());
    }
}
