package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.PhongDat;

import java.util.List;

@Repository
public interface PhongDatRepository extends JpaRepository<PhongDat, Integer> {
    List<PhongDat> findPhongDatByDonDatIdDonDat(Integer id);
}