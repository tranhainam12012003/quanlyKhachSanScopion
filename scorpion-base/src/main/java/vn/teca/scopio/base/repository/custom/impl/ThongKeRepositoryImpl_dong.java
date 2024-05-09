package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.DoanhThuDto_dong;
import vn.teca.scopio.base.model.dto.KhachoDTO_Dong;
import vn.teca.scopio.base.model.dto.ThongKeDto_dong;
import vn.teca.scopio.base.model.dto.ThongKeTopLoaiPhongDTO_dong;
import vn.teca.scopio.base.repository.custom.ThongKeRepository_dong;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Date;
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

    @Override
    public List<ThongKeTopLoaiPhongDTO_dong> getTopLoaiPhong() {
        List<Object[]> getTopLoaiPhong = entityManager.createNativeQuery("SELECT\n" +
                        "    lp.ten_loai_phong,\n" +
                        "    Count(lpd.loai_phong_Id_loai_phong) AS [Số lượng đặt],\n" +
                        "    MONTH(GETDATE()) AS 'thang'\n" +
                        "FROM\n" +
                        "    loai_phong lp\n" +
                        "LEFT JOIN\n" +
                        "    loai_phong_dat lpd ON lp.id_loai_phong = lpd.loai_phong_Id_loai_phong\n" +
                        "LEFT JOIN\n" +
                        "    don_dat dd ON lpd.don_dat_id_don_dat = dd.id_don_dat\n" +
                        "WHERE\n" +
                        "    MONTH(dd.thoi_gian_vao) = MONTH(GETDATE()) OR dd.thoi_gian_vao IS NULL\n" +
                        "GROUP BY \n" +
                        "    lp.ten_loai_phong,\n" +
                        "\tlpd.loai_phong_Id_loai_phong,\n" +
                        "    MONTH(dd.thoi_gian_vao)\n" +
                        "ORDER BY\n" +
                        "    [Số lượng đặt] DESC;")
                .getResultList();


        List<ThongKeTopLoaiPhongDTO_dong> listTop = new ArrayList<>();

        // Process phongDaGanResults
        for (Object[] result : getTopLoaiPhong) {
            ThongKeTopLoaiPhongDTO_dong thongKeTopLoaiPhongDTODong=new ThongKeTopLoaiPhongDTO_dong();
            thongKeTopLoaiPhongDTODong.setTenLoaiPhong((String) result[0]);
            thongKeTopLoaiPhongDTODong.setSoLuongDat((Integer) result[1]);
            thongKeTopLoaiPhongDTODong.setThang((Integer) result[2]);
            listTop.add(thongKeTopLoaiPhongDTODong);
        }


        return listTop;
    }

    public List<ThongKeDto_dong> thongKe() {
        List<Object[]> thongKeSoLuong = entityManager.createNativeQuery("SELECT \n" +
                        "    DATEADD(DAY, -n, CAST(GETDATE() AS DATE)) AS 'ngay',\n" +
                        "    (SELECT COUNT(*) FROM phong) AS 'tổng số phòng',\n" +
                        "    (SELECT COUNT(*) FROM phong_dat WHERE thoi_gian_vao <= DATEADD(DAY, -n, CAST(GETDATE() AS DATE)) AND thoi_gian_ra >= DATEADD(DAY, -n, CAST(GETDATE() AS DATE))) AS 'số phòng đã dùng',\n" +
                        "    (SELECT COUNT(*) FROM phong) - (SELECT COUNT(*) FROM phong_dat WHERE (thoi_gian_vao <= DATEADD(DAY, -n, CAST(GETDATE() AS DATE)) AND thoi_gian_ra >= DATEADD(DAY, -n, CAST(GETDATE() AS DATE))) OR (thoi_gian_vao < DATEADD(DAY, -n, CAST(GETDATE() AS DATE)) AND thoi_gian_ra > DATEADD(DAY, -n, CAST(GETDATE() AS DATE)))) AS 'phòng trống',\n" +
                        "    (SELECT COUNT(*) FROM thong_tin_khach_o ko left JOIN phong_dat pd ON ko.phong_dat_id_phong_dat = pd.id_phong_dat WHERE pd.thoi_gian_vao <= DATEADD(DAY, -n, CAST(GETDATE() AS DATE)) AND pd.thoi_gian_ra >= DATEADD(DAY, -n, CAST(GETDATE() AS DATE))) AS 'khách ở'\n" +
                        "FROM \n" +
                        "    (\n" +
                        "        SELECT 0 AS n UNION ALL\n" +
                        "        SELECT 1 UNION ALL\n" +
                        "        SELECT 2 UNION ALL\n" +
                        "        SELECT 3 UNION ALL\n" +
                        "        SELECT 4 UNION ALL\n" +
                        "        SELECT 5 UNION ALL\n" +
                        "        SELECT 6\n" +
                        "    ) AS days\n")
                .getResultList();


        List<ThongKeDto_dong> listDetail = new ArrayList<>();


        for (Object[] result : thongKeSoLuong) {
            ThongKeDto_dong thongKeDtoDong = new ThongKeDto_dong();
            thongKeDtoDong.setThoiGian((Date) result[0]);
            thongKeDtoDong.setTongSoPhong((Integer) result[1]);
            thongKeDtoDong.setSoLuongPhongDaDung((Integer) result[2]);
            thongKeDtoDong.setSoLuongPhongTrong((Integer) result[3]);
            thongKeDtoDong.setSoLuongNguoiO((int) result[4]);
            listDetail.add(thongKeDtoDong);
        }

        System.out.printf(listDetail.toString());
        return listDetail;

    }

}
