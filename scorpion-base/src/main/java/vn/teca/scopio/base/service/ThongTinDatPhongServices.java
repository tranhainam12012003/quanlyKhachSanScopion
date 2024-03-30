package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.model.dto.DTOThongTinLoaiPhong;
import vn.teca.scopio.base.repository.DonDatRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class ThongTinDatPhongServices {
    @Autowired
    DonDatRepository donDatRepository;
    private DTOThongTinLoaiPhong mapToObject(Object[] result) {
       DTOThongTinLoaiPhong dtoThongTinLoaiPhong=new DTOThongTinLoaiPhong();
        dtoThongTinLoaiPhong.setLoaiPhong((String) result[0]);
        dtoThongTinLoaiPhong.setSoLuong((Integer) result[1]);
        dtoThongTinLoaiPhong.setThoiGianVao((Timestamp) result[2]);
        dtoThongTinLoaiPhong.setThoiGianRa((Timestamp) result[3]);
        dtoThongTinLoaiPhong.setTienPhong((BigDecimal) result[4]);
        dtoThongTinLoaiPhong.setTen((String) result[5]);
        dtoThongTinLoaiPhong.setNgaySinh((Date) result[6]);
        dtoThongTinLoaiPhong.setSdt((String) result[7]);
        dtoThongTinLoaiPhong.setEmail((String) result[8]);
        dtoThongTinLoaiPhong.setQuocTich((String) result[9]);
        dtoThongTinLoaiPhong.setGioiTinh((Boolean) result[10]);
        dtoThongTinLoaiPhong.setDiaChi((String)result[11]);
        return dtoThongTinLoaiPhong;
    }
    public List<DTOThongTinLoaiPhong> getThongTInPhongDat(Integer id) {
        List<Object[]> results = donDatRepository.detailThongTinPhongDat(id);
        List<DTOThongTinLoaiPhong> dtoThongTinLoaiPhongs = new ArrayList<>();
        for (Object[] result : results) {
            dtoThongTinLoaiPhongs.add(mapToObject(result));
        }
        return dtoThongTinLoaiPhongs;
    }
}
