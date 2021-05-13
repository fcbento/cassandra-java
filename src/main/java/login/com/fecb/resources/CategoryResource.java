package login.com.fecb.resources;

import io.swagger.annotations.Api;
import login.com.fecb.domain.Category;
import login.com.fecb.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/categories")
@Api(value="API REST - Category")
public class CategoryResource {

    @Autowired
    CategoryService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Category> find(@PathVariable Integer id){
        Category obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<Category>> getAll(Pageable page){
        Page<Category> products = service.list(page);
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody Category category){
        Category obj = service.save(category);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getCategoryId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Category category){
        Category obj = service.update(category);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestBody @PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
