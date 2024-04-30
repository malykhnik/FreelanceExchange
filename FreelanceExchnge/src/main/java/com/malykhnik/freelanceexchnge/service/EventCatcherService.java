package com.malykhnik.freelanceexchnge.service;

import com.malykhnik.freelanceexchnge.model.EventCatcher;

import java.text.SimpleDateFormat;
import java.util.List;

public interface EventCatcherService {
    void saveEventCatcher(EventCatcher eventCatcher);
    List<EventCatcher> getAllActions();
}
