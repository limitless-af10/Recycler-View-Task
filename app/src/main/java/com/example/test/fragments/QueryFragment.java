package com.example.test.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.test.DisplayedUserInfo;
import com.example.test.R;
import com.example.test.models.UsersResponse;
import com.example.test.networking.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QueryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    UsersResponse cache;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QueryFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QueryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QueryFragment newInstance(String param1, String param2) {
        QueryFragment fragment = new QueryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

       retro();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Button button =  view.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                // Replace whatever is in the fragment_container view with this fragment
                transaction.add(R.id.fragment_container_view,userDispFragment.newInstance(cache,""), "userDisp");
                // Commit the transaction
                transaction.hide((QueryFragment) fragmentManager.findFragmentByTag("query"));
                transaction.addToBackStack("sec");
                transaction.commit();
            }
        });

        Button button4 = view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                button.setEnabled(false);
                button4.setEnabled(false);
                TextView text = view.findViewById(R.id.textView8);
                text.setText("Fetching User");
                text.setTextColor(Color.parseColor("#AC0B0B"));

                ProgressBar load = view.findViewById(R.id.load);
                load.setVisibility(View.VISIBLE);

                retro();
            }
        });
    }

    public void retro (){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<UsersResponse> call = service.getSeedUsers(100,"picture,name,email,phone");

        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                Log.v("", response.body().toString());

                String image=response.body().getResults().get(0).getPicture().getLarge();
                String name =response.body().getResults().get(0).getName().getTitle()+". "+response.body().getResults().get(0).getName().getFirst()+" "+response.body().getResults().get(0).getName().getLast();
                String email=response.body().getResults().get(0).getEmail();
                String phone =response.body().getResults().get(0).getPhone();

                cache= response.body();

                ProgressBar load =getView().findViewById(R.id.load);
                load.setVisibility(View.INVISIBLE);

                TextView status = getView().findViewById(R.id.textView8);
                status.setText("Fetched Users");
                status.setTextColor(Color.GREEN);

                Button button = getView().findViewById(R.id.button3);
                button.setEnabled(true);

                Button button4 =getView().findViewById(R.id.button4);
                button4.setEnabled(true);

            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

                Log.e("", t.getLocalizedMessage());
                Log.e("","NO");

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_query, container, false);
    }
}