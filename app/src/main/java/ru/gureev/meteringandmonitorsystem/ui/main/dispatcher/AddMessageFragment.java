package ru.gureev.meteringandmonitorsystem.ui.main.dispatcher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ru.gureev.meteringandmonitorsystem.Config;
import ru.gureev.meteringandmonitorsystem.R;
import ru.gureev.meteringandmonitorsystem.Repository;
import ru.gureev.meteringandmonitorsystem.enities.Message;

public class AddMessageFragment extends Fragment {

    private AddMessageViewModel mViewModel;
    private EditText editTextTitle;
    private EditText editTextText;
    private Button buttonAdd;
    private NavController navController;

    public static AddMessageFragment newInstance() {
        return new AddMessageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return inflater.inflate(R.layout.add_message_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddMessageViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonAdd = view.findViewById(R.id.button_create_message);

        editTextTitle = view.findViewById(R.id.textField_title);
        editTextText = view.findViewById(R.id.textField_text);

        buttonAdd.setOnClickListener(v -> {

            String title = editTextTitle.getText().toString();
            String text = editTextText.getText().toString();

            Message message = new Message();
            message.generateId();
            message.setTitle(title);
            message.setText(text);
            message.setDate(Config.getCurrentDateTime());

            if (!title.isEmpty() && !text.isEmpty()) {
                Repository.getInstance(getActivity()).getNetworkService().addMessage(message);
                navController.popBackStack();
            } else {
                Toast.makeText(getContext(), "Заполните все поля.", Toast.LENGTH_SHORT).show();
            }

        });
    }
}