package com.example.wanglei.findjob.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wanglei.findjob.R;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY_ID = "key_id";
    public static final String TITLE = "title";
    public static final String TYPE = "type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
