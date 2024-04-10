package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DichVu;
import vn.teca.scopio.base.repository.DichVuRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DichVuServices {
    @Autowired
    private DichVuRepository dichVuRepository;

    public List<DichVu> getall() {
        return dichVuRepository.findAll();
    }

    public DichVu add(DichVu dv) {
        return dichVuRepository.save(dv);
    }

    public DichVu detail(Integer id) {
        Optional<DichVu> optional = dichVuRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;

    }

    public DichVu update(DichVu dv, Integer id) {
        Optional<DichVu> optional = dichVuRepository.findById(id);
        return optional.map(o -> {
            o.setLoaiDichVuIdLoaiDichVu(dv.getLoaiDichVuIdLoaiDichVu());
            o.setGiaTien(dv.getGiaTien());
            o.setTenDichVu(dv.getTenDichVu());
            o.setTrangThai(dv.getTrangThai());
            return dichVuRepository.save(o);
        }).orElse(null);
    }

    public List<DichVu> timkiem(String ten) {
        return dichVuRepository.findByTenDichVuContaining(ten);
    }

    public List<DichVu> timkiemTheoId(Integer id) {
        return dichVuRepository.findByLoaiDichVuIdLoaiDichVu(id);
    }
}
