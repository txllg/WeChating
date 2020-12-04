package com.example.wechating.ui.friends;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wechating.R;
import com.example.wechating.component.SideBar;
import com.example.wechating.component.SortAdapter;
import com.example.wechating.domain.Friends;

import java.util.ArrayList;
import java.util.Collections;

public class FriendsFragment extends Fragment {

    private FriendsViewModel friendsViewModel;
    private ListView listView;
    private SideBar sideBar;
    private ArrayList<Friends> list;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        friendsViewModel =
                new ViewModelProvider(this).get(FriendsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_friends, container, false);
        friendsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        listView = (ListView) root.findViewById(R.id.listView);
        sideBar = (SideBar) root.findViewById(R.id.side_bar);
        initView();
        initData();
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity().getApplicationContext();
    }

        private void initView() {

        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < list.size(); i++) {
                    if (selectStr.equalsIgnoreCase(list.get(i).getFirstLetter())) {
                        listView.setSelection(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Friends("阿州")); // 亳[bó]属于不常见的二级汉字
        list.add(new Friends("大娃"));
        list.add(new Friends("二娃"));
        list.add(new Friends("三娃"));
        list.add(new Friends("四娃"));
        list.add(new Friends("五娃"));
        list.add(new Friends("六娃"));
        list.add(new Friends("七娃"));
        list.add(new Friends("喜羊羊"));
        list.add(new Friends("美羊羊"));
        list.add(new Friends("懒羊羊"));
        Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        SortAdapter adapter = new SortAdapter(getActivity().getApplicationContext(), list);
        listView.setAdapter(adapter);

    }
}