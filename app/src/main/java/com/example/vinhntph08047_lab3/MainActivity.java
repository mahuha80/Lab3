package com.example.vinhntph08047_lab3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RvAdapter.OnItemClickListener {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Disposable disposable;
    RecyclerView recyclerView;
    EditText editText;
    RvAdapter rvAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvMain);
        editText = findViewById(R.id.edSearch);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                disposable = NetModule.getAPIService().getRootModel(charSequence.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponse, this::handleError);
            }

            private void handleResponse(List<RootModel> rootModels) {
                rvAdapter.updateData(rootModels);
            }

            private void handleError(Throwable throwable) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        disposable = NetModule.getAPIService().getRootModel("android")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError);
        compositeDisposable.add(disposable);
    }

    private void handleResponse(List<RootModel> rootModel) {
        rvAdapter = new RvAdapter(this, rootModel, this::onItemClick);
        recyclerView.setAdapter(rvAdapter);
    }

    private void handleError(Throwable throwable) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void onItemClick(RootModel rootModel) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("id",String.valueOf(rootModel.getId()));
        startActivity(intent);
    }

}