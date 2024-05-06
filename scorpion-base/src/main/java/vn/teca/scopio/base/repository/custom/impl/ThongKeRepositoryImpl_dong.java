package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.DoanhThuDto_dong;
import vn.teca.scopio.base.model.dto.KhachoDTO_Dong;
import vn.teca.scopio.base.model.dto.ThongKeDto_dong;
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
            BigDecimal tongDoanhThu = doanhThuDtoDong.getDoanhThuOnline().add(doanhThuDtoDong.getDoanhThuOffline());
            doanhThuDtoDong.setTongDoanhThu(tongDoanhThu);
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
            BigDecimal tongDoanhThu = doanhThuDtoDong.getDoanhThuOnline().add(doanhThuDtoDong.getDoanhThuOffline());
            doanhThuDtoDong.setTongDoanhThu(tongDoanhThu);
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
            BigDecimal tongDoanhThu = doanhThuDtoDong.getDoanhThuOnline().add(doanhThuDtoDong.getDoanhThuOffline());
            doanhThuDtoDong.setTongDoanhThu(tongDoanhThu);
            listDoanhThu.add(doanhThuDtoDong);
        }


        return listDoanhThu;
    }
    public List<ThongKeDto_dong> thongKeSoLuongPhongBang() {
        List<Object[]> thongKeSoLuongPhongBang = entityManager.createNativeQuery("SELECT \n" +
                        "    loai_phong.ten_loai_phong AS TenLoaiPhong,\n" +
                        "    (SELECT COUNT(*) FROM phong WHERE phong.loai_phong_Id_loai_phong = loai_phong.Id_loai_phong) AS TongSoPhong,\n" +
                        "    COUNT(phong_dat.id_phong_dat) AS SoLuongPhongDaDung,\n" +
                        "    (SELECT COUNT(*) FROM phong WHERE phong.loai_phong_Id_loai_phong = loai_phong.Id_loai_phong) - COUNT(phong_dat.id_phong_dat) AS SoLuongPhongTrong,\n" +
                        "    phong_dat.thoi_gian_vao AS ThoiGianVao\n" +
                        "FROM \n" +
                        "    loai_phong\n" +
                        "LEFT JOIN \n" +
                        "    phong ON loai_phong.Id_loai_phong = phong.loai_phong_Id_loai_phong\n" +
                        "LEFT JOIN\n" +
                        "    phong_dat ON phong.id_phong = phong_dat.phong_id_phong\n" +
                        "WHERE\n" +
                        "    phong_dat.thoi_gian_vao IS NOT NULL\n" +
                        "GROUP BY \n" +
                        "    loai_phong.Id_loai_phong, loai_phong.ten_loai_phong, phong_dat.thoi_gian_vao;\n")
                .getResultList();


        List<ThongKeDto_dong> listDetail = new ArrayList<>();


        for (Object[] result : thongKeSoLuongPhongBang) {
            ThongKeDto_dong thongKeDtoDong = new ThongKeDto_dong();
            thongKeDtoDong.setTenLoaiPhong((String) result[0]);
            thongKeDtoDong.setTongSoPhong((Integer) result[1]);
            thongKeDtoDong.setSoLuongPhongDaDung((Integer) result[2]);
            thongKeDtoDong.setSoLuongPhongTrong((Integer) result[3]);
            thongKeDtoDong.setThoiGian((Timestamp) result[4]);
            listDetail.add(thongKeDtoDong);
        }

        System.out.printf(listDetail.toString());
        return listDetail;

    }
    public List<ThongKeDto_dong> thongKeSoLuongPhongBieuDo() {
        List<Object[]> thongKeSoLuongPhongBang = entityManager.createNativeQuery("SELECT\n" +
                        "    thoi_gian_vao,\n" +
                        "    SUM(SoLuongPhongDaDung) AS TongSoLuongPhongDaDung,\n" +
                        "    SUM(TongSoPhong - SoLuongPhongDaDung) AS TongSoLuongPhongChuaSuDung\n" +
                        "FROM (\n" +
                        "    SELECT \n" +
                        "        phong_dat.thoi_gian_vao,\n" +
                        "        COUNT(phong_dat.id_phong_dat) AS SoLuongPhongDaDung,\n" +
                        "        (SELECT COUNT(*) FROM phong) AS TongSoPhong\n" +
                        "    FROM \n" +
                        "        phong_dat\n" +
                        "    GROUP BY \n" +
                        "        phong_dat.thoi_gian_vao\n" +
                        ") AS PhongDaDung\n" +
                        "GROUP BY\n" +
                        "    thoi_gian_vao;")
                .getResultList();


        List<ThongKeDto_dong> listDetail = new ArrayList<>();


        for (Object[] result : thongKeSoLuongPhongBang) {
            ThongKeDto_dong thongKeDtoDong = new ThongKeDto_dong();
            thongKeDtoDong.setThoiGian((Timestamp) result[0]);
            thongKeDtoDong.setSoLuongPhongDaDung((Integer) result[1]);
            thongKeDtoDong.setSoLuongPhongTrong((Integer) result[2]);
            listDetail.add(thongKeDtoDong);
        }

        System.out.printf(listDetail.toString());
        return listDetail;

    }
}
