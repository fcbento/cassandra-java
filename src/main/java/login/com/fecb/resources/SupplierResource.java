package login.com.fecb.resources;

import login.com.fecb.domain.Category;
import login.com.fecb.domain.Supplier;
import login.com.fecb.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.ws.Response;
import java.net.URI;
import java.util.UUID;

@Controller
@RequestMapping(value="/api/suppliers")
public class SupplierResource {

    @Autowired
    SupplierService supplierService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Supplier> create(@RequestBody Supplier supplier) {
        Supplier obj = supplierService.create(supplier);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Slice<Supplier>> getAll(Pageable page){
        Slice<Supplier> suppliers = supplierService.getAll(page);
        return ResponseEntity.ok().body(suppliers);
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ResponseEntity<Supplier> getById(@RequestBody @PathVariable("id")UUID id){
        Supplier supplier = supplierService.findById(id);
        return ResponseEntity.ok().body(supplier);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> update(@RequestBody Supplier supplier) {
        Supplier obj = supplierService.update(supplier);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@RequestBody @PathVariable("id")UUID id) {
        try {
            supplierService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
