package login.com.fecb.services.impl;

import login.com.fecb.domain.User;
import login.com.fecb.repository.UserRepository;
import login.com.fecb.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles());
    }
}
