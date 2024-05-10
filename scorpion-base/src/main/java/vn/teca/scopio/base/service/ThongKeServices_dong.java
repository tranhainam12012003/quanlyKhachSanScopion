package vn.teca.scopio.base.service;

import com.sun.org.apache.bcel.internal.generic.LUSHR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.dto.DoanhThuDto_dong;
import vn.teca.scopio.base.model.dto.ThongKeDto_dong;
import vn.teca.scopio.base.model.dto.ThongKeTopLoaiPhongDTO_dong;
import vn.teca.scopio.base.repository.custom.impl.ThongKeRepositoryImpl_dong;

import java.util.List;

@Service
public class ThongKeServices_dong {
    @Autowired
    ThongKeRepositoryImpl_dong thongKeRepositoryImplDong;

    public List<ThongKeDto_dong> getSoLuongPhong() {
        return thongKeRepositoryImplDong.thongKe();
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

    public List<DoanhThuDto_dong>getDoanhThu(Integer id){
        if (id==1){
            return thongKeRepositoryImplDong.getDoanhThuTheoTuan();
        } else if (id==2) {
            return thongKeRepositoryImplDong.getDoanhThuTheoThang();
        } else if (id==3) {
            return thongKeRepositoryImplDong.getDoanhThuTheoNam();
        }
        return null;
    }
    public List<ThongKeTopLoaiPhongDTO_dong>getTopLoaiPhong(){
        return thongKeRepositoryImplDong.getTopLoaiPhong();
    }
}
