package ru.geekbrains.team1.geek_crm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.team1.geek_crm.entities.Event;
import ru.geekbrains.team1.geek_crm.repository.EventRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Event save(Event event) {
        return eventRepository.save(event);
    }

}
