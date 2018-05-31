package com.example.englishplay.bookrecycle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.englishplay.bookrecycle.R;
import com.example.englishplay.bookrecycle.SearchActivity;

/**
 * Created by 解奕鹏 on 2018/5/17.
 */

public class FragmentMainFirst extends Fragment {

    SearchView searchView;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_first, container, false);
        return view;
    }
}
