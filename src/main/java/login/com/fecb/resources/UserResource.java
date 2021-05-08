package login.com.fecb.resources;

import login.com.fecb.domain.Category;
import login.com.fecb.domain.User;
import login.com.fecb.dto.UserDTO;
import login.com.fecb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/api/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<User> find(@PathVariable Integer id){
        User obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO objDto){

        User obj = service.fromDTO(objDto);
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<User>> getAll(Pageable page){
        Page<User> users = service.getUsers(page);
        return ResponseEntity.ok().body(users);
    }

}
