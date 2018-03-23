package com.thanhhai.badminton.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thanhhai.badminton.R;
import com.thanhhai.badminton.ResideMenu;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private View parentView;
    private ResideMenu resideMenu;
    private CardView cv_news, cv_videos, cv_exchange, cv_laws, cv_share;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_home, container, false);

        return parentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addControls();
        addEvents();
    }

    private void addEvents() {
        cv_videos.setOnClickListener(this);
        cv_news.setOnClickListener(this);
        cv_laws.setOnClickListener(this);
        cv_exchange.setOnClickListener(this);
        cv_share.setOnClickListener(this);
    }

    private void addControls() {
        cv_exchange = (CardView) getActivity().findViewById(R.id.cv_exchange);
        cv_laws = (CardView) getActivity().findViewById(R.id.cv_laws);
        cv_news = (CardView) getActivity().findViewById(R.id.cv_news);
        cv_share = (CardView) getActivity().findViewById(R.id.cv_share);
        cv_videos = (CardView) getActivity().findViewById(R.id.cv_videos);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cv_news:
                Toast.makeText(getContext(), "news", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cv_videos:
                Toast.makeText(getContext(), "videos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cv_laws:
                LawsFragment laws_fragment = new LawsFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.main_fragment, laws_fragment, laws_fragment.getTag())
                        .addToBackStack(laws_fragment.getClass().getSimpleName())
                        .commit();
                break;
            case R.id.cv_share:
                Toast.makeText(getContext(), "share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cv_exchange:
                Toast.makeText(getContext(), "exchange", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
