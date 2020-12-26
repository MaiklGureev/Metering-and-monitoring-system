package ru.gureev.meteringandmonitorsystem.ui.main;

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

import ru.gureev.meteringandmonitorsystem.EnumUserType;
import ru.gureev.meteringandmonitorsystem.R;
import ru.gureev.meteringandmonitorsystem.Repository;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private Button enter;
    private EditText login;
    private EditText password;
    private NavController navController;


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return inflater.inflate(R.layout.login_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Repository.getInstance(getActivity());
        enter = view.findViewById(R.id.button_authorization);
        login = view.findViewById(R.id.edit_text_login);
        password = view.findViewById(R.id.edit_text_password);

        enter.setOnClickListener(v -> {
            authorize(v);
        });
    }

    void authorize(View view) {
        Repository.getInstance(getActivity()).getNetworkService().signIn(login.getText().toString(), password.getText().toString());
        Repository.getInstance(getActivity()).getNetworkService().getCurrentUserInfo().observe(getViewLifecycleOwner(), user -> {
            EnumUserType userType = user.getEnumUserType();
            if (userType == EnumUserType.CONTROLLER) {
                navController.navigate(R.id.action_loginFragment_to_menuControllerFragment);
            } else if (userType == EnumUserType.DISPATCHER) {
                navController.navigate(R.id.action_loginFragment_to_menuDispatcherFragment);
            } else {
                Toast.makeText(view.getContext(), R.string.wrongLoginOrPassword, Toast.LENGTH_SHORT).show();
            }
            Repository.getInstance(getActivity()).getNetworkService().getCurrentUserInfo().removeObservers(getViewLifecycleOwner());
        });

    }
}