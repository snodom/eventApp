package pl.studentsevent.studentseventapp.controlers.dtos;

import java.util.Set;

public class EventDto {
    private Long event_id;
    private String name;
    private String organizer;
    private String date; // day
    private String adress;
    private String price;
    private String link;
    private Set<CategoryDto> categoryDtos;

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<CategoryDto> getCategoryDtos() {
        return categoryDtos;
    }

    public void setCategoryDtos(Set<CategoryDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }
}

