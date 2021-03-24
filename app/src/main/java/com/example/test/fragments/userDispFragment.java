package com.example.test.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.DisplayedUserInfo;
import com.example.test.R;
import com.example.test.adapter.CustomAdapter;
import com.example.test.models.Result;
import com.example.test.models.UsersResponse;
import com.squareup.picasso.Picasso;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userDispFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userDispFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private UsersResponse user;
    private String mParam2;

    public userDispFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userDispFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static userDispFragment newInstance(UsersResponse param1, String param2) {
        userDispFragment fragment = new userDispFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (UsersResponse) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        CustomAdapter adapter = new CustomAdapter(user);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_disp, container, false);
    }
}