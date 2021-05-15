package login.com.fecb.services.impl;

import login.com.fecb.domain.Address;
import login.com.fecb.domain.User;
import login.com.fecb.repository.UserRepository;
import login.com.fecb.security.UserSecurity;
import login.com.fecb.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private AddressService addressService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repo.findByEmail(email);
        List<Address> addressList = addressService.findByUser(user.getId());
        user.setAddressList(addressList);
        System.out.println(user);
        if(user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles(), user.getPhoneNumber(), user.getAddressList());
    }
}
