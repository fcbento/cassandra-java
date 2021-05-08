package login.com.fecb.repository;

import login.com.fecb.domain.Supplier;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SupplierRepository extends CassandraRepository<Supplier, UUID> {
}
