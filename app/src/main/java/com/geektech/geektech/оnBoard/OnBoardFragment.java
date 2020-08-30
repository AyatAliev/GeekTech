package com.geektech.geektech.оnBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.geektech.geektech.R;


public class OnBoardFragment extends Fragment {

    public OnBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        int pos = getArguments().getInt("pos");

        TextView textViewEnd = view.findViewById(R.id.textViewEnd);
        TextView textViewTwo = view.findViewById(R.id.textViewTwo);
        ImageView imageView = view.findViewById(R.id.imageView);

        switch (pos) {
            case 0:
                imageView.setImageResource(R.drawable.logo_geek_tech);
                textViewEnd.setText("В данном приложении вы сможете легко находить Ментора! ");
                break;
            case 1:
                imageView.setImageResource(R.drawable.android);
                textViewEnd.setText("Быстро находить свою группу и просмотреть свои уроки!");
                break;
            case 2:
                imageView.setImageResource(R.drawable.kotlin);
                textViewEnd.setText("А самое главное оно направлено на легкое обучение как ученика так и для работы учителя!");
                break;
        }
        return view;
    }
}
