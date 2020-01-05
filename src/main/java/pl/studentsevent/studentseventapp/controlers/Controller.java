package pl.studentsevent.studentseventapp.controlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
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
import java.io.DataInput;
import java.util.List;
import java.util.Map;

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

            System.out.println(eventDto);
            Gson g = new Gson();
            String aa = eventDto.trim();
            EventDto a = g.fromJson(eventDto.trim(), EventDto.class);

            EventDto my = new ObjectMapper().readValue(eventDto, EventDto.class);
            String fileName = fileStorageService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();
            System.out.println(fileDownloadUri); //TODO tutaj trzeba bedzie podac sciezke do pliku

            my.setPathToFile(fileDownloadUri);

            eventService.addEvent(my);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

