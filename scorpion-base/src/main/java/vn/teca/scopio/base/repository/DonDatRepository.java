package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.teca.scopio.base.model.DonDat;

public interface DonDatRepository extends JpaRepository<DonDat, Integer> {
}