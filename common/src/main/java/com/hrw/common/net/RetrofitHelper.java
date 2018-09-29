package com.hrw.common.net;

import com.google.gson.GsonBuilder;
import com.hrw.common.utils.L;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 14:29
 * @desc:
 */
public class RetrofitHelper {
    private final OkHttpClient mClient;
    private final Retrofit mRetrofit;

    static RetrofitHelper retrofitHelper;

    public static RetrofitHelper instance() {
        synchronized (Object.class) {
            if (retrofitHelper == null) {
                retrofitHelper = new RetrofitHelper();
            }
        }
        return retrofitHelper;
    }

    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            System.out.println("Request Url:" + request.toString());
            long startTime = System.currentTimeMillis();
            okhttp3.Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            L.d("----------Request Start----------------");
            L.d("| " + request.toString());
            L.d("| Response:" + content);
            L.d("----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    };

    private RetrofitHelper() {
        mClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava
                .client(mClient)
                .build();


    }

    public <T> T createClass(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

}
