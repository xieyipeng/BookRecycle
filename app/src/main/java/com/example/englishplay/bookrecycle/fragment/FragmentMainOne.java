package com.example.englishplay.bookrecycle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.englishplay.bookrecycle.R;

/**
 * Created by 解奕鹏 on 2018/5/16.
 */

public class FragmentMainOne extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.fragment_main_one, container, false);
        return messageLayout;
    }
}
