package com.example.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.base.activity.IBasePagingView;
import com.example.base.loadsir.EmptyCallback;
import com.example.base.loadsir.ErrorCallback;
import com.example.base.loadsir.LoadingCallback;
import com.example.base.utils.LogUtils;
import com.example.base.utils.ToastUtil;
import com.example.base.viewmodel.IMvvmBaseViewModel;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

/**
 * author:lgh on 2020/6/12 10:20
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends IMvvmBaseViewModel>
        extends Fragment implements IBasePagingView {

    protected V           viewDataBingding;
    protected VM          viewModel;
    protected LoadService mLoadService;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        LogUtils.i(this, "onattach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParameters();
        LogUtils.i(this, "oncreate");
    }

    protected void initParameters() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBingding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        LogUtils.i(this, "oncreateview");
        return viewDataBingding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = getViewModel();
        if (viewModel != null) {
            viewModel.attachUi(this);
        }
        if (getBindingVariable() > 0) {
            viewDataBingding.setVariable(getBindingVariable(), viewModel);
            viewDataBingding.executePendingBindings();
        }
        LogUtils.i(this, "onviewcreate");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.i(this, "onactivitycreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i(this, "onstart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i(this, " : onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i(this, " : onPause");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.i(this, " : onDestroyView");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != viewModel && viewModel.isUiAttach()) {
            viewModel.detachUi();
        }
        LogUtils.i(this, " : onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (null != viewModel && viewModel.isUiAttach()) {
            viewModel.detachUi();
        }
        LogUtils.i(this, " : onDetach");

    }

    protected abstract int getBindingVariable();

    protected abstract VM getViewModel();

    @LayoutRes
    protected abstract int getLayoutId();

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
                ToastUtil.show(getContext(), message);
            }
        }
    }

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

}
