package com.zhy.http.okhttp.callback;

import android.util.Log;

import com.zhy.http.okhttp.utils.DESUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback<String> {
	@Override
	public String parseNetworkResponse(Response response, int id) throws IOException {
		try {
			String responseStr = response.body().string();
			JSONObject jsonObject = new JSONObject(responseStr);
			String data = jsonObject.getString("data");
			jsonObject.remove("data");
			String des =  DESUtils.decode(data);
			if (des.startsWith("[")){
				JSONArray dataObject = new JSONArray(des);
				jsonObject.put("data", dataObject);
			}else {
				JSONObject dataObject = new JSONObject(des);
				jsonObject.put("data", dataObject);
			}
			String jsonStr = jsonObject.toString();
			return jsonStr.replace("\\","");
		} catch (Exception e) {
			Log.e("XXX","解密失败");
			e.printStackTrace();
			return "";
		}
	}

}
