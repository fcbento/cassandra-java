package login.com.fecb.services;

import login.com.fecb.domain.Address;
import login.com.fecb.repository.AddressRepository;
import login.com.fecb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address find(Integer id) {

        Optional<Address> obj = Optional.ofNullable(addressRepository.findById(id));

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Not found! Id: " + id + ", Type: " + Address.class.getName()));
    }

    public List<Address> findByUser(Integer id) {

        List<Address> obj = addressRepository.findByUserId(id);

        return obj;
    }


    public Address save(Address address){
        return addressRepository.save(address);
    }

    public Page<Address> list(Pageable page){
        return addressRepository.findAll(page);
    }

    public void delete(Long id){
        addressRepository.deleteById(id);
    }

    public Address update(Address address){
        return addressRepository.save(address);
    }
}
