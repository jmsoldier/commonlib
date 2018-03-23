package com.jarhead.common.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jarhead.common.commonutils.TUtil;
import com.jarhead.common.commonwidget.LoadingDialog;

import butterknife.ButterKnife;

/**
 * Created by jiming1 on 2016/10/31.
 */
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {

	protected View rootView;
	public T mPresenter;
	public E mModel;
	ProgressDialog progressDialog;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (rootView == null) {
			rootView = inflater.inflate(getLayoutResource(), container, false);
			mPresenter = TUtil.getT(this, 0);
			mModel = TUtil.getT(this, 1);
			if (mPresenter != null) {
				mPresenter.mContext = this.getActivity();
			}
			ButterKnife.bind(this, rootView);
			initPresenter();
			initView();
			initData();
		} else {
			ButterKnife.bind(this, rootView);
		}

		return rootView;
	}

	//获取布局文件
	protected abstract int getLayoutResource();

	//简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
	public abstract void initPresenter();

	//初始化view
	protected abstract void initView();

	//初始化data
	protected abstract void initData();

	/**
	 * 通过Class跳转界面
	 **/
	public void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/**
	 * 通过Class跳转界面
	 **/
	public void startActivityForResult(Class<?> cls, int requestCode) {
		startActivityForResult(cls, null, requestCode);
	}

	/**
	 * 含有Bundle通过Class跳转界面
	 **/
	public void startActivityForResult(Class<?> cls, Bundle bundle,
									   int requestCode) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, requestCode);
	}

	/**
	 * 含有Bundle通过Class跳转界面
	 **/
	public void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}


	/**
	 * 开启加载进度条
	 */
	public void showLoadingDialog() {
		startProgressDialog("");
	}

	/**
	 * 开启加载进度条
	 *
	 * @param msg
	 */
	public void startProgressDialog(String msg) {
		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage(msg);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	/**
	 * 停止加载进度条
	 */
	public void dismissLoadingDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.unbind(this);
		if (rootView != null) {
			((ViewGroup) rootView.getParent()).removeView(rootView);
		}
	}
}
