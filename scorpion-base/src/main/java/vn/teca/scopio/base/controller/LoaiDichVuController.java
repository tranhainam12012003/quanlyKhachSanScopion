package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.model.LoaiDichVu;
import vn.teca.scopio.base.model.dto.LoaiDichVuDto;
import vn.teca.scopio.base.service.LoaiDichVuSerices;

@RestController
@RequestMapping("/admin/loai-dich-vu/")
public class LoaiDichVuController {
    @Autowired
    private LoaiDichVuSerices loaiDichVuSerices;

    @GetMapping("hien-thi")
    public ResponseEntity<?> getall() {
        return ResponseEntity.ok(loaiDichVuSerices.getall());
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody LoaiDichVu LoaiDichVu) {
        try {

            loaiDichVuSerices.add(LoaiDichVu);
            return ResponseEntity.ok().body("add thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok().body("add khoong thanh cong");
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update( @RequestBody LoaiDichVu loaiDichVu) {
        try {
            loaiDichVuSerices.update(loaiDichVu);
         return  ResponseEntity.ok().body("sua thanh cong");

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok().body("sua khong thanh cong");
        }


    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(loaiDichVuSerices.delete(Integer.parseInt(id)));
    }

//    @GetMapping("detail/{id}")
//    public ResponseEntity<?> detail(@PathVariable String id) {
//        return ResponseEntity.ok(loaiDichVuSerices.detail(Integer.parseInt(id)));
//    }
    @GetMapping("tim-kiem/{ten}")
    public ResponseEntity<?>timKiem(@PathVariable String ten){
        return ResponseEntity.ok(loaiDichVuSerices.search(ten));
    }
    @GetMapping("tim-dich-vu/{id}")
    public ResponseEntity<?>findById(@PathVariable String id){
        return ResponseEntity.ok(loaiDichVuSerices.getDichVuTheoID(Integer.parseInt(id)));
    }
}
