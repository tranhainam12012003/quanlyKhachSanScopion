package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.teca.scopio.base.model.LoaiPhongDat;

import java.util.List;

public interface LoaiPhongDatRepository extends JpaRepository<LoaiPhongDat, Integer> {

    List<LoaiPhongDat> findByLoaiPhongIdLoaiPhong_Id(Integer id);

    @Query(value = "SELECT * FROM loai_phong_dat lpd join don_dat dd ON lpd.don_dat_id_don_dat = dd.id_don_dat " +
            "WHERE dd.id_don_dat = :idDonDat  ", nativeQuery = true)
    LoaiPhongDat findByIdDonDat(@Param("idDonDat") Integer idDonDat);
}