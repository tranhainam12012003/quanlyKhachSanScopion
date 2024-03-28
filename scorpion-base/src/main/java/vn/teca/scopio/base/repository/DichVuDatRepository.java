package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.DichVuDat;
import vn.teca.scopio.base.repository.custom.LoaiPhongCustomRepository;

import java.util.List;

@Repository
public interface DichVuDatRepository extends JpaRepository<DichVuDat, Integer> {
    @Query(value = "  select Sum(dvd.so_tien)as 'tong tien'from dich_vu_dat dvd " +
            "join phong_dat pd on dvd.phong_dat_id_phong_dat=pd.id_phong_dat" +
            " where pd.phong_id_phong=:idPhong",nativeQuery = true)
    List<Object[]>getPriceWithIdPhong(@Param("idPhong") Integer id);
}