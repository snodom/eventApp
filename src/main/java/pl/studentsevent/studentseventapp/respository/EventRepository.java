package pl.studentsevent.studentseventapp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.studentsevent.studentseventapp.model.event.Event;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();
    List<Event> findAllByConfirmationIs(@NotNull int confirmation);
}
