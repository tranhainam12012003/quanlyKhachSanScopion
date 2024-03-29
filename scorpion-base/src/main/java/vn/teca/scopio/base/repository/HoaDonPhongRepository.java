package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.HoaDonPhong;

import java.util.List;

@Repository
public interface HoaDonPhongRepository extends JpaRepository<HoaDonPhong, Integer> {
   @Query(value = "SELECT \n" +
           "    YEAR(phong_dat.thoi_gian_ra) AS năm,\n" +
           "    SUM(hoa_don_phong.tien_thanh_toan) AS 'tổng tiền',\n" +
           "    SUM(hoa_don_phong.tien_thanh_toan) AS 'tổng doanh thu'\n" +
           "FROM \n" +
           "    hoa_don_phong \n" +
           "JOIN \n" +
           "    phong_dat ON hoa_don_phong.phong_dat_id_phong_dat = phong_dat.id_phong_dat\n" +
           "GROUP BY \n" +
           "    YEAR(phong_dat.thoi_gian_ra)\n" +
           "WITH ROLLUP",nativeQuery = true)
    List<Object[]>doanhThuTheoNam();
}