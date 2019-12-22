package pl.studentsevent.studentseventapp.controlers;


import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import pl.studentsevent.studentseventapp.controlers.dtos.CategoryDto;
import pl.studentsevent.studentseventapp.model.event.Category;
import pl.studentsevent.studentseventapp.model.event.Event;
import pl.studentsevent.studentseventapp.service.CategoryService;
import pl.studentsevent.studentseventapp.service.EventService;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/admin")
//@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EventService eventService;

    @GetMapping("/categoryList")
    public List<Category> categoryList(){
        return categoryService.adminGetAll();
    }

    @GetMapping("/eventList")
    public List<Event> eventList(){
        return eventService.adminGetAll();
    }

    @PutMapping(value = "/confirmation")
    public ResponseEntity confirmation(@Valid @RequestBody Long event_id){
        try{
           eventService.confirmation(event_id);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteEvent")
    public ResponseEntity deleteEvent(@Valid @RequestBody Long event_id){
        try{
            eventService.deleteEvent(event_id);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/addCategory")
    public ResponseEntity addCategory(@Valid @RequestBody CategoryDto categoryDto){
        try{
            categoryService.addCategory(categoryDto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteCategory")
    public ResponseEntity deleteCategory(@Valid @RequestBody Long category_id){
        try{
            categoryService.deleteCategory(category_id);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
