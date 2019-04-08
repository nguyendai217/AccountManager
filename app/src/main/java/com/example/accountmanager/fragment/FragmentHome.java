package com.example.accountmanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.accountmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {
    ImageButton imbAccount, imbGroup, imbLogout, imbInfo;


    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        controls();
    }

    private void init() {
        imbAccount = getActivity().findViewById(R.id.imb_account);
        imbGroup = getActivity().findViewById(R.id.imb_group);
        imbInfo = getActivity().findViewById(R.id.imb_info);
        imbLogout = getActivity().findViewById(R.id.imb_logout);

    }

    private void controls() {
        imbAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentAccount fragmentAccount = new FragmentAccount();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, fragmentAccount, fragmentAccount.getTag()).commit();
            }
        });
        imbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentCategory fragmentCategory = new FragmentCategory();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, fragmentCategory, fragmentCategory.getTag()).commit();
            }
        });
    }


}
