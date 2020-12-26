package ru.gureev.meteringandmonitorsystem.listAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.gureev.meteringandmonitorsystem.R;
import ru.gureev.meteringandmonitorsystem.enities.Message;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> messages;

    public MessageAdapter() {
        this.messages = new ArrayList<>();
    }

    public void setMessagesList(List<Message> messageList) {
        this.messages = messageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static final class MessageViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTextView;
        private final TextView dateTextView;
        private final TextView textTextView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.message_item_title);
            dateTextView = itemView.findViewById(R.id.message_item_date);
            textTextView = itemView.findViewById(R.id.message_item_text);
        }

        private void bind(@NonNull Message message) {
            titleTextView.setText(message.getTitle());
            dateTextView.setText("Дата: " + message.getDate());
            textTextView.setText("Сообщение: " + message.getText());
        }

    }

}
