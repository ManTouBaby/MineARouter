package com.hrw.common.net;

import com.google.gson.GsonBuilder;
import com.hrw.common.utils.MtLog;

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
public class MtRetrofitHelper {
    private static OkHttpClient mClient;
    private static Retrofit mRetrofit;

    private static MtRetrofitHelper mRetrofitHelper;
    private static String mBaseUrl;

    public static MtRetrofitHelper init(String baseUrl) {
        mBaseUrl = baseUrl;
        synchronized (Object.class) {
            if (mRetrofitHelper == null) {
                mRetrofitHelper = new MtRetrofitHelper(null);
            }
        }
        return mRetrofitHelper;
    }

    public static MtRetrofitHelper init(Interceptor interceptor, String baseUrl) {
        mBaseUrl = baseUrl;
        synchronized (Object.class) {
            if (mRetrofitHelper == null) {
                mRetrofitHelper = new MtRetrofitHelper(interceptor);
            }
        }
        return mRetrofitHelper;
    }

    public static MtRetrofitHelper getRetrofit() {
        if (mRetrofitHelper == null) new Throwable("必须对RetrofitHelper进行初始化");
        return mRetrofitHelper;
    }


    Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            okhttp3.Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            MtLog.d("----------Request Start----------------");
            MtLog.d("| Response:" + content);
            MtLog.d("----------Request End:" + duration + "毫秒----------");
//            System.out.println("Request Url:" + request.toString() + "\nResult:" + content);
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    };

    private MtRetrofitHelper(Interceptor interceptor) {
        if (interceptor == null) interceptor = mInterceptor;
        if (mClient == null) mClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        if (mRetrofit == null) mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava
                .client(mClient)
                .build();
    }

    public <T> T createClass(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

}
