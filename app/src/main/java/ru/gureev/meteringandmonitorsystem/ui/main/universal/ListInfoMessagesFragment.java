package ru.gureev.meteringandmonitorsystem.ui.main.universal;

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

import ru.gureev.meteringandmonitorsystem.R;
import ru.gureev.meteringandmonitorsystem.Repository;
import ru.gureev.meteringandmonitorsystem.listAdapters.MessageAdapter;

import static android.content.ContentValues.TAG;

public class ListInfoMessagesFragment extends Fragment {

    private ListInfoMessagesViewModel mViewModel;

    public static ListInfoMessagesFragment newInstance() {
        return new ListInfoMessagesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_info_messages_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListInfoMessagesViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_messages);
        MessageAdapter messageAdapter = new MessageAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(messageAdapter);

        Repository.getInstance(getActivity()).getNetworkService().getMessages();
        Repository.getInstance(getActivity()).getNetworkService().getMessagesList().observe(getViewLifecycleOwner(), messages -> {
            Log.d(TAG, "onViewCreated: messages" + messages.toString());
            messageAdapter.setMessagesList(messages);
        });
    }
}