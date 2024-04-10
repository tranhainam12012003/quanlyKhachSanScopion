package vn.teca.scopio.base.service;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import vn.teca.scopio.base.model.HinhAnh;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.repository.HinhAnhRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class HinhAnhServices {
    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    public List<HinhAnh> getall() {
        return hinhAnhRepository.findAll();
    }

    public HinhAnh add(Integer id, byte[] image) {
        HinhAnh hinhAnh = new HinhAnh();
        hinhAnh.setHinhAnhLoaiPhong(image);
        hinhAnh.setLoaiPhongIdLoaiPhong(LoaiPhong.builder().id(id).build());
        return hinhAnhRepository.save(hinhAnh);
    }

    public HinhAnh update(HinhAnh hinhAnh, Integer id) {
        Optional<HinhAnh> optional = hinhAnhRepository.findById(id);
        return optional.map(o -> {
            o.setHinhAnhLoaiPhong(hinhAnh.getHinhAnhLoaiPhong());
            return o;
        }).orElse(null);
    }

    public HinhAnh delete(Integer id) {
        Optional<HinhAnh> optional = hinhAnhRepository.findById(id);
        return optional.map(o -> {
            hinhAnhRepository.delete(o);
            return o;
        }).orElse(null);
    }

    public List detail(Integer id) {
        return hinhAnhRepository.findByLoaiPhongIdLoaiPhong_Id(id);
    }
    public List<String> getAllImagesAsBase64() {
        List<HinhAnh> images = hinhAnhRepository.findAll();
        return images.stream()
                .map(image -> Base64Utils.encodeToString(image.getHinhAnhLoaiPhong()))
                .collect(Collectors.toList());
    }

}
