package com.jarhead.common.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.gyf.barlibrary.ImmersionBar;
import com.jarhead.common.commonutils.TUtil;
import com.jarhead.common.commonwidget.LoadingDialog;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import flyn.Eyes;

/**
 * Created by jiming1 on 2016/10/31.
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {


	public T mPresenter;
	public E mModel;
	public Context mContext;
	public ImmersionBar mImmersionBar;

	ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		doBeforeSetcontentView();
		setContentView(getLayoutId());
		ButterKnife.bind(this);
		mContext = this;
		mPresenter = TUtil.getT(this, 0);
		mModel = TUtil.getT(this, 1);
		if (mPresenter != null) {
			mPresenter.mContext = this;
		}
		this.initPresenter();
		this.initToobar();
		this.initView();
		this.initData();
	}

	protected abstract void initToobar();

	/**
	 * 设置layout前配置
	 */
	private void doBeforeSetcontentView() {
		//设置昼夜主题
		// 无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 默认着色状态栏
		mImmersionBar = ImmersionBar.with(this);
		mImmersionBar.init();
	}


	/**
	 * 开启加载进度条
	 */
	public void showLoadingDialog() {
		showLoadingDialog("");
	}

	/**
	 * 开启加载进度条
	 *
	 * @param msg
	 */
	public void showLoadingDialog(String msg) {
		progressDialog = new ProgressDialog(this);
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
	public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
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
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}


	/*********************
	 * 子类实现
	 *****************************/
	//获取布局文件
	public abstract int getLayoutId();

	//简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
	public abstract void initPresenter();

	//初始化view
	public abstract void initView();

	//初始化data
	public abstract void initData();

	@Override
	protected void onResume() {
		super.onResume();
		//debug版本不统计crash
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		//debug版本不统计crash
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
	}
}
