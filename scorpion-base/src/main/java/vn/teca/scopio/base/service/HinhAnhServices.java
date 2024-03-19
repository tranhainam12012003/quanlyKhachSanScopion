package vn.teca.scopio.base.service;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.teca.scopio.base.model.HinhAnh;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.repository.HinhAnhRepository;
import vn.teca.scopio.base.util.ImageUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Builder
@Service
public class HinhAnhServices {
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    public List<HinhAnh>getall(){
        return  hinhAnhRepository.findAll();
    }
    public HinhAnh add(HinhAnh hinhAnh){

        return hinhAnhRepository.save(hinhAnh);
    }
    public HinhAnh update(HinhAnh hinhAnh,Integer id){
        Optional<HinhAnh>optional=hinhAnhRepository.findById(id);
        return optional.map(o->{
            o.setHinhAnhLoaiPhong(hinhAnh.getHinhAnhLoaiPhong());
            return o;
        }).orElse(null);
    }
    public HinhAnh delete(Integer id){
        Optional<HinhAnh>optional=hinhAnhRepository.findById(id);
        return optional.map(o->{
            hinhAnhRepository.delete(o);
            return o;
        }).orElse(null);
    }
    public HinhAnh detail(Integer id){
        return hinhAnhRepository.detail(id);
    }
    public String uploadImage(MultipartFile file,Integer id) throws IOException {

        HinhAnh imageData = hinhAnhRepository.save(HinhAnh.builder()

                .hinhAnhLoaiPhong(ImageUtil.compressImage(file.getBytes())).loaiPhongIdLoaiPhong(LoaiPhong.builder().id(id).build()).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

}
