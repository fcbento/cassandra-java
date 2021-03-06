package login.com.fecb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import login.com.fecb.enums.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name = "USR_SRVC")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    private String password;

    private String name;

    @Column(unique = true)
    private String email;

    private Integer role;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Address> addressList = new ArrayList<>();

    private long phoneNumber;

    @NotNull
    private Date createdAt;

    public User(){

    }

    public User(Integer id, String password, String email, String name, Integer role, long phoneNumber) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        addProfile();
    }

    public void addProfile() {
        profiles.add(role);
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }
}
