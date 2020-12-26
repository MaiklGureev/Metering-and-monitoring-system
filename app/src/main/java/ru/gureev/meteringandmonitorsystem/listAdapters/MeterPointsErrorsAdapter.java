package ru.gureev.meteringandmonitorsystem.listAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.gureev.meteringandmonitorsystem.Config;
import ru.gureev.meteringandmonitorsystem.R;
import ru.gureev.meteringandmonitorsystem.enities.MeterPoint;

public class MeterPointsErrorsAdapter extends RecyclerView.Adapter<MeterPointsErrorsAdapter.MeterPointsErrorsViewHolder> {

    private List<MeterPoint> meterPoints;

    public void setMeterPointsList(List<MeterPoint> meterPoints) {
        this.meterPoints = meterPoints;
        notifyDataSetChanged();
    }

    public MeterPointsErrorsAdapter() {
        this.meterPoints = new ArrayList<>();
    }

    @NonNull
    @Override
    public MeterPointsErrorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meter_point_error, parent, false);
        return new MeterPointsErrorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeterPointsErrorsViewHolder holder, int position) {
        holder.bind(meterPoints.get(position));
    }

    @Override
    public int getItemCount() {
        return meterPoints.size();
    }

    static final class MeterPointsErrorsViewHolder extends RecyclerView.ViewHolder {

        private final TextView numPointTextView;
        private final TextView addressTextView;
        private final TextView deviceStatusTextView;
        private final TextView meterReadingStatusTextView;
        private final TextView problemDescriptionTextView;
        private final CardView cardView;

        public MeterPointsErrorsViewHolder(@NonNull View itemView) {
            super(itemView);
            numPointTextView = itemView.findViewById(R.id.item_meter_point_num_point);
            addressTextView = itemView.findViewById(R.id.item_meter_point_address);
            deviceStatusTextView = itemView.findViewById(R.id.item_meter_point_device_status);
            problemDescriptionTextView = itemView.findViewById(R.id.item_meter_point_problem_description);
            meterReadingStatusTextView = itemView.findViewById(R.id.item_meter_reading_status);
            cardView = itemView.findViewById(R.id.cardView);
        }

        private void bind(@NonNull MeterPoint meterPoint) {
            String address = String.format("Адрес: %s, р-н %s, ул. %s, д. %s, кв. %s",
                    meterPoint.getCity(),
                    meterPoint.getDistrict(),
                    meterPoint.getStreet(),
                    meterPoint.getHouse(),
                    meterPoint.getFlat());

            numPointTextView.setText("Номер точки учёта: " + meterPoint.getPointNumber());
            addressTextView.setText(address);
            if (meterPoint.isReadingDone()) {
                meterReadingStatusTextView.setText("Статус осмотра: выполнен");
            } else {
                meterReadingStatusTextView.setText("Статус осмотра: в работе");
            }
            deviceStatusTextView.setText("Состояние прибора: " + meterPoint.getDeviceStatus());
            problemDescriptionTextView.setText("Примечание: " + meterPoint.getProblemDescription());
            if (meterPoint.getDeviceStatus().equals(Config.DEVICE_STATUS_BAD) || meterPoint.isProblemPoint()) {
                cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.getContext(), R.color.red));
            } else if (meterPoint.isReadingDone() == false) {
                cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.getContext(), R.color.gray));
            }
        }

    }
}
