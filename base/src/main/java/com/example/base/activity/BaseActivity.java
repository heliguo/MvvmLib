package com.example.base.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.base.loadsir.EmptyCallback;
import com.example.base.loadsir.ErrorCallback;
import com.example.base.loadsir.LoadingCallback;
import com.example.base.utils.ToastUtil;
import com.example.base.viewmodel.IMvvmBaseViewModel;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

/**
 * author:lgh on 2020/6/12 10:20
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends IMvvmBaseViewModel>
        extends AppCompatActivity implements IBasePagingView {

    protected V           viewDataBingding;
    protected VM          viewModel;
    protected LoadService mLoadService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        PerformDataBingding();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null && viewModel.isUiAttach()) {
            viewModel.detachUi();
        }
    }

    private void initViewModel() {
        viewModel = getViewModel();
        if (viewModel != null) {
            viewModel.attachUi(this);
        }

    }

    private void PerformDataBingding() {
        viewDataBingding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = viewModel == null ? getViewModel() : viewModel;
        if (getBindingVariable() > 0) {
            viewDataBingding.setVariable(getBindingVariable(), viewModel);
            viewDataBingding.executePendingBindings();
        }
    }

    /**
     * 设置viewmodel
     */
    protected abstract VM getViewModel();

    /**
     * 布局ID
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 获取参数Variable
     */
    protected abstract int getBindingVariable();

    /**
     * 注册LoadSer，设置重新加载
     *
     * @param view 需要替换的Load视图
     */
    public void setLoadSer(View view) {
        if (mLoadService == null) {
            mLoadService = LoadSir.getDefault().register(view,
                    (Callback.OnReloadListener) v -> onRetry());
        }
    }

    /**
     * 重新加载
     */
    protected abstract void onRetry();


    private boolean isContentShow = false;//是否正常加载


    @Override
    public void showContent() {
        if (mLoadService != null) {
            isContentShow = true;
            mLoadService.showSuccess();
        }
    }

    @Override
    public void showLoading() {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void showEmpty() {
        if (null != mLoadService) {
            mLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void showFailure(String message) {
        if (null != mLoadService) {
            if (!isContentShow) {
                mLoadService.showCallback(ErrorCallback.class);
            } else {
                ToastUtil.show(this, message);
            }
        }
    }

}
