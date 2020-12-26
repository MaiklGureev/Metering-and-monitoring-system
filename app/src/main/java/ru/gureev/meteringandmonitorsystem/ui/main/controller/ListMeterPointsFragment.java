package ru.gureev.meteringandmonitorsystem.ui.main.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.gureev.meteringandmonitorsystem.R;
import ru.gureev.meteringandmonitorsystem.Repository;
import ru.gureev.meteringandmonitorsystem.enities.MeterPoint;
import ru.gureev.meteringandmonitorsystem.listAdapters.MeterPointsForControllerAdapter;

import static android.content.ContentValues.TAG;

public class ListMeterPointsFragment extends Fragment {

    private ListMeterPointsViewModel mViewModel;

    public static ListMeterPointsFragment newInstance() {
        return new ListMeterPointsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_meter_points_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_list_meter_points);
        MeterPointsForControllerAdapter meterPointsForControllerAdapter = new MeterPointsForControllerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(meterPointsForControllerAdapter);

        Repository.getInstance(getActivity()).getNetworkService().getMeterPoints();
        Repository.getInstance(getActivity()).getNetworkService().getMeterPointsList().observe(getViewLifecycleOwner(), meterPoints -> {
            Log.d(TAG, "onViewCreated: messages" + meterPoints.toString());
            meterPoints = findMeterPointsForCurrentUser(meterPoints);
            meterPointsForControllerAdapter.setMeterPointsList(meterPoints);
        });

    }

    List<MeterPoint> findMeterPointsForCurrentUser(List<MeterPoint> meterPoints) {
        List<MeterPoint> tempMeterPoints = new ArrayList<>();
        String userId = Repository.getInstance(getActivity()).getNetworkService().getCurrentUserInfo().getValue().getId();
        for (MeterPoint m : meterPoints) {
            if (m.getUserId().equals(userId)) {
                tempMeterPoints.add(m);
            }
        }
        return tempMeterPoints;
    }

}