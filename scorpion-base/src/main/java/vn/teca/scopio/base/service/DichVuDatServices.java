package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DichVuDat;
import vn.teca.scopio.base.model.dto.DichVuDatDto;
import vn.teca.scopio.base.model.dto.LoaiDichVuDto;
import vn.teca.scopio.base.repository.DichVuDatRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
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


    public DichVuDat update(DichVuDat dv) {
        Optional<DichVuDat> optional = dichVuDatRepository.findById(dv.getId());
        return optional.map(o -> {
//            o.setDichVuIdDichVu(dv.getDichVuIdDichVu());
            o.setSoLuong(dv.getSoLuong());
            o.setSoTien(dv.getSoTien());
            return dichVuDatRepository.save(o);
        }).orElse(null);
    }

    public DichVuDat detail(Integer id) {
        Optional<DichVuDat> optional = dichVuDatRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }
    private DichVuDat mapToObject(Object[] result) {
        DichVuDat dichVuDat=new DichVuDat();
        dichVuDat.setSoTien((BigDecimal) result[0]);
        return dichVuDat;
    }

    public List<DichVuDat> getTonggTienTheoIdPhong(Integer id) {
        List<Object[]> results = dichVuDatRepository.getPriceWithIdPhong(id);
        List<DichVuDat> dichVuDats = new ArrayList<>();
        for (Object[] result : results) {
            dichVuDats.add(mapToObject(result));
        }
        return dichVuDats;
    }
    public List<DichVuDatDto> hienThiDichVuDat(Integer id){
        return dichVuDatRepository.hienThiDichVuDat(id);
    }
}
