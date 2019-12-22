package pl.studentsevent.studentseventapp.model.event;

import javax.persistence.*;
import java.util.Set;

@Table(name="categories")
@Entity
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long category_id;
    @Column
    private String name;

    @ManyToMany(mappedBy = "categories")
    @Column
    private Set<Event> events;

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
