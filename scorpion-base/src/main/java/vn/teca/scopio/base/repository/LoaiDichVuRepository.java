package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.LoaiDichVu;

import java.util.List;

@Repository
public interface LoaiDichVuRepository extends JpaRepository<LoaiDichVu, Integer> {
    List<LoaiDichVu>findByTenLoaiDichVuContaining(String ten);
    @Query(value = " select ldv.ten_loai_dich_vu,dv.ten_dich_vu,dv.gia_tien from \n" +
            "  loai_dich_vu ldv join dich_vu dv on ldv.id_loai_dich_vu=dv.loai_dich_vu_id_loai_dich_vu where ldv.id_loai_dich_vu=:idLoaiDichVu",nativeQuery = true)
    List<Object[]>findDichVuTheoID(@Param("idLoaiDichVu") Integer id);
}