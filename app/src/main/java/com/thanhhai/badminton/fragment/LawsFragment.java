package com.thanhhai.badminton.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thanhhai.badminton.R;
import com.thanhhai.badminton.activities.LawsActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class LawsFragment extends Fragment {

    ListView lvLaws;
    ProgressDialog progressDialog;
    ArrayList<String> mListTitle;
    ArrayAdapter adapter;
    ArrayList<String> arr = new ArrayList();
    String url = "https://badminton-laws.firebaseapp.com/laws/law";

    public static LawsFragment newInstance() {
        LawsFragment lawsFragment = new LawsFragment();
        return lawsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_laws, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arr.clear();
        lvLaws = (ListView) getActivity().findViewById(R.id.lvLaws);
        new LawsAsyncTask().execute();
        lvLaws.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = mListTitle.get(position);
                String nameFile = String.valueOf(position);
                Intent intent = new Intent(view.getContext(), LawsActivity.class);
                intent.putExtra("nameFile", nameFile);
                intent.putExtra("titleFile", title);
                startActivity(intent);
            }
        });
    }

    private class LawsAsyncTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getContext(), "", "Loading..Please wait.", false, false);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            getTitle();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mListTitle = new ArrayList();
            mListTitle = arr;
            adapter = new ArrayAdapter(getContext(), R.layout.item_laws, arr);
            lvLaws.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    public void getTitle() {
        for (int i = 0; i <= 18; i++) {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url + i + ".html", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Document doc = Jsoup.parse(response);
                    if (doc != null) {
                        Elements elements = doc.select("h3");
                        arr.add(elements.get(0).text());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("test", error.toString());
                }
            });
            requestQueue.add(stringRequest);
        }
    }
}
