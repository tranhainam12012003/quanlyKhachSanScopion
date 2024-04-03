package vn.teca.scopio.base.repository.custom;

import vn.teca.scopio.base.model.dto.HinhAnhDto;
import vn.teca.scopio.base.model.dto.LoaiPhongDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LoaiPhongCustomRepository {
    List<LoaiPhongDto> findAllHinhAnh();
    List<LoaiPhongDto> searchLoaiPhongTrong(LocalDateTime thoiGianVao, LocalDateTime thoiGianRa);

//    List<HinhAnhDto> findByIdLoaiPhong(Integer id);
}
