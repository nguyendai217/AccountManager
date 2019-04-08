package com.example.accountmanager.fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.accountmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAccount extends Fragment  {
    FloatingActionButton fabAddAccount;
    Button btnCancelAcc, btnAddAcc;
    EditText edtTittle, edtAcc, edtPass, edtWeb, edtEmail , edtDesp;
    Spinner spnGroup;

    public FragmentAccount() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fabAddAccount= getActivity().findViewById(R.id.fab_add_account);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View dialogview = layoutInflater.inflate(R.layout.dialog_add_account, null);
        alertDialog.setView(dialogview);
        fabAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTittle= dialogview.findViewById(R.id.edt_title);
                edtAcc= dialogview.findViewById(R.id.edt_user);
                edtEmail= dialogview.findViewById(R.id.edt_email);
                edtPass= dialogview.findViewById(R.id.edt_pass);
                edtDesp= dialogview.findViewById(R.id.edt_desp);
                edtWeb= dialogview.findViewById(R.id.edt_web);
                spnGroup= dialogview.findViewById(R.id.spn_group);

                btnAddAcc= dialogview.findViewById(R.id.btn_add_acc);
                btnCancelAcc= dialogview.findViewById(R.id.btn_cancel_acc);

                btnAddAcc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                btnCancelAcc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }
}
