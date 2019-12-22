package pl.studentsevent.studentseventapp.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.studentsevent.studentseventapp.controlers.dtos.CategoryDto;
import pl.studentsevent.studentseventapp.controlers.dtos.EventDto;
import pl.studentsevent.studentseventapp.service.CategoryService;
import pl.studentsevent.studentseventapp.service.EventService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private EventService eventService;

    @Autowired
    private CategoryService categoryService;

//    @GetMapping("/all")
//    public String hello(){
//        return "Hello world";
//    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
//    @GetMapping("/admin/all")
//    public String securedhello(){
//        return "Secured";
//    }

    // where confirmation is 1
    @GetMapping("/event/getAllEvents")
    public List<EventDto> getAllEvents(){
        return eventService.getAll();
    }

    @GetMapping("/event/getAllArchives")
    public List<EventDto> getAllArchives(){
        return eventService.getAllArchives();
    }

    @GetMapping("/category/getAllCategories")
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAll();
    }

    @PostMapping("/event/addEvent")
    public ResponseEntity addCategory(@Valid @RequestBody EventDto eventDto){
        try{
           eventService.addEvent(eventDto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
