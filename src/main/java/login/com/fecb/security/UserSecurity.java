package login.com.fecb.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import login.com.fecb.domain.Address;
import login.com.fecb.enums.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class UserSecurity implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String password;
    private long phoneNumber;
    private List<Address> addressList;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSecurity(Integer id, String email, String password, Set<Profile> profiles, long phoneNumber, List<Address> addressList){
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.addressList = addressList;
        this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(Profile profile) {
        return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));
    }

}
