package vn.teca.scopio.base.repository.custom;

import vn.teca.scopio.base.model.dto.DoanhThuDto_dong;
import vn.teca.scopio.base.model.dto.DoanhThuNam_Thang_TuanDTO;
import vn.teca.scopio.base.model.dto.ThongKeTopLoaiPhongDTO_dong;

import java.util.List;

public interface ThongKeRepository_dong {
    List<DoanhThuNam_Thang_TuanDTO>getDoanhThuTheoNam();
    List<DoanhThuNam_Thang_TuanDTO>getDoanhThuTheoThang();
//    List<DoanhThuDto_dong>getTongDoanhThu();
    List<DoanhThuNam_Thang_TuanDTO>getDoanhThuTheoTuan();
    List<ThongKeTopLoaiPhongDTO_dong>getTopLoaiPhong();
}
