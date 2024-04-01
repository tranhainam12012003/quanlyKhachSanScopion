package vn.teca.scopio.base.repository.custom;

import vn.teca.scopio.base.model.dto.HoaDonResponseDto;

import java.util.List;

public interface HoaDonResponseRepository {
    List<HoaDonResponseDto> layHoaDonChiTiet(Integer id);
}
