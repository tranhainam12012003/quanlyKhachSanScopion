package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.TienIch;

import java.util.List;

@Repository
public interface TienIchRepository extends JpaRepository<TienIch, Integer> {
    @Query(value = "SELECT ti.ten_tien_ich, ti.id_tien_ich FROM tien_ich ti INNER JOIN tien_ich_loai_phong tilp ON ti.id_tien_ich = tilp.tien_ich_id_tien_ich WHERE tilp.loai_phong_id_loai_phong = :idLoaiPhong", nativeQuery = true)
    List<Object[]> findTienIchByIdLoaiPhong(@Param("idLoaiPhong") Integer idLoaiPhong);
    


}

