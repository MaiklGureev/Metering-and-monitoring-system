package ru.gureev.meteringandmonitorsystem.ui.main.dispatcher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import ru.gureev.meteringandmonitorsystem.EnumUserType;
import ru.gureev.meteringandmonitorsystem.R;
import ru.gureev.meteringandmonitorsystem.Repository;
import ru.gureev.meteringandmonitorsystem.enities.MeterPoint;
import ru.gureev.meteringandmonitorsystem.enities.User;

public class AddMeterPointFragment extends Fragment {

    private EditText editTextNumMeterPoint;
    private EditText editTextCity;
    private EditText editTextDistrict;
    private EditText editTextStreet;
    private EditText editTextHouse;
    private EditText editTextFlat;
    private EditText editTextDescription;
    private Spinner spinnerControllers;

    private Button buttonAddMeterPoint;

    private MeterPoint currentMeterPoint;
    private AddMeterPointViewModel mViewModel;
    private NavController navController;

    private List<User> userList;

    public static AddMeterPointFragment newInstance() {
        return new AddMeterPointFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return inflater.inflate(R.layout.add_meter_point_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddMeterPointViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentMeterPoint = new MeterPoint();

        editTextNumMeterPoint = view.findViewById(R.id.textField_num_meter_point);
        editTextCity = view.findViewById(R.id.textField_city);
        editTextDistrict = view.findViewById(R.id.textField_district);
        editTextStreet = view.findViewById(R.id.textField_street);
        editTextHouse = view.findViewById(R.id.textField_house);
        editTextFlat = view.findViewById(R.id.textField_flat);
        editTextDescription = view.findViewById(R.id.textField_description);

        buttonAddMeterPoint = view.findViewById(R.id.button_create_meter_point);
        buttonAddMeterPoint.setOnClickListener(onClickListener);

        ArrayAdapter<User> userArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_selectable_list_item);
        userList = Repository.getInstance(getActivity()).getNetworkService().getUsersList().getValue();
        userList = filterUsers(userList);
        userArrayAdapter.addAll(userList);

        spinnerControllers = view.findViewById(R.id.spinner_list_of_controllers);
        spinnerControllers.setAdapter(userArrayAdapter);
        spinnerControllers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentMeterPoint.setUserId(userList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    View.OnClickListener onClickListener = v -> {
        currentMeterPoint.generateId();
        currentMeterPoint.setPointNumber(editTextNumMeterPoint.getText().toString());
        currentMeterPoint.setCity(editTextCity.getText().toString());
        currentMeterPoint.setDistrict(editTextDistrict.getText().toString());
        currentMeterPoint.setStreet(editTextStreet.getText().toString());
        currentMeterPoint.setHouse(editTextHouse.getText().toString());
        currentMeterPoint.setFlat(editTextFlat.getText().toString());
        currentMeterPoint.setDescription(editTextDescription.getText().toString());

        if (!currentMeterPoint.getPointNumber().isEmpty() &&
                !currentMeterPoint.getPointNumber().isEmpty() &&
                !currentMeterPoint.getCity().isEmpty() &&
                !currentMeterPoint.getDistrict().isEmpty() &&
                !currentMeterPoint.getStreet().isEmpty() &&
                !currentMeterPoint.getHouse().isEmpty() &&
                !currentMeterPoint.getFlat().isEmpty() &&
                !currentMeterPoint.getDescription().isEmpty()) {
            Toast.makeText(getContext(), "Точка учёта добавлена.", Toast.LENGTH_SHORT).show();
            Repository.getInstance(getActivity()).getNetworkService().addMeterPoint(currentMeterPoint);
            navController.popBackStack();
        } else {
            Toast.makeText(getContext(), "Заполните все поля.", Toast.LENGTH_SHORT).show();
        }

    };

    List<User> filterUsers(List<User> userList) {
        List<User> tempList = new ArrayList<>();
        for (User user : userList) {
            if (user.getEnumUserType() == EnumUserType.CONTROLLER) {
                tempList.add(user);
            }
        }
        return tempList;
    }
}