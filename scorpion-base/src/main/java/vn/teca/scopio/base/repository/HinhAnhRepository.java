package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.HinhAnh;
import vn.teca.scopio.base.model.dto.HinhAnhDto;

import java.util.List;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Integer> {
    List<HinhAnh>findByLoaiPhongIdLoaiPhong_Id(Integer id);

    @Query(value = "SELECT ha.hinh_anh_loai_phong as hinhAnhLoaiPhong\n" +
            "FROM hinh_anh ha\n" +
            "JOIN loai_phong lp ON ha.loai_phong_Id_loai_phong = lp.Id_loai_phong\n" +
            "WHERE lp.Id_loai_phong = :id ",nativeQuery = true)
    List<HinhAnhDto> findByIdLoaiPhong(@Param("id") Integer id);

}