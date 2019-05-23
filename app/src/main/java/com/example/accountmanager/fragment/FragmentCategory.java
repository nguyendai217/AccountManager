package com.example.accountmanager.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.accountmanager.R;
import com.example.accountmanager.adapter.CategoryAdapter;
import com.example.accountmanager.database.DataBaseHelper;
import com.example.accountmanager.model.Category;

import java.util.ArrayList;


public class FragmentCategory extends Fragment {

    FloatingActionButton fabAdd;
    Button btnCancel, btnAdd;
    RecyclerView recyclerView;
    EditText edtCategory;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> arrCategory = new ArrayList<>();

    public FragmentCategory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_group,container,false);

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fabAdd = getActivity().findViewById(R.id.fab_add_group);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        final View dialogview = layoutInflater.inflate(R.layout.dialog_add_group, null);
        alertDialog.setView(dialogview);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCategory = dialogview.findViewById(R.id.edt_category);
                btnCancel = dialogview.findViewById(R.id.btn_cancel);
                btnAdd = dialogview.findViewById(R.id.btn_add);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edtCategory.length() == 0) {
                            Toast.makeText(getActivity(), "Danh mục không được để trống !", Toast.LENGTH_SHORT).show();
                        } else {
                            if (edtCategory.length() > 0) {
                                DataBaseHelper db = new DataBaseHelper(getActivity());
                                Category category = new Category();
                                category.setCategoryname(edtCategory.getText().toString());
                                db.insertCategory(category);
                                arrCategory.add(category);
                                Toast.makeText(getActivity(), "Thêm danh mục thành công !", Toast.LENGTH_SHORT).show();
                                categoryAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getActivity(), "Thêm thất bại !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        dataBaseHelper.getReadableDatabase();
        arrCategory = dataBaseHelper.getAllCategoryName();

//        Category category= new Category("Hoa Hồng");
//        arrCategory.add(category);
//        Category category1= new Category("Hoa Mai");
//        arrCategory.add(category1);
        recyclerView = getActivity().findViewById(R.id.recyclerview);
        categoryAdapter = new CategoryAdapter(arrCategory);
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

//    public  Category getData() {
//        Cursor cur;
//        Category cat;
//        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
//        dataBaseHelper.getReadableDatabase();
//
//        cur = (Cursor) dataBaseHelper.getAllCategory();
//        if (cur.moveToFirst()) {
//            do {
//                cat = new Category();
//                cat.setCategoryname(cur.getString(1));
//                arrCategory.add(cat);
//            }
//            while (cur.moveToNext());
//        }
//        return  ;
//    }
}
