package com.example.vinhntph08047_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {
    Disposable disposable;
    String id;
    private TextView tvID;
    private TextView tvModified;
    private TextView tvTitle;
    private TextView tvContent;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvID = (TextView) findViewById(R.id.tvID);
        tvModified = (TextView) findViewById(R.id.tvModified);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvContent = (TextView) findViewById(R.id.tvContent);
        getAgrument();
        getDataFromServer();
    }

    private void getDataFromServer() {
        disposable = NetModule.getAPIService()
                .getDetalItem(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(this::OnSuccess, this::OnFailure);
        compositeDisposable.add(disposable);

    }

    private void OnSuccess(DetailClass detailClasses) {
        tvID.setText(String.valueOf(detailClasses.getId()));
        tvContent.setText(detailClasses.getContent().rendered.toString());
        tvModified.setText(detailClasses.getModified().toString());
        tvTitle.setText(detailClasses.getTitle().toString());

    }

    private void OnFailure(Throwable throwable) {
    }

    private void getAgrument() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        this.id = id;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}