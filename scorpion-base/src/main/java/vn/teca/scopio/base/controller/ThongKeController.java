package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RestController
@RequestMapping("/admin/doanh-thu/")
public class ThongKeController {
    @Autowired
    private EntityManager entityManager;

    @GetMapping("/nam")
    public ResponseEntity<List<Object[]>> getDoanhThuTheoNam() {
        String nativeQuery = "SELECT \n" +
                "    YEAR(phong_dat.thoi_gian_ra) AS năm,\n" +
                "    SUM(hoa_don_phong.tien_thanh_toan) AS'doanh thu'\n" +
                "FROM \n" +
                "    hoa_don_phong join phong_dat on hoa_don_phong.phong_dat_id_phong_dat=phong_dat.id_phong_dat\n" +
                "GROUP BY \n" +
                "    YEAR(phong_dat.thoi_gian_ra)\n" +
                "ORDER BY \n" +
                "    năm;";

        Query query = entityManager.createNativeQuery(nativeQuery);
        List<Object[]> results = query.getResultList();

        if (results != null && !results.isEmpty()) {
            return ResponseEntity.ok(results);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/thang")
    public ResponseEntity<List<Object[]>> getDoanhThuTheoThang() {
        String nativeQuery ="SELECT \n" +
                "    YEAR(phong_dat.thoi_gian_ra) AS năm,\n" +
                "    MONTH(phong_dat.thoi_gian_ra) AS tháng,\n" +
                "    SUM(hoa_don_phong.tien_thanh_toan) AS 'tông tiền'\n" +
                "FROM \n" +
                "    hoa_don_phong join phong_dat on hoa_don_phong.phong_dat_id_phong_dat=phong_dat.id_phong_dat\n" +
                "GROUP BY \n" +
                "    YEAR(don_dat.thoi_gian_ra), MONTH(don_dat.thoi_gian_ra)\n" +
                "ORDER BY \n" +
                "    năm, tháng;";

        Query query = entityManager.createNativeQuery(nativeQuery);
        List<Object[]> results = query.getResultList();

        if (results != null && !results.isEmpty()) {
            return ResponseEntity.ok(results);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
