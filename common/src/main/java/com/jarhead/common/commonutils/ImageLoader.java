package com.jarhead.common.commonutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jarhead.common.R;


/**
 * Created by jiming1 on 2016/11/7.
 */
public class ImageLoader {

	public static void loadImage(Context context, String imageUrl, ImageView imageView) {

		Glide.with(context)
				.load(imageUrl)//图片地址
//                .asGif()//asGif加载Gif动态图，asBitmap可以将Gif或者视频（没试过）解码成bitmap
//                .placeholder(R.mipmap.ic_launcher)//加载中显示的图片
				.crossFade()//淡入效果
				.into(imageView);
	}

	public static void loadImage(Context context, int res, ImageView imageView) {

		Glide.with(context)
				.load(res)//图片地址
//                .asGif()//asGif加载Gif动态图，asBitmap可以将Gif或者视频（没试过）解码成bitmap
//                .placeholder(R.mipmap.ic_launcher)//加载中显示的图片
				.crossFade()//淡入效果
				.into(imageView);
	}

	public static void loadRound(final Context context, String url, final ImageView iv) {
		Glide.with(context)//
				.load(url)//
				.asBitmap()//
				.centerCrop()//
				.into(new BitmapImageViewTarget(iv) {
					@Override
					protected void setResource(Bitmap resource) {
						RoundedBitmapDrawable circularBitmapDrawable =
								RoundedBitmapDrawableFactory.create(context.getResources(), resource);
						circularBitmapDrawable.setCircular(true);
						iv.setImageDrawable(circularBitmapDrawable);
					}
				});
	}

}
