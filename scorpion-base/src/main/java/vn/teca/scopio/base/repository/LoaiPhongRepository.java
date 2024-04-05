package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.model.dto.LoaiPhongDto;
import vn.teca.scopio.base.repository.custom.LoaiPhongCustomRepository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LoaiPhongRepository extends JpaRepository<LoaiPhong, Integer>, LoaiPhongCustomRepository {
    @Query(value = " select * from loai_phong where loai_phong.ten_loai_phong like CONCAT('%', :tenLoaiPhong, '%')",nativeQuery = true)
    List<LoaiPhong>timKiemTheoTenLoaiPhong(@Param("tenLoaiPhong") String tenLoaiPhong);
    List<LoaiPhong>findByGiaTienLessThanEqualOrderByGiaTienDesc(BigDecimal sl);
    List<LoaiPhong>findBySoLuongNguoiOLessThanEqualOrderBySoLuongNguoiODesc(Integer sl);

    @Query(value = "select lp.ten_loai_phong,lp.dien_tich," +
            "lp.gia_tien,lp.huong_nhin,lp.mo_ta,lp.so_luong_nguoi_o,p.id_phong,ti.id_tien_ich\n" +
            "from loai_phong lp join phong p on p.loai_phong_Id_loai_phong=lp.Id_loai_phong \n" +
            "join tien_ich_loai_phong tilp\n" +
            "on tilp.loai_phong_Id_loai_phong=lp.Id_loai_phong join tien_ich ti on \n" +
            "ti.id_tien_ich=tilp.tien_ich_id_tien_ich where lp.Id_loai_phong=:id",nativeQuery = true)
    List<Object[]> detailLoaiPhong(@Param("id") Integer idLoaiPhong);
}