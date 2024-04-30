package com.malykhnik.freelanceexchnge.service.impls;

import com.malykhnik.freelanceexchnge.model.EventCatcher;
import com.malykhnik.freelanceexchnge.repository.EventCatcherRepository;
import com.malykhnik.freelanceexchnge.service.EventCatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventCatcherServiceImpl implements EventCatcherService {
    private final EventCatcherRepository eventCatcherRepository;
    @Override
    public void saveEventCatcher(EventCatcher eventCatcher) {
        eventCatcherRepository.save(eventCatcher);
    }

    @Override
    public List<EventCatcher> getAllActions() {
        return eventCatcherRepository.findByOrderByDateDesc();
    }
}
