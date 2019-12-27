package pl.studentsevent.studentseventapp.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.studentsevent.studentseventapp.controlers.dtos.CategoryDto;
import pl.studentsevent.studentseventapp.controlers.dtos.EventDto;
import pl.studentsevent.studentseventapp.service.CategoryService;
import pl.studentsevent.studentseventapp.service.EventService;
import pl.studentsevent.studentseventapp.service.imp.FileStorageService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private FileStorageService fileStorageService;

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
    public  ResponseEntity addEvent(@Valid @RequestBody @RequestParam("request") String eventDto, @RequestParam("file") MultipartFile file){
        try{

            String fileName = fileStorageService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();


            //return new EventDto(eventDto.getEvent_id(),eventDto.getName(),eventDto.getOrganizer(),eventDto.getDate(),eventDto.getHour(),eventDto.getAdress(),eventDto.getPrice(),eventDto.)
          //  eventDto.setLink(fileDownloadUri);

          //  eventService.addEvent(eventDto);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
