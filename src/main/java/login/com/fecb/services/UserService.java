package login.com.fecb.services;

import login.com.fecb.domain.Address;
import login.com.fecb.domain.User;
import login.com.fecb.dto.UserDTO;
import login.com.fecb.repository.UserRepository;
import login.com.fecb.security.UserSecurity;
import login.com.fecb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private AddressService addressService;

    public User find(Integer id){

        //UserSecurity user = UserServiceAuth.authenticated();

        //if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            //throw new AuthorizationException("Denied");
        //}

        Optional<User> obj = userRepository.findById(id);
        List<Address> address = addressService.findByUser(id);
        User user = obj.get();

        if(obj.isPresent()){
            user.setAddressList(address);
        }

        return user;
    }

    @Transactional
    public User insert(User obj) {
        obj.setId(null);
        obj = userRepository.save(obj);
        return obj;
    }

    public User fromDTO(UserDTO objDto) {
        User cli = new User(null, pe.encode(objDto.getPassword()), objDto.getEmail(), objDto.getName(), objDto.getRole(), objDto.getPhoneNumber());
        return cli;
    }

    public Page<User> getUsers(Pageable page) {
        return userRepository.findAll(page);
    }

}
