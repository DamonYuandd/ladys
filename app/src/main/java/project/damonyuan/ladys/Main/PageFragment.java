package project.damonyuan.ladys.Main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import project.damonyuan.ladys.Config;

import project.damonyuan.ladys.R;

import static project.damonyuan.ladys.Config.API.API_MAIN;

/**
 * Created by Damon on 2016/6/14.
 */
public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private Context context;
    private CoordinatorLayout rootlayout ;
    private TextView textView;
    private LinearLayout linearlayout_id;
    private ViewGroup container_fixed;

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
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        container_fixed = container;
        RequestQueue mQueue = Volley.newRequestQueue(container.getContext());
        String url = API_MAIN;
        switch (mPage) {
            case 1:
                url = url + "search/query/listview/category/all/count/10/page/1";
                break;
            case 2:
                url = url + "search/query/listview/category/Android/count/10/page/1";
                break;
            case 3:
                url = url + "search/query/listview/category/iOS/count/10/page/1";
                break;
            case 4:
                url = url + "search/query/listview/category/休息视频/count/10/page/1";
                break;
            case 5:
                url = url + "search/query/listview/category/福利/count/10/page/1";
                break;
            case 6:
                url = url + "search/query/listview/category/拓展资源/count/10/page/1";
                break;
            case 7:
                url = url + "search/query/listview/category/前端/count/10/page/1";
                break;
            case 8:
                url = url + "search/query/listview/category/瞎推荐/count/10/page/1";
                break;
            case 9:
                url = url + "search/query/listview/category/App/count/10/page/1";
                break;
            default:
                url = url + "search/query/listview/category/all/count/10/page/1";
        }

        textView = (TextView) view.findViewById(R.id.f_tv);
        //textView = (TextView) view;
        textView.setText("loading.....");
        linearlayout_id = (LinearLayout) view.findViewById(R.id.fragment_id);
       // rootlayout = (CoordinatorLayout) view.findViewById(R.id.container);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //textView.setText("");
                try {
                   // JSONObject resultsStr = response.getJSONObject("results");
                    JSONArray resultsArr = response.getJSONArray("results");
                    for (int i = 0; i<resultsArr.length();i++){
                        JSONObject jsonObject2 = (JSONObject)resultsArr.opt(i);
                        TextView tv = new TextView(container_fixed.getContext());
                        tv.setText(jsonObject2.getString("readability"));
                        linearlayout_id.addView(tv);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //textView.setText(response.toString());
                //Log.d("TAGgg", );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(rootlayout, "error", Snackbar.LENGTH_SHORT).setDuration(Snackbar.LENGTH_LONG).show();
            }
        });
        mQueue.add(jsonObjectRequest);

        return view;
    }

}
