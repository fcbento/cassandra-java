package login.com.fecb.resources;

import io.swagger.annotations.Api;
import login.com.fecb.domain.Order;
import login.com.fecb.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="API REST - Orders")
public class OrderResource {

    @Autowired
    OrderService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Order> find(@PathVariable Integer id){
        Order obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<Order>> getAll(Pageable page){
        Page<Order> products = service.list(page);
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody Order order){
        Order obj = service.save(order);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getProductOrderId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Order order){
        Order obj = service.update(order);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestBody Order order){
        service.delete(order);
        return ResponseEntity.noContent().build();
    }
}
