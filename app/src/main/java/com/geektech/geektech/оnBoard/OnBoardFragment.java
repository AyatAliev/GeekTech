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
                textViewEnd.setText(R.string.test);
                textViewTwo.setText(R.string.test);
                break;
            case 1:
                imageView.setImageResource(R.drawable.logo_geek_tech);
                textViewEnd.setText(R.string.test);
                textViewTwo.setText(R.string.test);
                break;
            case 2:
                imageView.setImageResource(R.drawable.logo_geek_tech);
                textViewEnd.setText(R.string.test);
                textViewTwo.setText(R.string.test);
                break;

            case 3:
                imageView.setImageResource(R.drawable.logo_geek_tech);
                textViewEnd.setText(R.string.test);
                textViewTwo.setText(R.string.test);
                break;

            case 4:
                imageView.setImageResource(R.drawable.logo_geek_tech);
                textViewEnd.setText(R.string.test);
                textViewTwo.setText(R.string.test);
                break;

            case 5:
                imageView.setImageResource(R.drawable.logo_geek_tech);
                textViewEnd.setText(R.string.test);
                textViewTwo.setText(R.string.test);
                break;
        }
        return view;
    }
}
