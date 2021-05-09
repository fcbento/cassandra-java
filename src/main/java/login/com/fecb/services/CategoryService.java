package login.com.fecb.services;

import login.com.fecb.domain.Category;
import login.com.fecb.repository.CategoryRepository;
import login.com.fecb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    public Category find(Integer id) {

        Optional<Category> obj = Optional.ofNullable(repository.findById(id));

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Not found! Id: " + id + ", Type: " + Category.class.getName()));
    }

    public Category save(Category category){
        return repository.save(category);
    }

    public Page<Category> list(Pageable page){
        return repository.findAll(page);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Category update(Category category){
        return repository.save(category);
    }
}
