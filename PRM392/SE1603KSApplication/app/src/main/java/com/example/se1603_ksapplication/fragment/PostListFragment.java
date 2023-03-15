package com.example.se1603_ksapplication.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.se1603_ksapplication.R;
import com.example.se1603_ksapplication.bean.PostInfo;
import com.example.se1603_ksapplication.fragment.placeholder.PlaceholderContent;
import com.example.se1603_ksapplication.webservice.MyApiEndpointInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of Items.
 */
public class PostListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PostListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PostListFragment newInstance(int columnCount) {
        PostListFragment fragment = new PostListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_list_list, container, false);
        getPostList(); // Call webservice to get post list
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS);
            recyclerView.setAdapter(myItemRecyclerViewAdapter);
        }
        return view;
    }
    private MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private void getPostList() {
        Retrofit retrofit = new Retrofit.Builder() .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) .build();
        MyApiEndpointInterface myRetrofitAPI = retrofit.create(MyApiEndpointInterface.class);
        myRetrofitAPI.getPostList().enqueue(new Callback<List<PostInfo>>() {
            @Override
            public void onResponse(Call<List<PostInfo>> call, Response<List<PostInfo>> response) {
                PlaceholderContent.ITEMS.clear();
                List<PostInfo> postInfoList = response.body();
                for(int i = 0; i < postInfoList.size(); i++) {
                    PlaceholderContent.PlaceholderItem item =
                            new PlaceholderContent.PlaceholderItem(postInfoList.get(i).getId(),
                                    postInfoList.get(i).getTitle(),
                                    postInfoList.get(i).getBody());
                    PlaceholderContent.ITEMS.add(item);
                }
                myItemRecyclerViewAdapter.notifyDataSetChanged(); // Update view after got data
            }

            @Override
            public void onFailure(Call<List<PostInfo>> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });
    }
}