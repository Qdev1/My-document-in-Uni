package com.example.myempty2application.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myempty2application.R;
import com.example.myempty2application.bean.PostInfo;
import com.example.myempty2application.fragment.placeholder.PlaceholderContent;
import com.example.myempty2application.webservice.MyApiEndpointInterface;

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
    private PostItemRecyclerViewAdapter postItemRecyclerViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_list_list, container, false);
        //Create an adapter
        postItemRecyclerViewAdapter =
                new PostItemRecyclerViewAdapter(PlaceholderContent.ITEMS);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(postItemRecyclerViewAdapter);
        }
        getPostList();
        return view;
    }

    /**
     * Get post list from web service
     */
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private void getPostList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        MyApiEndpointInterface myApiEndpointInterface = retrofit.create(MyApiEndpointInterface.class);
        myApiEndpointInterface.getPostInfoList().enqueue(new Callback<List<PostInfo>>() {
            @Override
            public void onResponse(Call<List<PostInfo>> call, Response<List<PostInfo>> response) {
                PlaceholderContent.ITEMS.clear();
                List<PostInfo> postInfoList = response.body();
                for(PostInfo postInfo : postInfoList) {
                    PlaceholderContent.PlaceholderItem placeholderItem =
                            new PlaceholderContent.PlaceholderItem(postInfo.getUserId(),
                                    postInfo.getTitle(), postInfo.getBody());
                    PlaceholderContent.ITEMS.add(placeholderItem);
                }
                postItemRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PostInfo>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get pos tlist", Toast.LENGTH_SHORT).show();
            }
        });
    }

}