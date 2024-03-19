package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.TienIchLoaiPhong;
import vn.teca.scopio.base.repository.TienIchLoaiPhongRepository;

import java.util.List;

@Service
public class TienIchLoaiPhongServices {
    @Autowired
    private TienIchLoaiPhongRepository loaiPhongRepository;
    public TienIchLoaiPhong add(TienIchLoaiPhong tienIchLoaiPhong){

        return loaiPhongRepository.save(tienIchLoaiPhong);
    }
    public List<TienIchLoaiPhong>getall(){
        return loaiPhongRepository.findAll();
    }
}
// ctrl +T