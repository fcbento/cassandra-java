package login.com.fecb.resources;

import login.com.fecb.domain.Address;
import login.com.fecb.domain.Category;
import login.com.fecb.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/api/address")
public class AddressResource {

    @Autowired
    AddressService addressService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<List<Address>> getAddressByUser(@RequestBody @PathVariable Integer id){
        List<Address> address = addressService.findByUser(id);
        return ResponseEntity.ok().body(address);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody Address address){
        Address obj = addressService.save(address);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
