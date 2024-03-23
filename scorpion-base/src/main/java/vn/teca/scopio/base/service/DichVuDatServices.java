package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DichVuDat;
import vn.teca.scopio.base.repository.DichVuDatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DichVuDatServices {
    @Autowired
    private DichVuDatRepository dichVuDatRepository;

    public List<DichVuDat> getall() {
        return dichVuDatRepository.findAll();

    }

    public DichVuDat add(DichVuDat dichVuDat) {
        return dichVuDatRepository.save(dichVuDat);
    }

    public DichVuDat update(DichVuDat dv, Integer id) {
        Optional<DichVuDat> optional = dichVuDatRepository.findById(id);
        return optional.map(o -> {
            o.setDichVuIdDichVu(dv.getDichVuIdDichVu());
            o.setSoLuong(dv.getSoLuong());
            o.setSoTien(dv.getSoTien());
            return dichVuDatRepository.save(o);
        }).orElse(null);
    }

    public DichVuDat detail(Integer id) {
        Optional<DichVuDat> optional = dichVuDatRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }
}
