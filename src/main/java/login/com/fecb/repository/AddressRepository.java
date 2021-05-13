package login.com.fecb.repository;

import login.com.fecb.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findById(long id);

    @Transactional(readOnly=true)
    @Query("SELECT obj FROM Address obj WHERE obj.user.id = :id")
    List<Address> findByUserId(@Param("id") Integer id);
}
