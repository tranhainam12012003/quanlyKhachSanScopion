package vn.teca.scopio.base.repository.custom;

import vn.teca.scopio.base.model.dto.LoaiPhongDto;

import java.util.List;

public interface LoaiPhongCustomRepository {
    List<LoaiPhongDto> findAllHinhAnh();

}
