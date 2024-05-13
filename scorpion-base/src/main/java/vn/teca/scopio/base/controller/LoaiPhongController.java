package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.model.dto.LoaiPhongDTOAdd;
import vn.teca.scopio.base.repository.HinhAnhRepository;
import vn.teca.scopio.base.service.LoaiPhongServices;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RestController
@RequestMapping("/admin/loai-phong")
public class LoaiPhongController {
    @Autowired
    LoaiPhongServices loaiPhongServices;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @GetMapping("/list")
    public ResponseEntity<?> getall() {
        return ResponseEntity.ok(loaiPhongServices.getall());
    }

    // hien thi tat ca hinh anh + loai phong + tien ich
    @GetMapping("/all")
    public ResponseEntity<?> getAllList(){
            return ResponseEntity.ok(loaiPhongServices.findAllHinhAnh());
    }


    // tim kiem so luong con trong cua loai phong
   @GetMapping("/search")
   public ResponseEntity<?> searchLoaiPhongTrong(@RequestParam(required = false) String thoiGianVao, @RequestParam(required = false) String thoiGianRa){
//       String str = "1986-04-08 12:30";
//       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//       LocalDateTime vao = LocalDateTime.parse(thoiGianVao, formatter);
//       LocalDateTime ra = LocalDateTime.parse(thoiGianRa, formatter);
       if(thoiGianVao == null || thoiGianRa == null){
           return ResponseEntity.ok(loaiPhongServices.findAllHinhAnh());
       }
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
       LocalDateTime vao = LocalDateTime.parse(thoiGianVao, formatter);
       LocalDateTime ra = LocalDateTime.parse(thoiGianRa, formatter);

        return ResponseEntity.ok(loaiPhongServices.searchLoaiPhongTrong(vao,ra));
   }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            hinhAnhRepository.deleteByIdLoaiPhong(Integer.parseInt(id));
            loaiPhongServices.delete(Integer.parseInt(id));
            return ResponseEntity.ok().body("Xóa thành công");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Xóa không thành công! Vui lòng chuyển các phòng sang loại phòng khác");

        }

    }

    @PostMapping("/sua")
    public ResponseEntity<?> update( @RequestBody LoaiPhongDTOAdd loaiPhongDTOAdd) {
       try {
           loaiPhongServices.update(loaiPhongDTOAdd);
           return ResponseEntity.ok().body("sua thanh cong");
       }catch(Exception e){
           e.printStackTrace();
           return ResponseEntity.badRequest().body("luu khong thanh cong");
       }

    }

    @PostMapping("/luu")
    public ResponseEntity<?> add(@RequestBody LoaiPhongDTOAdd loaiPhongDTOAdd) {


        try{
            loaiPhongServices.add(loaiPhongDTOAdd);
            return ResponseEntity.ok().body("luu thanh cong");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("luu khong thanh cong");
        }

    }

    @GetMapping("/tim-theo-ten/{ten}")
    public ResponseEntity<?>timtheoten(@PathVariable String ten){
        return ResponseEntity.ok(loaiPhongServices.timthemten(ten));
    }

    @GetMapping("tim-kiem-theo-gia/{gia}")
    public ResponseEntity<?>timtheogia(@PathVariable String gia){
        return ResponseEntity.ok(loaiPhongServices.timTheoGia(BigDecimal.valueOf(Long.parseLong(gia))));
    }
    @GetMapping("tim-kiem-theo-so-luong/{sl}")
    public ResponseEntity<?>timTheoSoLuong(@PathVariable String sl){
        return ResponseEntity.ok(loaiPhongServices.timTheoSoNguoi(Integer.parseInt(sl)));
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?>detailByIdLoaiPhong(@PathVariable String id){
        return ResponseEntity.ok(loaiPhongServices.detaiiByIdLoaiPhong(Integer.parseInt(id)));
    }
}
