package vn.teca.scopio.base.repository.custom;

import vn.teca.scopio.base.model.dto.DoanhThuDto_dong;

import java.util.List;

public interface ThongKeRepository_dong {
    List<DoanhThuDto_dong>getDoanhThuTheoNam();
    List<DoanhThuDto_dong>getDoanhThuTheoThang();
    List<DoanhThuDto_dong>getTongDoanhThu();
    List<DoanhThuDto_dong>getDoanhThuTheoTuan();
}
