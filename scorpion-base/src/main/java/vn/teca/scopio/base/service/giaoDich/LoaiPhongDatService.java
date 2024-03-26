package vn.teca.scopio.base.service.giaoDich;

import vn.teca.scopio.base.model.LoaiPhongDat;

public interface LoaiPhongDatService {
    LoaiPhongDat save(LoaiPhongDat entity);

//    Optional<LoaiPhongDat> findById(Integer integer);

    void deleteById(Integer integer);
}
