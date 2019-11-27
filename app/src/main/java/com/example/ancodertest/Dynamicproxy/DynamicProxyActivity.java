package com.example.ancodertest.Dynamicproxy;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ancodertest.R;
import java.lang.reflect.Proxy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DynamicProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_proxy);
        IVehical car = new Car();
        IVehical vehical = (IVehical)Proxy.newProxyInstance(car.getClass().getClassLoader(),Car.class.getInterfaces(),new VehicalInvacationHandler(car));
        vehical.run();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        GetRequest_Interface requestInterface = retrofit.create(GetRequest_Interface.class);
        Call<ReceptionModel> call = requestInterface.getCall("");
        call.enqueue(new Callback<ReceptionModel>() {
            @Override
            public void onResponse(Call<ReceptionModel> call, Response<ReceptionModel> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<ReceptionModel> call, Throwable t) {

            }
        });
    }
}
