package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.repository.PhongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class phongServices {
    @Autowired
    PhongRepository phongRepository;

    public List<Phong> getall(){
        return phongRepository.findAll();
    }
// load danh sách phòng theo loại phòng ;
    public List<Phong> locLoaiPhong(Integer id){
      return phongRepository.loc(id);
    }
    public Phong them(Phong phong){
        return phongRepository.save(phong);
    }

    public Phong xoa(Integer id){
        Optional<Phong> optional=phongRepository.findById(id);
        return optional.map(o->{
            phongRepository.delete(o);
            return o;
        }).orElse(null);
    }
    public Phong update(Phong phong,Integer id){
        Optional<Phong>optional=phongRepository.findById(id);
        return optional.map(o -> {
            o.setSoPhong(phong.getSoPhong());
            o.setLoaiPhongIdLoaiPhong(phong.getLoaiPhongIdLoaiPhong());
            o.setTrangThai(phong.getTrangThai());
            o.setSoTang(phong.getSoTang());
         //   o.setPhongDats(phong.getPhongDats()); (cái này là khóa rì đây a thấy sql không có kết nối)
            return phongRepository.save(phong);
        }).orElse(null);
    }

    // detail cho phần chuyển màn
    public Phong findById(Integer id) {
        Optional<Phong> optional = phongRepository.findById(id);
        return optional.orElse(null);
    }


}
