package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.service.ThongKeServices_dong;

@RestController
@RequestMapping("admin/thong-ke/")
public class ThongKeController_Dong {
    @Autowired
    ThongKeServices_dong thongKeServicesDong;

    @GetMapping("doanh-thu-theo-nam")
    public ResponseEntity<?> getDoanhThuTheoNam() {
        return ResponseEntity.ok(thongKeServicesDong.getDoanhThuTheoNam());
    }

    @GetMapping("doanh-thu-theo-thang")
    public ResponseEntity<?> getDoanhThuTheoThang() {
        return ResponseEntity.ok(thongKeServicesDong.getDoanhThuTheoThang());
    }

    @GetMapping("doanh-thu-theo-tuan")
    public ResponseEntity<?> getDoanhThuTheoTuan() {
        return ResponseEntity.ok(thongKeServicesDong.getDoanhThuTheoTuan());
    }


//    so luong phong bang: hien thi thoi gian checkin, ten loai phong,tong so luong phong ma loai phong ay co
//    so luong phong da dung,so luong phong chua dung
    @GetMapping("thong-ke-so-luong")
    public ResponseEntity<?> getSoLuongPhongBang() {
        return ResponseEntity.ok(thongKeServicesDong.getSoLuongPhong());
    }


    //số lượng phòng bieu do o day la hien thi ngay vao, tong so luong phong con trong cua ca hotel va tong
    //so luong phong con lai cua ca hotel

    @GetMapping("thong-ke-doanh-thu/{id}")//id o day la 1-tuan,2-thang,3-nam
    public ResponseEntity<?>getDoanhThu(@PathVariable String id){
        return ResponseEntity.ok(thongKeServicesDong.getDoanhThu(Integer.parseInt(id)));
    }
}
