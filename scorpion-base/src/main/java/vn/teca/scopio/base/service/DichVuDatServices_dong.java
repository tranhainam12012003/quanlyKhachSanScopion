package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DichVu;
import vn.teca.scopio.base.model.DichVuDat;
import vn.teca.scopio.base.model.PhongDat;
import vn.teca.scopio.base.model.dto.DichVuDatAdd_dong;
import vn.teca.scopio.base.model.dto.DichVuDatDTO_dong;
import vn.teca.scopio.base.repository.DichVuDatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DichVuDatServices_dong {
    @Autowired
    DichVuDatRepository dichVuDatRepository;
    public void luu(DichVuDatAdd_dong dichVuDat) {
        PhongDat phongDat = new PhongDat();
        phongDat.setId(dichVuDat.getIdPhongDat());

        List<DichVu> dichVuList = dichVuDat.getDichVuList();
        for (DichVu dichVu : dichVuList) {
            DichVuDat dichVuDat1 = new DichVuDat();
            dichVuDat1.setSoLuong(dichVuDat.getSoLuong());
            dichVuDat1.setSoTien(dichVuDat.getSoTien());
            dichVuDat1.setPhongDatIdPhongDat(phongDat);
            dichVuDat1.setDichVuIdDichVu(dichVu);
            dichVuDatRepository.save(dichVuDat1);
        }
    }
   public void delete(Integer idDichvuDat){
        dichVuDatRepository.deleteById(idDichvuDat);
   }
    public void update(DichVuDatAdd_dong dichVuDat) {
//        Integer idDichVuDat = dichVuDat.getIdDichVuDat(); // Lấy idDichVuDat từ DichVuDatAdd_dong

        // Tìm đối tượng DichVuDat cần cập nhật trong cơ sở dữ liệu
        Optional<DichVuDat> optionalDichVuDat = dichVuDatRepository.findById(dichVuDat.getIdDichVuDat());
        if (optionalDichVuDat.isPresent()) {
            DichVuDat dichVuDat1 = optionalDichVuDat.get();

            // Cập nhật số lượng và giá tiền từ dichVuDat
            dichVuDat1.setSoLuong(dichVuDat.getSoLuong());
            dichVuDat1.setSoTien(dichVuDat.getSoTien());

            dichVuDatRepository.save(dichVuDat1);
        } else {
            // Xử lý khi không tìm thấy DichVuDat với idDichVuDat tương ứng
            // có thể throw một Exception hoặc xử lý theo cách khác tùy vào yêu cầu của bạn
            System.out.println("Không tìm thấy DichVuDat với idDichVuDat: " + dichVuDat.getIdDichVuDat());
        }
    }

}


