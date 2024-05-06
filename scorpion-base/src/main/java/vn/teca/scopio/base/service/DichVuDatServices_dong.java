package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DichVu;
import vn.teca.scopio.base.model.DichVuDat;
import vn.teca.scopio.base.model.PhongDat;
import vn.teca.scopio.base.model.dto.DichVuDatAdd_dong;
import vn.teca.scopio.base.model.dto.DichVuDatDTO_dong;
import vn.teca.scopio.base.model.dto.DichVuDto_dong;
import vn.teca.scopio.base.repository.DichVuDatRepository;
import vn.teca.scopio.base.repository.DichVuRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DichVuDatServices_dong {
    @Autowired
    DichVuDatRepository dichVuDatRepository;
    @Autowired
    DichVuRepository dichVuRepository;

    public void luu(DichVuDatAdd_dong dichVuDat) {
        PhongDat phongDat = new PhongDat();
        phongDat.setId(dichVuDat.getIdPhongDat());

        List<DichVuDto_dong> dichVuList = dichVuDat.getDichVuList();
        for (DichVuDto_dong dichVuDto : dichVuList) {
            DichVuDat dichVuDat1 = new DichVuDat();
            dichVuDat1.setSoLuong(dichVuDto.getSoLuong());
            dichVuDat1.setPhongDatIdPhongDat(phongDat);

            DichVu dichVu = new DichVu();
            dichVu.setId(dichVuDto.getIdDichVu()); // Đặt ID cho đối tượng DichVu
            dichVuDat1.setDichVuIdDichVu(dichVu);
            BigDecimal getgiadichvu = dichVuRepository.getById(dichVu.getId()).getGiaTien();
                    BigDecimal soTien = getgiadichvu.multiply(BigDecimal.valueOf(dichVuDto.getSoLuong()));
            dichVuDat1.setSoTien(soTien); // Sửa thành getGiaTien()
            dichVuDatRepository.save(dichVuDat1);
        }
    }

    public void delete(Integer idDichvuDat) {
        dichVuDatRepository.deleteById(idDichvuDat);
    }

    public DichVuDat detail(Integer id) {
        return dichVuDatRepository.getDichVuDatDetail(id);

    }

    public DichVuDat update(DichVuDat dv) {
        Optional<DichVuDat> optional = dichVuDatRepository.findById(dv.getId());
        return optional.map(o -> {
//            o.setDichVuIdDichVu(dv.getDichVuIdDichVu());
            o.setSoLuong(dv.getSoLuong());
            o.setSoTien(dv.getSoTien());
            return dichVuDatRepository.save(o);
        }).orElse(null);
    }
}


