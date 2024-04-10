package vn.teca.scopio.base.service;

import com.sun.org.apache.bcel.internal.generic.LUSHR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.dto.DoanhThuDto_dong;
import vn.teca.scopio.base.model.dto.ThongKeDto_dong;
import vn.teca.scopio.base.repository.custom.impl.ThongKeRepositoryImpl_dong;

import java.util.List;

@Service
public class ThongKeServices_dong {
    @Autowired
    ThongKeRepositoryImpl_dong thongKeRepositoryImplDong;

    public List<ThongKeDto_dong> getSoLuongPhongBang() {
        return thongKeRepositoryImplDong.thongKeSoLuongPhongBang();
    }

    public List<ThongKeDto_dong> getSoLuongPhongBieuDo() {
        return thongKeRepositoryImplDong.thongKeSoLuongPhongBieuDo();
    }

    public List<DoanhThuDto_dong> getDoanhThuTheoNam() {
        return thongKeRepositoryImplDong.getDoanhThuTheoNam();
    }

    public List<DoanhThuDto_dong> getDoanhThuTheoThang() {
        return thongKeRepositoryImplDong.getDoanhThuTheoThang();
    }

    public List<DoanhThuDto_dong> getDoanhThuTheoTuan() {
        return thongKeRepositoryImplDong.getDoanhThuTheoTuan();
    }
}
