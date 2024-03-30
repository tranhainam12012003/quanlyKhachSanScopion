package vn.teca.scopio.base.repository.custom;

import vn.teca.scopio.base.model.dto.PhongDto;

import java.util.List;

public interface PhongCustomRepository {
    List<PhongDto> getPhongCheckin();

    List<PhongDto> getPhongTrangThaiTrong();
}
