package com.huyingbao.simple;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxListFragment;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;
import com.huyingbao.simple.adapter.ProductAdapter;
import com.huyingbao.simple.model.Product;
import com.huyingbao.simple.store.MainStore;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * 列表展示
 * Created by liujunfeng on 2017/11/9.
 */
public class ProductListFragment extends BaseRxFluxListFragment<Product> {
    @Inject
    MainStore mStore;

    public static ProductListFragment newInstance() {
        ProductListFragment fragment = new ProductListFragment();
        return fragment;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        super.afterCreate(savedInstanceState);
        initActionBar("列表展示");
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getRxAction().getType()) {
            case Actions.GET_GIT_REPO_LIST:
                showDataList(mStore.getProductList());
                break;
        }
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }

    @Override
    protected void initAdapter() {
        mAdapter = new ProductAdapter(mDataList);
    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
        mRvContent.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                mActionCreator.postLocalAction(Actions.TO_GIT_USER, ActionsKeys.USER_ID,
                        mDataList.get(position).getShopId().getShopId());
            }
        });
    }

    @Override
    protected void getDataList(int index) {
        mActionCreator.getProductList();
    }
}
