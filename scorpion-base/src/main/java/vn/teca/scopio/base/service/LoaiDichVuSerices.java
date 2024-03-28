package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.LoaiDichVu;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.model.dto.LoaiDichVuDto;
import vn.teca.scopio.base.repository.LoaiDichVuRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoaiDichVuSerices {
    @Autowired
    private LoaiDichVuRepository loaiDichVuRepository;

    public List<LoaiDichVu> getall() {
        return loaiDichVuRepository.findAll();
    }

    public LoaiDichVu add(LoaiDichVu loaiDichVu) {
        return loaiDichVuRepository.save(loaiDichVu);
    }

    public LoaiDichVu detail(Integer id) {
        Optional<LoaiDichVu> optional = loaiDichVuRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public LoaiDichVu update(LoaiDichVu loaiDichVu, Integer id) {
        Optional<LoaiDichVu> optional = loaiDichVuRepository.findById(id);
        return optional.map(o -> {
            o.setTenLoaiDichVu(loaiDichVu.getTenLoaiDichVu());
            return loaiDichVuRepository.save(o);
        }).orElse(null);
    }
    public LoaiDichVu delete(Integer id){

        Optional<LoaiDichVu> optional=loaiDichVuRepository.findById(id);
        return optional.map(o->{
            loaiDichVuRepository.delete(o);
            return o;
        }).orElse(null);

    }
    public List<LoaiDichVu> search(String ten){
        return loaiDichVuRepository.findByTenLoaiDichVu(ten);
    }
    private LoaiDichVuDto mapToObject(Object[] result) {
        LoaiDichVuDto loaiDichVuDto=new LoaiDichVuDto();
        loaiDichVuDto.setTenLoaiDichVu((String) result[0]);
        loaiDichVuDto.setTendichVu((String) result[1]);
        loaiDichVuDto.setGia((BigDecimal) result[2]);
        return loaiDichVuDto;
    }
    public List<LoaiDichVuDto> getDichVuTheoID(Integer id) {
        List<Object[]> results =loaiDichVuRepository.findDichVuTheoID(id);
        List<LoaiDichVuDto> loaiDichVuDtos = new ArrayList<>();
        for (Object[] result : results) {
            loaiDichVuDtos.add(mapToObject(result));
        }
        return loaiDichVuDtos;
    }
}
