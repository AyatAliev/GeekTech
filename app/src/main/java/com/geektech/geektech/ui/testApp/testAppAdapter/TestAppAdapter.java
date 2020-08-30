package com.geektech.geektech.ui.testApp.testAppAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.geektech.R;
import com.geektech.geektech.presenter.Toaster;
import com.geektech.geektech.ui.model.TestAppModels;

import java.util.ArrayList;

public class TestAppAdapter extends RecyclerView.Adapter<TestAppAdapter.TestAppHolder> {
    private ArrayList<TestAppModels> card;

    public TestAppAdapter(ArrayList<TestAppModels> card) {
        this.card = card;

        TestAppModels itemApp = new TestAppModels(R.drawable.logo_play, "Print Bishkek", "Мобильное приложение интернет-магазина «Print Bishkek»");
        card.add(itemApp);
    }

    @NonNull
    @Override
    public TestAppHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_app_item, parent, false);
        return new TestAppHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAppHolder holder, int position) {
        holder.onBind(card.get(position));

    }

    @Override
    public int getItemCount() {
        return card.size();
    }

    public class TestAppHolder extends RecyclerView.ViewHolder {

        private ImageView imageApp;
        private TextView textName;
        private TextView textDesc;

        public TestAppHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toaster.show("установлено)");
                }
            });
            imageApp = itemView.findViewById(R.id.image_cardApp);
            textName = itemView.findViewById(R.id.text_name_app);
            textDesc = itemView.findViewById(R.id.text_desc_app);
        }

        public void onBind(TestAppModels testAppModels) {
            imageApp.setImageResource(testAppModels.getImageApp());
            textName.setText(testAppModels.getTextNameApp());
            textDesc.setText(testAppModels.getTextDescApp());
        }
    }
}
