package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.LoaiDichVu;
import vn.teca.scopio.base.repository.LoaiDichVuRepository;

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
}
