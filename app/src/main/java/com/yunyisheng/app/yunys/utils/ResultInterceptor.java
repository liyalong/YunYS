package com.yunyisheng.app.yunys.utils;

import com.google.gson.Gson;
import com.yunyisheng.app.yunys.base.BaseModel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by liyalong on 2018/1/3.
 */

public class ResultInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return checkResponse(response);
    }

    private Response checkResponse(Response response) {
        try{
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            ResponseBody body = clone.body();
            if (body != null) {
                MediaType mediaType = body.contentType();
                if (mediaType != null) {
                    String json = body.string();
                    BaseModel baseModel = new BaseModel();
                    Gson gson = new Gson();
                    baseModel = gson.fromJson(json,BaseModel.class);
                    Integer status = baseModel.getRespCode();
                    if(status != null && status.equals(501)){
                       //TODO 跳转登录页


                    }
                    String resp = body.string();
                    body = ResponseBody.create(mediaType, resp);
                    return response.newBuilder().body(body).build();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
