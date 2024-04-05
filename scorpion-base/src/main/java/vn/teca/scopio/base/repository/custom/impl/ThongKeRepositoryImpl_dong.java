package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.DoanhThuDto_dong;
import vn.teca.scopio.base.model.dto.KhachoDTO_Dong;
import vn.teca.scopio.base.repository.custom.ThongKeRepository_dong;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ThongKeRepositoryImpl_dong implements ThongKeRepository_dong {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<DoanhThuDto_dong> getDoanhThuTheoNam() {
        List<Object[]> getDoanhThu = entityManager.createNativeQuery("SELECT YEAR(phong_dat.thoi_gian_ra) AS Nam,\n" +
                        "       SUM(CASE WHEN hinh_thuc_dat.ten_hinh_thuc = 'online' THEN hoa_don_phong.tien_thanh_toan ELSE 0 END) AS Doanh_Thu_Online,\n" +
                        "       SUM(CASE WHEN hinh_thuc_dat.ten_hinh_thuc = 'offline' THEN hoa_don_phong.tien_thanh_toan ELSE 0 END) AS Doanh_Thu_Offline\n" +
                        "FROM hoa_don_phong \n" +
                        "JOIN phong_dat  ON hoa_don_phong.phong_dat_id_phong_dat = phong_dat.id_phong_dat join don_dat\n" +
                        "on don_dat.id_don_dat=phong_dat.don_dat_id_don_dat\n" +
                        "join hinh_thuc_dat on hinh_thuc_dat.id_hinh_thuc_dat=don_dat.hinh_thuc_dat_id_hinh_thuc_dat\n" +
                        "GROUP BY YEAR(phong_dat.thoi_gian_ra);")
                .getResultList();


        List<DoanhThuDto_dong> listDoanhThu = new ArrayList<>();

        // Process phongDaGanResults
        for (Object[] result : getDoanhThu) {
            DoanhThuDto_dong doanhThuDtoDong = new DoanhThuDto_dong();
            doanhThuDtoDong.setNam((Integer) result[0]);
            doanhThuDtoDong.setDoanhThuOnline((BigDecimal) result[1]);
            doanhThuDtoDong.setDoanhThuOffline((BigDecimal) result[2]);

            listDoanhThu.add(doanhThuDtoDong);
        }


        return listDoanhThu;

    }

    @Override
    public List<DoanhThuDto_dong> getDoanhThuTheoThang() {
        List<Object[]> getDoanhThu = entityManager.createNativeQuery("SELECT YEAR(phong_dat.thoi_gian_ra) AS Nam,\n" +
                        "       MONTH(phong_dat.thoi_gian_ra) AS Thang,\n" +
                        "       SUM(CASE WHEN hinh_thuc_dat.ten_hinh_thuc = 'online' THEN hoa_don_phong.tien_thanh_toan ELSE 0 END) AS Doanh_Thu_Online,\n" +
                        "       SUM(CASE WHEN hinh_thuc_dat.ten_hinh_thuc = 'offline' THEN hoa_don_phong.tien_thanh_toan ELSE 0 END) AS Doanh_Thu_Offline\n" +
                        "FROM hoa_don_phong \n" +
                        "JOIN phong_dat  ON hoa_don_phong.phong_dat_id_phong_dat = phong_dat.id_phong_dat \n" +
                        "JOIN don_dat ON don_dat.id_don_dat = phong_dat.don_dat_id_don_dat \n" +
                        "JOIN hinh_thuc_dat ON hinh_thuc_dat.id_hinh_thuc_dat = don_dat.hinh_thuc_dat_id_hinh_thuc_dat\n" +
                        "GROUP BY YEAR(phong_dat.thoi_gian_ra), MONTH(phong_dat.thoi_gian_ra);\n")
                .getResultList();


        List<DoanhThuDto_dong> listDoanhThu = new ArrayList<>();

        // Process phongDaGanResults
        for (Object[] result : getDoanhThu) {
            DoanhThuDto_dong doanhThuDtoDong = new DoanhThuDto_dong();
            doanhThuDtoDong.setNam((Integer) result[0]);
            doanhThuDtoDong.setThang((Integer) result[1]);
            doanhThuDtoDong.setDoanhThuOnline((BigDecimal) result[2]);
            doanhThuDtoDong.setDoanhThuOffline((BigDecimal) result[3]);

            listDoanhThu.add(doanhThuDtoDong);
        }


        return listDoanhThu;
    }

    @Override
    public List<DoanhThuDto_dong> getTongDoanhThu() {
        return null;
    }

    @Override
    public List<DoanhThuDto_dong> getDoanhThuTheoTuan() {
        List<Object[]> getDoanhThu = entityManager.createNativeQuery("SELECT YEAR(phong_dat.thoi_gian_ra) AS Nam,\n" +
                        "       DATEPART(ISO_WEEK, phong_dat.thoi_gian_ra) AS Tuan,\n" +
                        "       MONTH(phong_dat.thoi_gian_ra) AS Thang,\n" +
                        "       SUM(CASE WHEN hinh_thuc_dat.ten_hinh_thuc = 'online' THEN hoa_don_phong.tien_thanh_toan ELSE 0 END) AS Doanh_Thu_Online,\n" +
                        "       SUM(CASE WHEN hinh_thuc_dat.ten_hinh_thuc = 'offline' THEN hoa_don_phong.tien_thanh_toan ELSE 0 END) AS Doanh_Thu_Offline\n" +
                        "FROM hoa_don_phong \n" +
                        "JOIN phong_dat  ON hoa_don_phong.phong_dat_id_phong_dat = phong_dat.id_phong_dat \n" +
                        "JOIN don_dat ON don_dat.id_don_dat = phong_dat.don_dat_id_don_dat \n" +
                        "JOIN hinh_thuc_dat ON hinh_thuc_dat.id_hinh_thuc_dat = don_dat.hinh_thuc_dat_id_hinh_thuc_dat\n" +
                        "GROUP BY YEAR(phong_dat.thoi_gian_ra), DATEPART(ISO_WEEK, phong_dat.thoi_gian_ra), MONTH(phong_dat.thoi_gian_ra);\n")
                .getResultList();


        List<DoanhThuDto_dong> listDoanhThu = new ArrayList<>();

        // Process phongDaGanResults
        for (Object[] result : getDoanhThu) {
            DoanhThuDto_dong doanhThuDtoDong = new DoanhThuDto_dong();
            doanhThuDtoDong.setNam((Integer) result[0]);
            doanhThuDtoDong.setTuan((Integer) result[1]);
            doanhThuDtoDong.setThang((Integer) result[2]);
            doanhThuDtoDong.setDoanhThuOnline((BigDecimal) result[3]);
            doanhThuDtoDong.setDoanhThuOffline((BigDecimal) result[4]);

            listDoanhThu.add(doanhThuDtoDong);
        }


        return listDoanhThu;
    }

}
