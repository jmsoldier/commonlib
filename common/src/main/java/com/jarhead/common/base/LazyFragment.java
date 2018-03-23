package com.jarhead.common.base;

import android.os.Bundle;

/**
 * Created by jiming1 on 2017/7/21.
 */
public abstract class LazyFragment extends BaseFragment {

	protected boolean isViewInitiated;
	protected boolean isVisibleToUser;
	protected boolean isDataInitiated;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		isViewInitiated = true;
		prepareFetchData();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		this.isVisibleToUser = isVisibleToUser;
		prepareFetchData();
	}

	public boolean prepareFetchData() {
		return prepareFetchData(false);
	}

	public boolean prepareFetchData(boolean forceUpdate) {
		if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
			fetchData();
			isDataInitiated = true;
			return true;
		}
		return false;
	}

	@Override
	protected void initData() {

	}

	@Override
	public void initPresenter() {

	}

	public abstract void fetchData();
}