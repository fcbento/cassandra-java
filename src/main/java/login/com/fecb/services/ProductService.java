package login.com.fecb.services;

import login.com.fecb.domain.Product;
import login.com.fecb.repository.ProductRepository;
import login.com.fecb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Product find(Integer id) {

        Optional<Product> obj = Optional.ofNullable(repository.findById(id));

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Not found! Id: " + id + ", Type: " + Product.class.getName()));
    }

    public Product save(Product product){
        return repository.save(product);
    }

    public Page<Product> list(Pageable page){
        return repository.findAll(page);
    }

    public void delete(Product product){
        repository.delete(product);
    }

    public Product update(Product product){
        return repository.save(product);
    }

}
