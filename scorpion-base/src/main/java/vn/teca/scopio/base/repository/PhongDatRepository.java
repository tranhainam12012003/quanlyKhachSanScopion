package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.PhongDat;

import java.util.List;

@Repository
public interface PhongDatRepository extends JpaRepository<PhongDat, Integer> {
    @Query(value = "SELECT * FROM phong_dat pd join don_dat dd ON pd.don_dat_id_don_dat = dd.id_don_dat " +
            "WHERE dd.id_don_dat = :idDonDat ",nativeQuery = true)
    List<PhongDat> findPhongDatByIdDonDat(@Param("idDonDat") Integer idDonDat);
}