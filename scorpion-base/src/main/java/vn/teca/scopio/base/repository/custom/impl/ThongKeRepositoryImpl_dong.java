package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.DoanhThuDto_dong;
import vn.teca.scopio.base.model.dto.DoanhThuNam_Thang_TuanDTO;
import vn.teca.scopio.base.model.dto.KhachoDTO_Dong;
import vn.teca.scopio.base.model.dto.ThongKeDto_dong;
import vn.teca.scopio.base.model.dto.ThongKeTopLoaiPhongDTO_dong;
import vn.teca.scopio.base.repository.custom.ThongKeRepository_dong;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ThongKeRepositoryImpl_dong implements ThongKeRepository_dong {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<DoanhThuNam_Thang_TuanDTO> getDoanhThuTheoNam() {
        List<Object[]> getDoanhThu = entityManager.createNativeQuery("WITH AllMonths AS (\n" +
                        "    SELECT 1 AS MonthNumber\n" +
                        "    UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 \n" +
                        "    UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 \n" +
                        "    UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 \n" +
                        "    UNION ALL SELECT 11 UNION ALL SELECT 12\n" +
                        "),\n" +
                        "RevenuePerMonth AS (\n" +
                        "    SELECT \n" +
                        "        MONTH(pd.thoi_gian_ra) AS Thang,\n" +
                        "        SUM(hdp.tien_thanh_toan) AS TongDoanhThu\n" +
                        "    FROM \n" +
                        "        phong_dat pd\n" +
                        "    LEFT JOIN \n" +
                        "        hoa_don_phong hdp ON pd.id_phong_dat = hdp.phong_dat_id_phong_dat\n" +
                        "    WHERE \n" +
                        "        YEAR(pd.thoi_gian_ra) = YEAR(GETDATE())\n" +
                        "    GROUP BY \n" +
                        "        MONTH(pd.thoi_gian_ra)\n" +
                        ")\n" +
                        "SELECT \n" +
                        "    AllMonths.MonthNumber AS Thang,\n" +
                        "    ISNULL(RevenuePerMonth.TongDoanhThu, 0) AS TongDoanhThu\n" +
                        "FROM \n" +
                        "    AllMonths\n" +
                        "LEFT JOIN \n" +
                        "    RevenuePerMonth ON AllMonths.MonthNumber = RevenuePerMonth.Thang\n" +
                        "ORDER BY \n" +
                        "    AllMonths.MonthNumber;\n")
                .getResultList();
        Integer nam = (Integer) entityManager.createNativeQuery("SELECT YEAR(GETDATE()) AS CurrentYear;")
                .getSingleResult();

        List<DoanhThuNam_Thang_TuanDTO> listDoanhThu = new ArrayList<>();

        DoanhThuNam_Thang_TuanDTO doanhThuNamThangTuan = new DoanhThuNam_Thang_TuanDTO();
        doanhThuNamThangTuan.setNam(nam);
        BigDecimal tongTien = BigDecimal.ZERO; // Tổng tiền
        List<DoanhThuDto_dong> list = new ArrayList<>();
        for (Object[] reObjects : getDoanhThu) {
            DoanhThuDto_dong doanhThuDto = new DoanhThuDto_dong();
            doanhThuDto.setThoiGian(String.valueOf(BigInteger.valueOf((Integer) reObjects[0])));
            doanhThuDto.setTongDoanhThu((BigDecimal) reObjects[1]);
            tongTien = tongTien.add((BigDecimal) reObjects[1]); // Cộng vào tổng tiền
            list.add(doanhThuDto);

        }
        doanhThuNamThangTuan.setDoanhThu(list);
        doanhThuNamThangTuan.setTongDoanhThu(tongTien);
        listDoanhThu.add(doanhThuNamThangTuan);

        return listDoanhThu;
    }

    @Override
    public List<DoanhThuNam_Thang_TuanDTO> getDoanhThuTheoThang() {
        List<Object[]> getDoanhThu = entityManager.createNativeQuery("-- Lấy ra tháng hiện tại\n" +
                        "DECLARE @CurrentMonth INT = MONTH(GETDATE());\n" +
                        "\n" +
                        "-- Lấy ra năm hiện tại\n" +
                        "DECLARE @CurrentYear INT = YEAR(GETDATE());\n" +
                        "\n" +
                        "-- Tạo bảng số ngày trong tháng\n" +
                        "DECLARE @DaysInMonth TABLE (DayOfMonth DATE);\n" +
                        "\n" +
                        "DECLARE @FirstDayOfMonth DATE = DATEFROMPARTS(@CurrentYear, @CurrentMonth, 1);\n" +
                        "DECLARE @LastDayOfMonth DATE = EOMONTH(@FirstDayOfMonth);\n" +
                        "\n" +
                        "WHILE @FirstDayOfMonth <= @LastDayOfMonth\n" +
                        "BEGIN\n" +
                        "    INSERT INTO @DaysInMonth (DayOfMonth) VALUES (@FirstDayOfMonth);\n" +
                        "    SET @FirstDayOfMonth = DATEADD(DAY, 1, @FirstDayOfMonth);\n" +
                        "END\n" +
                        "\n" +
                        "-- Lấy ra tổng doanh thu của từng tuần trong tháng hiện tại\n" +
                        "SELECT \n" +
                        "    ROW_NUMBER() OVER (ORDER BY Weeks.WeekNumber) AS WeekNumber,\n" +
                        "    COALESCE(SUM(hdp.tien_thanh_toan), 0) AS TotalRevenue\n" +
                        "FROM \n" +
                        "    (\n" +
                        "        -- Tạo danh sách các tuần trong tháng\n" +
                        "        SELECT DISTINCT DATEPART(WEEK, di.DayOfMonth) AS WeekNumber\n" +
                        "        FROM @DaysInMonth di\n" +
                        "    ) AS Weeks\n" +
                        "LEFT JOIN \n" +
                        "    phong_dat pd ON MONTH(pd.thoi_gian_ra) = @CurrentMonth AND YEAR(pd.thoi_gian_ra) = @CurrentYear\n" +
                        "    AND DATEPART(WEEK, pd.thoi_gian_ra) = Weeks.WeekNumber\n" +
                        "LEFT JOIN \n" +
                        "    hoa_don_phong hdp ON pd.id_phong_dat = hdp.phong_dat_id_phong_dat\n" +
                        "GROUP BY \n" +
                        "    Weeks.WeekNumber\n" +
                        "ORDER BY \n" +
                        "    Weeks.WeekNumber;")
                .getResultList();
        Integer thang = (Integer) entityManager.createNativeQuery("SELECT MONTH(GETDATE()) AS CurrentMonth;\n")
                .getSingleResult();

        List<DoanhThuNam_Thang_TuanDTO> listDoanhThu = new ArrayList<>();
        BigDecimal tongTien = BigDecimal.ZERO; // Tổng tiền
        DoanhThuNam_Thang_TuanDTO doanhThuNamThangTuan = new DoanhThuNam_Thang_TuanDTO();
        doanhThuNamThangTuan.setThang(thang);
        List<DoanhThuDto_dong> list = new ArrayList<>();
        for (Object[] reObjects : getDoanhThu) {
            DoanhThuDto_dong doanhThuDto = new DoanhThuDto_dong();
            doanhThuDto.setThoiGian(String.valueOf((reObjects[0])));
            doanhThuDto.setTongDoanhThu((BigDecimal) reObjects[1]);
            tongTien = tongTien.add((BigDecimal) reObjects[1]); // Cộng vào tổng tiền
            list.add(doanhThuDto);
        }
        doanhThuNamThangTuan.setDoanhThu(list);
        doanhThuNamThangTuan.setTongDoanhThu(tongTien);
        listDoanhThu.add(doanhThuNamThangTuan);

        return listDoanhThu;
    }
//
//    @Override
//    public List<DoanhThuDto_dong> getTongDoanhThu() {
//        return null;
//    }
//
    @Override
    public List<DoanhThuNam_Thang_TuanDTO> getDoanhThuTheoTuan() {
        List<Object[]> getDoanhThu = entityManager.createNativeQuery("DECLARE @CurrentWeek INT = DATEPART(ISO_WEEK, GETDATE()); -- Lấy tuần hiện tại\\n" +
                        "\n" +
                        "-- Tạo bảng chứa tất cả các ngày trong tuần\n" +
                        "DECLARE @AllDaysInWeek TABLE (WeekDay INT);\n" +
                        "DECLARE @CurrentDate DATE = DATEADD(DAY, -DATEPART(WEEKDAY, GETDATE()) + 2, GETDATE()); -- Ngày đầu tiên là thứ Hai của tuần\n" +
                        "DECLARE @i INT = 1;\n" +
                        "\n" +
                        "WHILE @i <= 7\n" +
                        "BEGIN\n" +
                        "    INSERT INTO @AllDaysInWeek (WeekDay) VALUES (DATEPART(WEEKDAY, @CurrentDate));\n" +
                        "    SET @CurrentDate = DATEADD(DAY, 1, @CurrentDate);\n" +
                        "    SET @i = @i + 1;\n" +
                        "END\n" +
                        "\n" +
                        "-- Tính tổng doanh thu của các ngày trong tuần\n" +
                        "SELECT \n" +
                        "    CASE \n" +
                        "        WHEN ad.WeekDay = 1 THEN 'Sunday'\n" +
                        "        ELSE DATENAME(WEEKDAY, DATEADD(DAY, ad.WeekDay - 2, '1900-01-01'))\n" +
                        "    END AS WeekDay,\n" +
                        "    COALESCE(SUM(hdp.tien_thanh_toan), 0) AS TotalRevenue\n" +
                        "FROM \n" +
                        "    @AllDaysInWeek ad\n" +
                        "LEFT JOIN \n" +
                        "    phong_dat pd ON DATEPART(WEEK, pd.thoi_gian_ra) = @CurrentWeek AND ad.WeekDay = DATEPART(WEEKDAY, pd.thoi_gian_ra)\n" +
                        "LEFT JOIN \n" +
                        "    hoa_don_phong hdp ON pd.id_phong_dat = hdp.phong_dat_id_phong_dat\n" +
                        "GROUP BY \n" +
                        "    ad.WeekDay\n" +
                        "ORDER BY \n" +
                        "    ad.WeekDay;")
                .getResultList();
        Integer Tuan = (Integer) entityManager.createNativeQuery("SELECT DATEPART(ISO_WEEK, GETDATE()) AS WeekNumber;")
                .getSingleResult();

        List<DoanhThuNam_Thang_TuanDTO> listDoanhThu = new ArrayList<>();
        BigDecimal tongTien = BigDecimal.ZERO; // Tổng tiền
        DoanhThuNam_Thang_TuanDTO doanhThuNamThangTuan = new DoanhThuNam_Thang_TuanDTO();
        doanhThuNamThangTuan.setTuan(Tuan);
        List<DoanhThuDto_dong> list = new ArrayList<>();
        for (Object[] reObjects : getDoanhThu) {
            DoanhThuDto_dong doanhThuDto = new DoanhThuDto_dong();
            doanhThuDto.setThoiGian((String) reObjects[0]);
            doanhThuDto.setTongDoanhThu((BigDecimal) reObjects[1]);
            list.add(doanhThuDto);
            tongTien = tongTien.add((BigDecimal) reObjects[1]); // Cộng vào tổng tiền
        }
        doanhThuNamThangTuan.setDoanhThu(list);
        doanhThuNamThangTuan.setTongDoanhThu(tongTien);
        listDoanhThu.add(doanhThuNamThangTuan);

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
        double tongSoLuongDat = 0;

        // Tính tổng số lượng đặt
        for (Object[] result : getTopLoaiPhong) {
            double soLuongDat = ((Number) result[1]).doubleValue();
            tongSoLuongDat += soLuongDat;
        }
        // Process phongDaGanResults
        for (Object[] result : getTopLoaiPhong) {
            ThongKeTopLoaiPhongDTO_dong thongKeTopLoaiPhongDTODong=new ThongKeTopLoaiPhongDTO_dong();
            thongKeTopLoaiPhongDTODong.setTenLoaiPhong((String) result[0]);
            int soluong= (int) result[1];
            thongKeTopLoaiPhongDTODong.setSoLuongDat((Integer) result[1]);
            thongKeTopLoaiPhongDTODong.setThang((Integer) result[2]);
            double phantram = (soluong / tongSoLuongDat) * 100;
            DecimalFormat df = new DecimalFormat("#.##");
            phantram = Double.parseDouble(df.format(phantram));

            thongKeTopLoaiPhongDTODong.setPhanTram(phantram);

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
