package com.ahmadrosid.olxlite.core.home;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.ahmadrosid.lib.baseapp.core.BasePresenter;
import com.ahmadrosid.lib.baseapp.helper.BaseRecyclerAdapter;
import com.ahmadrosid.olxlite.R;
import com.ahmadrosid.olxlite.core.detail.DetailIklanActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.ahmadrosid.olxlite.api.OlxLiteApi.getIklan;

/**
 * Created by ocittwo on 1/30/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class HomeFragmentPresenter extends BasePresenter<ViewHome> {
    private static final String TAG = "HomeFragmentPresenter";
    List<IklanModel> data = new ArrayList<>();
    private Activity activity;

    public HomeFragmentPresenter(ViewHome mvpView, Activity baseActivity) {
        super(mvpView);
        this.activity = baseActivity;
    }

    public void loadCommonIklan() {
        
    }

    private void onResponse(String json) {
        Log.d(TAG, "onResponse: " + json);
        getMvpView().stopLoading();
        parseData(json);
    }

    private void parseData(String json) {
        
    }

    private void setAdapter() {
        BaseRecyclerAdapter<IklanModel, ItemIklanHolder> adapter =
                new BaseRecyclerAdapter<IklanModel, ItemIklanHolder>(
                        data, IklanModel.class,
                        R.layout.item_list_iklan, ItemIklanHolder.class) {
                    @Override protected void bindView(ItemIklanHolder holder, IklanModel model, int position) {
                        holder.bindView(model);
                        holder.itemView.setOnClickListener(view -> {
                            Intent intent = new Intent(activity, DetailIklanActivity.class);
                            intent.putExtra("data", model);
                            view.getContext().startActivity(intent);
                        });
                    }
                };
        getMvpView().setDataIklan(adapter);
    }

    private void onError(Throwable throwable) {
        Log.e(TAG, "onError: ", throwable);
        getMvpView().stopLoading();
        getMvpView().showMessage("Failed load data.");
    }

    @Override public void detachView() {
        super.detachView();
    }

    public void refreshIklan() {
        getSubscraiber().add(
                getIklan()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onRefresh, this::onErrorRefresh)
        );
    }

    private void onErrorRefresh(Throwable throwable) {
        Log.e(TAG, "onErrorRefresh: ", throwable);
        getMvpView().dismishrefresh();
    }

    private void onRefresh(String json) {
        parseData(json);
        getMvpView().dismishrefresh();
    }
}
