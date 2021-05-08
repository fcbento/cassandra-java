package login.com.fecb.services;

import login.com.fecb.domain.Order;
import login.com.fecb.repository.OrderRepository;
import login.com.fecb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    public Order find(Integer id) {

        Optional<Order> obj = Optional.ofNullable(repository.findById(id));

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Not found! Id: " + id + ", Type: " + Order.class.getName()));
    }

    public Order save(Order order){
        order.setOrderStatus(1);
        return repository.save(order);
    }

    public Page<Order> list(Pageable page){
        return repository.findAll(page);
    }

    public void delete(Order order){
        repository.delete(order);
    }

    public Order update(Order order){
        return repository.save(order);
    }
}
