package vn.teca.scopio.base.service.giaoDich;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.repository.DonDatRepository;

import java.util.Optional;

public interface DonDatService {

    Page<DonDat> findAll(Pageable pageable);

    Optional<DonDat> findById(Integer id);

    DonDat save(DonDat entity);

    DonDat update(DonDat dt, Integer id);

    void deleteById(Integer integer);
}
