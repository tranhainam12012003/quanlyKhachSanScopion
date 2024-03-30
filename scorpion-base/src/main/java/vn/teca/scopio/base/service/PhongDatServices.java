package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.PhongDat;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.model.dto.PhongDTO;
import vn.teca.scopio.base.model.dto.PhongDatDto;
import vn.teca.scopio.base.repository.PhongDatRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhongDatServices {
    @Autowired
    private PhongDatRepository phongDatRepository;

    public List<PhongDat> getall() {
        return phongDatRepository.findAll();
    }

    public PhongDat detail(Integer id) {
        Optional<PhongDat> optional = phongDatRepository.findById(id);
        return optional.orElse(null);
    }

    private PhongDTO mapToObjectPhongGan(Object[] result) {
        PhongDTO phongDTO = new PhongDTO();
        phongDTO.setTenLoaiPhong((String) result[0]);
        phongDTO.setTenPhong((String) result[1]);
        phongDTO.setSoLuongPhong((int) result[2]);
        phongDTO.setIdPhong((int) result[3]);
        return phongDTO;
    }

    private PhongDatDto mapToObjectPhongDat(Object[] result) {
        PhongDatDto phongDatDto=new PhongDatDto();
        phongDatDto.setTenKhach((String) result[0]);
        phongDatDto.setTenLoaiPhong((String) result[1]);
        phongDatDto.setSoTienPhong((BigDecimal) result[2]);
        phongDatDto.setThoiGianVao((Timestamp) result[3]);
        phongDatDto.setThoiGianRa((Timestamp) result[4]);
        phongDatDto.setSoPhong((String) result[5]);
        return phongDatDto;
    }
    public List<PhongDatDto> getDetailPhongDatByIdPhong(Integer id) {
        List<Object[]> results = phongDatRepository.getThongTinPhongDatByIdPhong(id);
        List<PhongDatDto> phongDatDtos = new ArrayList<>();
        for (Object[] result : results) {
            phongDatDtos.add(mapToObjectPhongDat(result));
        }
        return phongDatDtos;
    }
    private PhongDTO mapToObjectPhongChuaGan(Object[] result) {
        PhongDTO phongDTO = new PhongDTO();
        phongDTO.setTenLoaiPhong((String) result[0]);
        phongDTO.setSoLuongPhong((int) result[1]);
        return phongDTO;
    }

    public List<PhongDTO> getPhongDaGan(Integer id) {
        List<Object[]> results = phongDatRepository.getPhongDaGan(id);
        List<PhongDTO> phongDTOS = new ArrayList<>();
        for (Object[] result : results) {
            phongDTOS.add(mapToObjectPhongGan(result));
        }
        return phongDTOS;
    }

    public List<PhongDTO> getPhongChuaGan() {
        List<Object[]> results = phongDatRepository.getPhongChuaGan();
        List<PhongDTO> phongDTOS = new ArrayList<>();
        for (Object[] result : results) {
            phongDTOS.add(mapToObjectPhongChuaGan(result));
        }
        return phongDTOS;
    }
}
