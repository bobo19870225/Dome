package com.yy.dome.ui.activity.school;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.yy.dome.R;
import com.yy.dome.ui.activity.home.FindSchool;

public class DetailInfoFragment  extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM = "param";

    // TODO: Rename and change types of parameters

    private int mParam;//用来表示当前需要展示的是哪一页
    private TextView detail_text;//展示的具体内容，这里为了简单只用一个TextView意思一下

    public DetailInfoFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static DetailInfoFragment newInstance(int param) {
        DetailInfoFragment fragment = new DetailInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getInt(ARG_PARAM);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment, container, false);
        //detail_text= (TextView) view.findViewById(R.id.detail_info);


        //根据mParam来判断当前展示的是哪一页，根据页数的不同展示不同的信息
        switch (mParam){
            case 0:
/*                Intent intent=new Intent(getContext(), FindSchool.class);
                startActivity(intent);*/
                detail_text.setText("内容1");
                break;
            case 1:
                detail_text.setText("内容2");
                break;
            case 2:
                detail_text.setText("内容3");
                break;
            case 3:
                detail_text.setText("内容4");
                break;
            default:break;

        }

        return view;
    }


}
