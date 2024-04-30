package com.malykhnik.freelanceexchnge.service.impls;

import com.malykhnik.freelanceexchnge.model.EventCatcher;
import com.malykhnik.freelanceexchnge.repository.EventCatcherRepository;
import com.malykhnik.freelanceexchnge.service.EventCatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventCatcherServiceImpl implements EventCatcherService {
    private final EventCatcherRepository eventCatcherRepository;
    @Override
    public void saveEventCatcher(EventCatcher eventCatcher) {
        eventCatcherRepository.save(eventCatcher);
    }
}
