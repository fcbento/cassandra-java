package login.com.fecb.resources;

import io.swagger.annotations.Api;
import login.com.fecb.domain.Product;
import login.com.fecb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/products")
@Api(value="API REST - Products")
public class ProductResource {

    @Autowired
    ProductService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id){
        Product obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<Product>> getAll(Pageable page){
        Page<Product> products = service.list(page);
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody Product product){
        Product obj = service.save(product);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(obj.getProductId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Product product){
        Product obj = service.update(product);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestBody List<Product> ids){
        service.delete(ids);
        return ResponseEntity.noContent().build();
    }
}
