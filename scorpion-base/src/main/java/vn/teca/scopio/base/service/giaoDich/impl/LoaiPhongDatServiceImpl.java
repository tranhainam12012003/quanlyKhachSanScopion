package vn.teca.scopio.base.service.giaoDich.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.repository.LoaiPhongDatRepository;
import vn.teca.scopio.base.service.giaoDich.LoaiPhongDatService;

@Service
public class LoaiPhongDatServiceImpl implements LoaiPhongDatService {
    @Autowired
    LoaiPhongDatRepository loaiPhongDatRepository;

    @Override
    public LoaiPhongDat save(LoaiPhongDat entity) {
        return loaiPhongDatRepository.save(entity);
    }

//    @Override
//    public Optional<LoaiPhongDat> findById(Integer integer) {
//        return loaiPhongDatRepository.findById(integer);
//    }

    @Override
    public void deleteById(Integer integer) {
        loaiPhongDatRepository.deleteById(integer);
    }
}
