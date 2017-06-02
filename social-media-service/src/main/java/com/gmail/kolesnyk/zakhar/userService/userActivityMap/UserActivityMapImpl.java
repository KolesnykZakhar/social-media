package com.gmail.kolesnyk.zakhar.userService.userActivityMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserActivityMapImpl implements UserActivityMap {

    private int inactiveTime;

    private Map<Integer, Long> mapActivity;

    public UserActivityMapImpl(@Autowired Environment environment) {
        inactiveTime = Integer.parseInt(environment.getProperty("offlineIfInactiveMin"));
        mapActivity = new HashMap<>();
    }

    @Override
    public void put(Integer idUser) {
        mapActivity.put(idUser, System.currentTimeMillis());
    }

    @Override
    public boolean isOnline(Integer idUser) {
        Long time = mapActivity.get(idUser);
        return (time != null && System.currentTimeMillis() - time < inactiveTime * 60000);
    }
}
