package pl.studentsevent.studentseventapp.model.event;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long event_id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String organizer;

    @Column
    @NotNull
    private String date; // day

    @Column

    private String hour;

    @Column

    private String adress;

    @Column

    private String price;

    @Column

    private String link;

    @Column
    @NotNull
    private int confirmation=0;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "event_category",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")

    )
    @Column
    private Set<Category> categories;

    public Event() {
    }

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

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
    public Event(Event event) {
        this.event_id =event.getEvent_id();
        this.name=event.getName();
        this.organizer=event.getOrganizer();
        this.date = event.getDate();
        this.adress = event.getAdress();
        this.price = event.getPrice();
        this.link= event.getLink();
        this.confirmation = event.getConfirmation();
        this.categories = event.getCategories();
        this.hour=event.getHour();
    }

}