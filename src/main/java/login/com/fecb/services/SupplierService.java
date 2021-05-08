package login.com.fecb.services;

import com.datastax.oss.driver.api.core.paging.OffsetPager;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import login.com.fecb.domain.Supplier;
import login.com.fecb.repository.SupplierRepository;
import login.com.fecb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Supplier findById(UUID id) {

        Optional<Supplier> obj = supplierRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Not found! Id: " + id + ", Type: " + Supplier.class.getName()));
    }

    public Supplier create(Supplier supplier) {
        supplier.setId(Uuids.timeBased());
        return supplierRepository.save(supplier);
    }

    public Slice<Supplier> getAll(Pageable page){
        Pageable pageRequest = CassandraPageRequest.of(page.getPageNumber(), page.getPageSize());
        return supplierRepository.findAll(pageRequest);
    }

    public Supplier update(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void delete(UUID id) {
        supplierRepository.deleteById(id);
    }

    public List<Supplier> list() {
        return supplierRepository.findAll();
    }

}
