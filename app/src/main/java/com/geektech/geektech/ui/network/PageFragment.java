package com.geektech.geektech.ui.network;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.geektech.geektech.R;


public class PageFragment extends Fragment {

    WebView webView;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String url;
    private static String TAG = "ololo";


    public static PageFragment newInstance(String url) {
        Log.e(TAG, "newInstance: " + url);
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_SECTION_NUMBER, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            url = getArguments().getString(ARG_SECTION_NUMBER);
            Log.e(TAG, "onCreate: " + url);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.web_view);
        // подключаем клиент
        webView.setWebViewClient(new WebViewClient());
        // включаем поддержку JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        // указываем страницу загрузки
        Log.e("ololo", "onViewCreated: " + url);

        if (savedInstanceState != null)
            webView.restoreState(savedInstanceState);
        else
            webView.loadUrl(url);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        webView.saveState(outState);
        super.onSaveInstanceState(outState);
    }
}