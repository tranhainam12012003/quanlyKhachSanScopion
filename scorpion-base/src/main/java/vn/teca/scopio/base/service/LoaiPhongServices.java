package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.repository.LoaiPhongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiPhongServices {
    @Autowired
    LoaiPhongRepository loaiPhongRepository;
    public List<LoaiPhong>getall(){
        return loaiPhongRepository.findAll();
    }
    public  LoaiPhong delete(Integer id){
        Optional<LoaiPhong> optional=loaiPhongRepository.findById(id);
        return optional.map(o->{
            loaiPhongRepository.delete(o);
            return o;
        }).orElse(null);
    }
    public  LoaiPhong findbyId(Integer id){
         Optional<LoaiPhong>optional=loaiPhongRepository.findById(id);
         return optional.isPresent() ? optional.get() : null;
    }
    public LoaiPhong add(LoaiPhong lp){
        return loaiPhongRepository.save(lp);
    }
    public LoaiPhong update(LoaiPhong lp,Integer id){
        Optional<LoaiPhong>optional=loaiPhongRepository.findById(id);
        return optional.map(o -> {
            o.setTenLoaiPhong(lp.getTenLoaiPhong());
            o.setDienTich(lp.getDienTich());
            o.setHuongNhin(lp.getHuongNhin());
            o.setGiaTien(lp.getGiaTien());
            o.setMoTa(lp.getMoTa());
            o.setTrangThai(lp.getTrangThai());
            o.setSoLuongNguoiO(lp.getSoLuongNguoiO());
            return loaiPhongRepository.save(lp);
        }).orElse(null);
    }

}
