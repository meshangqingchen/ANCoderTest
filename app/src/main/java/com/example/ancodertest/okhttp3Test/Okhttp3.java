package com.example.ancodertest.okhttp3Test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ancodertest.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;

import static okhttp3.internal.Util.UTF_8;

public class Okhttp3 extends AppCompatActivity {
    String postApi = "https://api.apiopen.top/likePoetry";
    private static final String LOG_TAG = "RetrofitService";

    private static OkHttpClient httpClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp3);
        postNet();
    }

    void getNet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://api.apiopen.top/getSingleJoke")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    Log.i("response", response.body().string());
                    Log.i("aa", "aaaaa");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void asynGetNet(){

    }

    void postNet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 创建请求body，MediaType请求包体类型

                OkHttpClient client = new OkHttpClient();

                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request request = chain.request();
                        final Response response = chain.proceed(request);

                        StringBuilder sb = new StringBuilder();
                        sb.append("\n Request: ");
                        sb.append("\n URL: ").append(request.url().toString());
                        sb.append("\n Headers: ").append(response.headers().toString());
                        try {
                            if (request.body() == null) {
                                sb.append("\n Query: ").append(request.url().query());
                            } else {
                                Buffer buffer = new Buffer();
                                request.body().writeTo(buffer);
                                sb.append("\n RequestBody: ").append(readString(buffer, request.body().contentType()));
                            }

                            if (response.body() != null) {
                                BufferedSource source = response.body().source();
                                source.request(Long.MAX_VALUE);
                                Buffer buffer = source.buffer().clone();
                                sb.append("\n ResponseBody: ").append(readString(buffer, response.body().contentType()));
                            }
                        } catch (IOException e) {
                            sb.append("\n Error: ").append(e.getMessage());
                        } finally {
                            Log.i(LOG_TAG, sb.toString());
                        }

                        return response;
                    }
                });

                client = builder.build();

                Map<String, Object> map = new HashMap<>();
                map.put("name","李白");
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),new Gson().toJson(map));
                Request request = new Request.Builder()
                        .url(postApi)
                        .post(requestBody)
                        .addHeader("name", "value")
                        .tag("postSync")
                        .build();

                try {
                    Response response = client.newCall(request).execute();

//                    Log.i("request", request.body().writeTo();
                    Log.i("request.header",request.headers().toString());
                    Log.i("request.url",request.url().url().toString());


                    Log.i("response", response.body().string());
                    Log.i("response.header",response.headers().toString());
                    Log.i("response.url",response.request().url().url().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String readString(Buffer buffer, MediaType contentType) throws IOException {
        Charset charset = UTF_8;
        if (contentType != null) {
            charset = contentType.charset(charset);
        }

        charset = Util.bomAwareCharset(buffer, charset);
        return buffer.readString(charset);
    }

    void asynPostNet(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/html; charset=utf-8"),"content");
        FormBody formBody = new FormBody.Builder()
                .add("name", "李白")
                .build();

        RequestBody requestBody1 = new MultipartBody.Builder()
                .addPart(requestBody)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"params\""),
                        formBody)
                .build();

        Request request = new Request.Builder()
                .url(postApi)
                .post(requestBody1)
                .addHeader("name", "value")
                .tag("postSync")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("url",call.request().url().url().toString());
                Log.i("header",call.request().headers().toString());
            }
        });
    }
}

