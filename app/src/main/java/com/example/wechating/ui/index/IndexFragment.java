package com.example.wechating.ui.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wechating.R;

public class IndexFragment extends Fragment {

    private IndexViewModel indexViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        indexViewModel =
                new ViewModelProvider(this).get(IndexViewModel.class);
        View root = inflater.inflate(R.layout.fragment_index, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        indexViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}