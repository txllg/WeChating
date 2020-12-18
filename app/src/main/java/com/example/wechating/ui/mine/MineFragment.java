package com.example.wechating.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wechating.EmptyContentPage;
import com.example.wechating.MainActivity;
import com.example.wechating.R;

public class MineFragment extends Fragment implements View.OnClickListener{

    private MineViewModel mineViewModel;
    private LinearLayout linearLayoutStar;
    private LinearLayout linearLayoutCards;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mineViewModel =
                new ViewModelProvider(this).get(MineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        mineViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        linearLayoutStar=root.findViewById(R.id.linear_start);
        linearLayoutCards=root.findViewById(R.id.linear_cards);
        linearLayoutStar.setOnClickListener(this);
        linearLayoutCards.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(), EmptyContentPage.class);
        startActivity(intent);
    }
}