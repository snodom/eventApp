package pl.studentsevent.studentseventapp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.studentsevent.studentseventapp.controlers.dtos.CategoryDto;
import pl.studentsevent.studentseventapp.controlers.dtos.EventDto;
import pl.studentsevent.studentseventapp.model.event.Category;
import pl.studentsevent.studentseventapp.model.event.Event;
import pl.studentsevent.studentseventapp.respository.CategoryRepository;
import pl.studentsevent.studentseventapp.respository.EventRepository;
import pl.studentsevent.studentseventapp.service.EventService;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EventServiceImp implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<EventDto> getAll() {
        List<EventDto> eventDtos = eventDtoListMapper(this.eventRepository.findAllByConfirmationIs(1));
        return eventDtos.stream()
                .filter(eventDto -> {
                    try {
                        Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(eventDto.getDate());
                        return parsedDate.compareTo(new Date()) < 0;
                    } catch (ParseException e) {
                        return false;
                    }
                })
                .sorted(Comparator.comparing(a -> {
                    try {
                        return new SimpleDateFormat("dd/MM/yyyy").parse(a.getDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return new Date("11/22/2999");
                })).collect(Collectors.toList());
    }

    @Override
    public List<Event> adminGetAll() {

        return eventRepository.findAll();
    }

    @Override
    public List<EventDto> getAllArchives() {
        List<EventDto> eventDtos = eventDtoListMapper(this.eventRepository.findAllByConfirmationIs(1));
        return eventDtos.stream()
                .filter(eventDto -> {
                    try {
                        Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(eventDto.getDate());
                        return parsedDate.compareTo(new Date()) > 0;
                    } catch (ParseException e) {
                        return false;
                    }
                })
                .sorted(Comparator.comparing(a -> {
            try {
                return new SimpleDateFormat("dd/MM/yyyy").parse(a.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date("11/22/2999");
        })).collect(Collectors.toList());


    }


    // zmiana potwierdzenie na 1
    @Override
    public Event confirmation(Long event_id) {
        Event findEvent = this.eventRepository.getOne(event_id);

        Event confirmedEvent = new Event(findEvent);
        confirmedEvent.setConfirmation(1);
        return eventRepository.save(confirmedEvent);
    }

    //TODO zmiana potwierdzenia na 2 jezeli data jest starsza niz costam

    @Override
    public void deleteEvent(Long event_id) {

        Event event = eventRepository.getOne(event_id);

        for(Category category : event.getCategories()){
            category.getEvents().remove(event);
        }
        eventRepository.deleteById(event_id);
    }

    @Override
    @Transactional
    public void addEvent(EventDto eventDto) {
        Event event = new Event();

        event.setName(eventDto.getName());
        event.setOrganizer(eventDto.getOrganizer());
        event.setDate(eventDto.getDate());
        event.setAdress(eventDto.getAdress());
        event.setPrice(eventDto.getPrice());
        event.setHour(eventDto.getHour());
        event.setLink(eventDto.getLink());
        event.setCategories(this.categorySetMapper(eventDto.getCategoryDtos()));

        eventRepository.save(event);
    }
//    @Override
//    public EventDto getById(Long event_id) {
//        return eventDtoMapper(this.eventRepository.findByEvent_id(event_id));
//    }

    private List<EventDto> eventDtoListMapper(List<Event> events){
       return events.stream().map((this::eventDtoMapper)).collect(Collectors.toList());
    }

    private Set<CategoryDto> categoryDtoSetMapper(Set<Category> categories){
        return categories.stream().map((this::categoryDtoMapper)).collect(Collectors.toSet());
    }

    private Set<Category> categorySetMapper(Set<CategoryDto> categoriesDto){
        return categoriesDto.stream().map((categoryDto -> {
            return categoryRepository.findById(categoryDto.getCategory_id()).get();
        })).collect(Collectors.toSet());
    }


    private EventDto eventDtoMapper(Event event){

        EventDto eventDto = new EventDto();
        eventDto.setEvent_id(event.getEvent_id());
        eventDto.setName(event.getName());
        eventDto.setOrganizer(event.getOrganizer());
        eventDto.setDate(event.getDate());
        eventDto.setAdress(event.getAdress());
        eventDto.setPrice(event.getPrice());
        eventDto.setLink(event.getLink());
        eventDto.setHour(event.getHour());
        eventDto.setCategoryDtos(categoryDtoSetMapper(event.getCategories()));
        return eventDto;
    }

    private CategoryDto categoryDtoMapper(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategory_id(category.getCategory_id());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
