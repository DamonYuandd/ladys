package project.damonyuan.ladys.Main;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import project.damonyuan.ladys.Commom;
import project.damonyuan.ladys.R;

/**
 * Created by damonyuan on 2016/6/24.
 */

public class MyWebView extends Commom {
    private WebView webView;
    private long exitTime = 0;
    private String actionBarTitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        webView = (WebView)findViewById(R.id.web_view) ;



        //获取数据
       // Bundle bundle=this.getIntent().getExtras();
        Intent intent=this.getIntent();

        webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //actionBarTitle = title;
                getSupportActionBar().setTitle(title);
            }
        };

        // Log.d("ttt",bundle.get("url"));
        // 设置setWebChromeClient对象
        webView.setWebChromeClient(wvcc);
        webView.getSettings().setJavaScriptEnabled(true);  //设置WebView属性,运行执行js脚本
        webView.loadUrl(intent.getStringExtra("url"));          //调用loadView方法为WebView加入链接
        setContentView(webView);                           //调用Activity提供的setContentView将webView显示出来

        //添加返回按鈕
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
