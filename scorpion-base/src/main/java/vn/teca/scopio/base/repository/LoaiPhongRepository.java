package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.model.dto.LoaiPhongDto;
import vn.teca.scopio.base.repository.custom.LoaiPhongCustomRepository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LoaiPhongRepository extends JpaRepository<LoaiPhong, Integer>, LoaiPhongCustomRepository {
    List<LoaiPhong> findByTenLoaiPhong(String tenLoaiPhong);
    List<LoaiPhong>findByGiaTienLessThanEqualOrderByGiaTienDesc(BigDecimal sl);
    List<LoaiPhong>findBySoLuongNguoiOLessThanEqualOrderBySoLuongNguoiODesc(Integer sl);

//    @Query(value = "SELECT lp.ten_loai_phong, lp.huong_nhin, lp.dien_tich, lp.gia_tien, lp.mo_ta, ha.hinh_anh_loai_phong\n" +
//            "FROM loai_phong lp\n" +
//            "INNER JOIN hinh_anh ha ON lp.Id_loai_phong = ha.loai_phong_Id_loai_phong;", nativeQuery = true)
//    List<LoaiPhongDto>findAllAndHinhAnh();
}