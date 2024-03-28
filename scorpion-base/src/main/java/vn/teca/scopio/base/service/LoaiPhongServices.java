package vn.teca.scopio.base.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.model.TienIchLoaiPhong;
import vn.teca.scopio.base.model.dto.LoaiPhongDTOAdd;
import vn.teca.scopio.base.model.dto.LoaiPhongDto;
import vn.teca.scopio.base.repository.HinhAnhRepository;
import vn.teca.scopio.base.repository.LoaiPhongRepository;
import vn.teca.scopio.base.repository.PhongRepository;
import vn.teca.scopio.base.repository.TienIchLoaiPhongRepository;
import vn.teca.scopio.base.repository.TienIchRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class LoaiPhongServices {
    @Autowired
    LoaiPhongRepository loaiPhongRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    TienIchLoaiPhongRepository tienIchLoaiPhongRepository;

    @Autowired
    TienIchRepository tienIchRepository;

    @Autowired
    TienIchServices tienIchServices;
    @Autowired
    PhongRepository phongRepository;

    public List<LoaiPhong> getall() {
        return loaiPhongRepository.findAll();
    }

    //    public List<LoaiPhongDto>getAllHinhAnh(){return loaiPhongRepository.findAllAndHinhAnh();};
    public LoaiPhong delete(Integer id) {
        Optional<LoaiPhong> optional = loaiPhongRepository.findById(id);
        return optional.map(o -> {
            loaiPhongRepository.delete(o);
            return o;
        }).orElse(null);
    }

    public LoaiPhong findbyId(Integer id) {
        Optional<LoaiPhong> optional = loaiPhongRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public LoaiPhongDTOAdd mapToObject(Object[] result) {
        LoaiPhongDTOAdd loaiPhongDTOAdd = new LoaiPhongDTOAdd();
        loaiPhongDTOAdd.setTenLoaiPhong((String) result[0]);
        loaiPhongDTOAdd.setDienTich((String) result[1]);
        loaiPhongDTOAdd.setGiaTien((BigDecimal) result[2]);
        loaiPhongDTOAdd.setHuongNhin((String) result[3]);
        loaiPhongDTOAdd.setMoTa((String) result[4]);
        loaiPhongDTOAdd.setSoNguoi((Integer) result[5]);
        Phong phong = phongRepository.findById((Integer) result[6]).get();
        loaiPhongDTOAdd.setPhongidPhong(phong);
        TienIch tienIch = tienIchRepository.findById((Integer) result[7]).get();
        loaiPhongDTOAdd.setTienichidtienich(tienIch);
        return loaiPhongDTOAdd;

    }

    public List<LoaiPhongDTOAdd> detaiiByIdLoaiPhong(Integer id) {
        List<Object[]> results = loaiPhongRepository.detailLoaiPhong(id);
        List<LoaiPhongDTOAdd> list = new ArrayList<>();
        for (Object[] reObjects : results) {
            list.add(mapToObject(reObjects));
        }
        return list;
    }

    public void add(LoaiPhongDTOAdd loaiPhongDTOAdd) {
        LoaiPhong loaiPhong = new LoaiPhong();
        loaiPhong.setTenLoaiPhong(loaiPhongDTOAdd.getTenLoaiPhong());
        loaiPhong.setHuongNhin(loaiPhongDTOAdd.getHuongNhin());
        loaiPhong.setSoLuongNguoiO(loaiPhongDTOAdd.getSoNguoi());
        loaiPhong.setDienTich(loaiPhongDTOAdd.getDienTich());
        loaiPhong.setGiaTien(loaiPhongDTOAdd.getGiaTien());
        loaiPhong.setMoTa(loaiPhongDTOAdd.getMoTa());
        loaiPhongRepository.save(loaiPhong);

        Phong phong = phongRepository.getById(loaiPhongDTOAdd.getPhongidPhong().getId());
        phong.setLoaiPhongIdLoaiPhong(loaiPhong);
        phongRepository.save(phong);

        TienIchLoaiPhong tienIchLoaiPhong = new TienIchLoaiPhong();
        tienIchLoaiPhong.setLoaiPhong(loaiPhong);
        tienIchLoaiPhong.setTienIchIdTienIch(loaiPhongDTOAdd.getTienichidtienich());
        tienIchLoaiPhongRepository.save(tienIchLoaiPhong);
    }

    public void update(LoaiPhongDTOAdd loaiPhongDTOAdd, Integer id) {
        LoaiPhong loaiPhong= loaiPhongRepository.getById(id);
        loaiPhong.setTenLoaiPhong(loaiPhongDTOAdd.getTenLoaiPhong());
        loaiPhong.setDienTich(loaiPhongDTOAdd.getDienTich());
        loaiPhong.setTenLoaiPhong(loaiPhongDTOAdd.getTenLoaiPhong());
        loaiPhong.setMoTa(loaiPhongDTOAdd.getMoTa());
        loaiPhong.setHuongNhin(loaiPhongDTOAdd.getHuongNhin());
        loaiPhong.setSoLuongNguoiO(loaiPhongDTOAdd.getSoNguoi());

        Phong phong = phongRepository.getById(loaiPhongDTOAdd.getPhongidPhong().getId());
        phong.setLoaiPhongIdLoaiPhong(loaiPhong);
        phongRepository.save(phong);

        TienIchLoaiPhong tienIchLoaiPhong= tienIchLoaiPhongRepository.findTienIchLoaiPhongByLoaiPhong_Id(id);
        tienIchLoaiPhong.setLoaiPhong(loaiPhong);
        tienIchLoaiPhong.setTienIchIdTienIch(loaiPhongDTOAdd.getTienichidtienich());
        tienIchLoaiPhongRepository.save(tienIchLoaiPhong);
    }

    public List<LoaiPhong> timthemten(String name) {
        return loaiPhongRepository.findByTenLoaiPhong(name);

    }

    public List<LoaiPhong> timTheoGia(BigDecimal gia) {
        return loaiPhongRepository.findByGiaTienLessThanEqualOrderByGiaTienDesc(gia);
    }

    public List<LoaiPhong> timTheoSoNguoi(Integer sl) {
        return loaiPhongRepository.findBySoLuongNguoiOLessThanEqualOrderBySoLuongNguoiODesc(sl);
    }

    public List<LoaiPhongDto> findAllHinhAnh() {
        List<LoaiPhongDto> result = loaiPhongRepository.findAllHinhAnh();
//        List<LoaiPhongDto> newList = result;
        if (result != null) {
//            newList = result;
            result.forEach(p -> {
                p = getMoreInfro(p);
            });
//            return result;
        }
        return result;
    }

    public LoaiPhongDto getMoreInfro(LoaiPhongDto dto) {
        // set hinh anh
        try {
            dto.setHinhAnh(hinhAnhRepository.findByLoaiPhongIdLoaiPhong_Id(dto.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            dto.setHinhAnh(null);
        }
        // set tien ich
        try {
//            List<TienIch> tienI =
            dto.setTienTienIch(tienIchServices.getTienIchTheoID(dto.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            dto.setTienTienIch(null);
        }
        return dto;
    }

}
