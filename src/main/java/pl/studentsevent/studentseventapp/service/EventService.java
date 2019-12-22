package pl.studentsevent.studentseventapp.service;

import pl.studentsevent.studentseventapp.controlers.dtos.EventDto;
import pl.studentsevent.studentseventapp.model.event.Event;

import java.util.List;

public interface EventService {
    List<EventDto> getAll();
    List<Event> adminGetAll();
    Event confirmation(Long event_id);
    void deleteEvent(Long event_id);
    void addEvent(EventDto eventDto);
   // EventDto getById(Long event_id);
}
