package com.geektech.geektech.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.geektech.R;
import com.geektech.geektech.ui.home.recyclerview.Adapter;
import com.geektech.geektech.ui.model.Group;

public class HomeFragment extends Fragment implements OnClickHolder {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.fh_recycler_view);
        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        return root;
    }

    @Override
    public void click(int s, Group group) {
        navController.navigate(R.id.lessonFragmnet);
    }

}
