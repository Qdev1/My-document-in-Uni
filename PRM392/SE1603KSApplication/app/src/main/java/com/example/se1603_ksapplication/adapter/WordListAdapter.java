package com.example.se1603_ksapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1603_ksapplication.R;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordListViewHolder> {
    private Context context;
    private List<String> wordList;
    public WordListAdapter(Context context, List<String> wordList) {
        this.context = context;
        this.wordList = wordList;
    }
    @NonNull
    @Override
    public WordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Load layout for one word item
        View viewItem = LayoutInflater.from(context).inflate(R.layout.word_list_item, parent, false);
        return new WordListViewHolder(viewItem, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListViewHolder holder, int position) {
        //Get data from wordList
        String word = wordList.get(position);
        //Set data to one item
        holder.textViewWord.setText(word);
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    protected class WordListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewWord;
        private WordListAdapter wordListAdapter;
        public WordListViewHolder(@NonNull View itemView, WordListAdapter adapter) {
            super(itemView);
            this.textViewWord = itemView.findViewById(R.id.tv_word_item);
            this.wordListAdapter = adapter;
            textViewWord.setOnClickListener(this);
        }
        public WordListViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            textViewWord.setText("Clicked! " +  textViewWord.getText());
        }
    }
}
