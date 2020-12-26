package ru.gureev.meteringandmonitorsystem.ui.main.universal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.gureev.meteringandmonitorsystem.R;
import ru.gureev.meteringandmonitorsystem.Repository;

public class UserInfoFragment extends Fragment {

    private UserInfoViewModel mViewModel;

    private TextView nameTextView;
    private TextView lastNameTextView;
    private TextView fatherNameTextView;
    private TextView positionTextView;
    private TextView personalNumTextView;
    private TextView brigadeNumTextView;
    private TextView departmentNumTextView;

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_info_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserInfoViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameTextView = view.findViewById(R.id.text_name);
        lastNameTextView = view.findViewById(R.id.text_surname);
        fatherNameTextView = view.findViewById(R.id.text_middle_name);
        positionTextView = view.findViewById(R.id.text_position);
        personalNumTextView = view.findViewById(R.id.text_personal_number);
        brigadeNumTextView = view.findViewById(R.id.text_brigade_number);
        departmentNumTextView = view.findViewById(R.id.text_department_number);

        Repository.getInstance(getActivity()).getNetworkService().getCurrentUserInfo().observe(getViewLifecycleOwner(), user -> {
            nameTextView.setText("Имя: " + user.getName());
            lastNameTextView.setText("Фамилия: " + user.getLastName());
            fatherNameTextView.setText("Отчество: " + user.getFatherName());
            positionTextView.setText("Должность: " + user.getPosition());
            personalNumTextView.setText("Персональный номер: " + user.getPersonalNum());
            brigadeNumTextView.setText("Номер бригады: " + user.getBrigadeNum());
            departmentNumTextView.setText("Номер отдела:  " + user.getDepartmentNum());
        });
    }
}