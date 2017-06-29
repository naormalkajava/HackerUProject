package com.example.ericbell.hackeruproject.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ericbell.hackeruproject.R;
import com.example.ericbell.hackeruproject.firebase.IO;
import com.example.ericbell.hackeruproject.firebase.Ynet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class YnetFragment extends Fragment {
    interface OnYnetArrivedListener {
        void ynetArrived(List<Ynet> data);
    }


    public static void getYnet(final OnYnetArrivedListener listener) {
        new AsyncTask<Void, Void, List<Ynet>>() {
            @Override
            protected List<Ynet> doInBackground(Void... params) {
                try {
                    String xml = IO.readWebSite("the xml address", "Windows-1255");
                    List<Ynet> data = parse(xml);
                    return data;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }


            @Override
            protected void onPostExecute(List<Ynet> ynets) {
                listener.ynetArrived(ynets);
            }
        }.execute();
    }

    private List<Ynet> parse(String xml) {
        List<Ynet> data = new ArrayList<>();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_ynet, container, false);

        return v;
    }


    public class YnetAdapter extends RecyclerView.Adapter<YnetAdapter.YnetViewHOlder> {
        Context context;
        LayoutInflater inflater;
        List<Ynet> data;

        @Override
        public YnetViewHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(YnetViewHOlder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class YnetViewHOlder extends RecyclerView.ViewHolder {
            ImageView ivImageYnet;
            TextView tvDescription;

            public YnetViewHOlder(View itemView) {
                super(itemView);

                ivImageYnet = (ImageView) itemView.findViewById(R.id.ivImageYnet);
                tvDescription = (TextView) itemView.findViewById(R.id.tvdescription);
            }
        }
    }

}