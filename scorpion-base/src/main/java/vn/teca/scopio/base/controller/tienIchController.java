package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.service.TienIchServices;

import java.util.List;

@Controller
@RequestMapping("/admin/tien-ich")
public class tienIchController {
    @Autowired
    public TienIchServices tienIchServices;

    @GetMapping("/hien-thi")
    public ResponseEntity<?> getall() {
        return ResponseEntity.ok(tienIchServices.getall());
    }
    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody TienIch tienIch){
        return ResponseEntity.ok(tienIchServices.add(tienIch));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable String id ){
       return ResponseEntity.ok(tienIchServices.delete(Integer.parseInt(id)));
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?>detailwithID(@PathVariable String id){
       List<TienIch> tienIchList = tienIchServices.getTienIchTheoID(Integer.parseInt(id));
        return ResponseEntity.ok(tienIchList);
    }
    @GetMapping("tim-theo-ten/{ten}")
    public  ResponseEntity<?>timTheoTen(@PathVariable("ten")String ten){
        return ResponseEntity.ok(tienIchServices.getTienIchTheoTen(ten));
    }
}
