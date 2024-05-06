package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.model.DichVuDat;
import vn.teca.scopio.base.model.dto.DichVuDatAdd_dong;
import vn.teca.scopio.base.model.dto.LoaiPhongDTOAdd;
import vn.teca.scopio.base.service.DichVuDatServices;
import vn.teca.scopio.base.service.DichVuDatServices_dong;

import java.util.List;

@RestController
@Controller
@RequestMapping("admin/dich-vu-dat/")
public class DichVuDatController_add_update_delete_dong {
    @Autowired
    DichVuDatServices_dong dichVuDatServicesDong;
    @Autowired
    DichVuDatServices dichVuDatServices;

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody DichVuDatAdd_dong dichVuDatAddDong) {
        try {
            dichVuDatServicesDong.luu(dichVuDatAddDong);
            return ResponseEntity.ok().body("luu thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("luu khong thanh cong");
        }
    }

//    @GetMapping("/detail/{idDichVuDat}")
//    public ResponseEntity<?> detail(@PathVariable String idDichVuDat) {
//        try {
//            int id = Integer.parseInt(idDichVuDat);
//            return ResponseEntity.ok(dichVuDatServicesDong.detail(id));
//        } catch (NumberFormatException e) {
//            // Xử lý khi id không hợp lệ
//            return ResponseEntity.badRequest().body("ID không hợp lệ");
//        }
//    }


    @DeleteMapping("delete/{idDichVuDat}")
    public ResponseEntity<?> delete(@PathVariable String idDichVuDat) {
        try {
            dichVuDatServicesDong.delete(Integer.parseInt(idDichVuDat));
            return ResponseEntity.ok().body("xóa thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("xóa không thành công");
        }
    }

    @PostMapping("/sua")
    public ResponseEntity<?> update( @RequestBody DichVuDat dichVuDat) {
//        dichVuDatAddDong.setIdDichVuDat(Integer.parseInt(id));
        try {
            dichVuDatServices.update(dichVuDat);
            return ResponseEntity.ok().body("sua thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("sua khong thanh cong");
        }

    }
}
