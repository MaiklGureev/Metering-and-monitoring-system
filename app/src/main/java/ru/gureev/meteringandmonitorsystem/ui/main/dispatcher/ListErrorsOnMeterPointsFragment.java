package ru.gureev.meteringandmonitorsystem.ui.main.dispatcher;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.gureev.meteringandmonitorsystem.R;
import ru.gureev.meteringandmonitorsystem.Repository;
import ru.gureev.meteringandmonitorsystem.enities.MeterPoint;
import ru.gureev.meteringandmonitorsystem.listAdapters.MeterPointsErrorsAdapter;

import static android.content.ContentValues.TAG;

public class ListErrorsOnMeterPointsFragment extends Fragment {

    private ListErrorsOnMeterPointsViewModel mViewModel;

    public static ListErrorsOnMeterPointsFragment newInstance() {
        return new ListErrorsOnMeterPointsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_errors_on_meter_points_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListErrorsOnMeterPointsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_list_errors);
        MeterPointsErrorsAdapter meterPointsErrorsAdapter = new MeterPointsErrorsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(meterPointsErrorsAdapter);

        Repository.getInstance(getActivity()).getNetworkService().getMeterPoints();
        Repository.getInstance(getActivity()).getNetworkService().getMeterPointsList().observe(getViewLifecycleOwner(), meterPoints -> {
            Log.d(TAG, "onViewCreated: messages" + meterPoints.toString());
            List<MeterPoint> list = findErrorMeterPoints(meterPoints);
            meterPointsErrorsAdapter.setMeterPointsList(list);
        });

    }

    List<MeterPoint> findErrorMeterPoints(List<MeterPoint> meterPoints) {
        List<MeterPoint> tempMeterPoints = new ArrayList<>();
        for (MeterPoint meterPoint : meterPoints) {
//            if (meterPoint.isProblemPoint()) {
            tempMeterPoints.add(meterPoint);
//            }
        }
        return tempMeterPoints;
    }
}