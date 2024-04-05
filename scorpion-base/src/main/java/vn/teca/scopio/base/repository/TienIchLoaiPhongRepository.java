package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.TienIchLoaiPhong;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TienIchLoaiPhongRepository extends JpaRepository<TienIchLoaiPhong, Integer> {
    TienIchLoaiPhong findTienIchLoaiPhongByLoaiPhong_Id(Integer id);
    @Modifying
    @Query(value = " delete from tien_ich_loai_phong where loai_phong_Id_loai_phong=:idLoaiPhong",nativeQuery = true)
    void deleteTienIchLoaiPhong(@Param("idLoaiPhong") Integer idLoaiPhong);
}