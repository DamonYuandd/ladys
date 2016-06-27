package project.damonyuan.ladys.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.damonyuan.ladys.Config;

import project.damonyuan.ladys.R;

import static project.damonyuan.ladys.Config.API.API_MAIN;

/**
 * Created by Damon on 2016/6/14.
 */
public class PageFragment extends Fragment implements MyPage {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private Context context;
    private CoordinatorLayout rootlayout;
    private TextView textView;
    private LinearLayout linearlayout_id;
    private ViewGroup container_fixed;
    private ListView listView;
    String str[];
    ArrayList<String> listArr = new ArrayList(), linkArr = new ArrayList();
    ListView list;
    ArrayAdapter<String> arr_adapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private int pageMum = 1;
    private View view;
    private boolean canUp = true;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_page, container, false);
        container_fixed = container;
        initData(pageMum);
        return view;
    }

    private boolean initData(int i) {
        if(canUp == false){
            return false;
        }
        canUp = false;
        int count = 30;
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        String url = API_MAIN;
        switch (mPage) {
            case 1:
                url = url + "search/query/listview/category/all/count/"+count+"/page/" + i;
                break;
            case 2:
                url = url + "search/query/listview/category/Android/count/"+count+"/page/" + i;
                break;
            case 3:
                url = url + "search/query/listview/category/iOS/count/"+count+"/page/" + i;
                break;
            case 4:
                url = url + "search/query/listview/category/休息视频/count/"+count+"/page/" + i;
                break;
            case 5:
                url = url + "search/query/listview/category/福利/count/"+count+"/page/" + i;
                break;
            case 6:
                url = url + "search/query/listview/category/拓展资源/count/"+count+"/page/" + i;
                break;
            case 7:
                url = url + "search/query/listview/category/前端/count/"+count+"/page/" + i;
                break;
            case 8:
                url = url + "search/query/listview/category/瞎推荐/count/"+count+"/page/" + i;
                break;
            case 9:
                url = url + "search/query/listview/category/App/count/"+count+"/page/" + i;
                break;
            default:
                url = url + "search/query/listview/category/all/count/"+count+"/page/" + i;
        }

        textView = (TextView) view.findViewById(R.id.f_tv);
        //textView = (TextView) view;
        //Log.d("dd",url);
        textView.setText("loading.....");
        linearlayout_id = (LinearLayout) view.findViewById(R.id.fragment_id);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        // list = (ListView) view.findViewById(R.id.list);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                textView.setText("");

                try {
                    JSONArray resultsArr = response.getJSONArray("results");
                    // str = new String[resultsArr.length()];
                    for (int i = 0; i < resultsArr.length(); i++) {
                        //添加信息条
                        JSONObject jsonObject2 = (JSONObject) resultsArr.opt(i);
                        listArr.add(String.valueOf(Html.fromHtml(jsonObject2.getString("desc"))));
                        linkArr.add(jsonObject2.getString("url"));
                    }
                    if(mPage == 5){
                       // Log.d("ttt", String.valueOf(mPage));
                        mRecyclerView.setHasFixedSize(true);
                        // use a linear layout manager
                        mLayoutManager = new LinearLayoutManager(container_fixed.getContext());
                        //设置RecycleView的显示方向：（默认为垂直） 水平
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.addItemDecoration(new RecycleViewDivider(container_fixed.getContext(), LinearLayoutManager.HORIZONTAL));
                        mAdapter = new MyImgAdapter(listArr, getMypage(), linkArr);
                        mRecyclerView.setAdapter(mAdapter);

                    }else {
                        mRecyclerView.setHasFixedSize(true);
                        // use a linear layout manager
                        mLayoutManager = new LinearLayoutManager(container_fixed.getContext());
                        //设置RecycleView的显示方向：（默认为垂直） 水平
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.addItemDecoration(new RecycleViewDivider(container_fixed.getContext(), LinearLayoutManager.HORIZONTAL));
                        mAdapter = new MyAdapter(listArr, getMypage(), linkArr);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
                            @Override
                            public void onItemClick(View view, String data) {
                                Intent intent = new Intent(getActivity(), MyWebView.class);
                                intent.putExtra("url", data);
                                startActivity(intent);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(container_fixed.getContext(), "error", Toast.LENGTH_LONG).show();
                // Snackbar.make(rootlayout, "error", Snackbar.LENGTH_SHORT).setDuration(Snackbar.LENGTH_LONG).show();
            }
        });
        canUp = true;
        mQueue.add(jsonObjectRequest);
        return false;
    }

    private MyPage getMypage(){
        return this;
    }
    @Override
    public void fragmentLast(int i) {
        pageMum++;
        initData(pageMum);
       // Log.d("ttt", String.valueOf(i));
    }
}