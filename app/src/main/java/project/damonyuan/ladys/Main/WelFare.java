package project.damonyuan.ladys.Main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import project.damonyuan.ladys.Commom;
import project.damonyuan.ladys.R;

/**
 * Created by damonyuan on 2016/6/24.
 */

public class WelFare extends Commom {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welfare);
        Intent intent=this.getIntent();
        Log.d("url",intent.getStringExtra("url"));
        //添加返回按鈕
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
