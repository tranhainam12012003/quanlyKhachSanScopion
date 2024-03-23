package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.LoaiPhong;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LoaiPhongRepository extends JpaRepository<LoaiPhong, Integer> {
    List<LoaiPhong> findByTenLoaiPhong(String tenLoaiPhong);
    List<LoaiPhong>findByGiaTienLessThanEqualOrderByGiaTienDesc(BigDecimal sl);
    List<LoaiPhong>findBySoLuongNguoiOLessThanEqualOrderBySoLuongNguoiODesc(Integer sl);
}