package ru.gureev.meteringandmonitorsystem;

import android.app.Activity;

public class Repository {

    private static volatile Repository REPOSITORY;

    private NetworkService networkService;

    public NetworkService getNetworkService() {
        return networkService;
    }

    private Repository(Activity activity) {
        networkService = new NetworkService(activity);
    }

    public static Repository getInstance(Activity activity) {
        Repository result = REPOSITORY;

        if (result != null) {
            return result;
        }
        synchronized (Repository.class) {
            if (result == null) {
                REPOSITORY = new Repository(activity);
            }
            return REPOSITORY;
        }
    }


}