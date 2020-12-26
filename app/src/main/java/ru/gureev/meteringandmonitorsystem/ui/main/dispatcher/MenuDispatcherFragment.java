package ru.gureev.meteringandmonitorsystem.ui.main.dispatcher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ru.gureev.meteringandmonitorsystem.R;

public class MenuDispatcherFragment extends Fragment {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private NavController navController;
    private MenuDispatcherViewModel mViewModel;

    public static MenuDispatcherFragment newInstance() {
        return new MenuDispatcherFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return inflater.inflate(R.layout.menu_dispatcher_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MenuDispatcherViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button1 = view.findViewById(R.id.button_add_meter_point);
        button2 = view.findViewById(R.id.button_add_info_message);
        button3 = view.findViewById(R.id.button_list_error_on_meter_points);
        button6 = view.findViewById(R.id.button_show_info_messages);
        button4 = view.findViewById(R.id.button_user_info);
        button5 = view.findViewById(R.id.button_exit);

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = v -> {
        switch (v.getId()) {
            case R.id.button_add_meter_point: {
                navController.navigate(R.id.action_menuDispatcherFragment_to_addMeterPointFragment);
                break;
            }
            case R.id.button_add_info_message: {
                navController.navigate(R.id.action_menuDispatcherFragment_to_addMessageFragment);
                break;
            }
            case R.id.button_list_error_on_meter_points: {
                navController.navigate(R.id.action_menuDispatcherFragment_to_listErrorsOnMeterPointsFragment);
                break;
            }
            case R.id.button_show_info_messages: {
                navController.navigate(R.id.action_menuDispatcherFragment_to_listInfoMessagesFragment);
                break;
            }
            case R.id.button_user_info: {
                navController.navigate(R.id.action_menuDispatcherFragment_to_userInfo);
                break;
            }
            case R.id.button_exit: {
                System.exit(1);
                break;
            }
        }
    };
}