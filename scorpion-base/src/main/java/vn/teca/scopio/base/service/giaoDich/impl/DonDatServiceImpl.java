package vn.teca.scopio.base.service.giaoDich.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.repository.DonDatRepository;
import vn.teca.scopio.base.service.giaoDich.DonDatService;

import java.util.Optional;

@Service
public class DonDatServiceImpl implements DonDatService {
    @Autowired
    DonDatRepository donDatRepository;

    @Override
    public Page<DonDat> findAll(Pageable pageable) {

//        pageable = PageRequest.ofSize(20);
        return donDatRepository.findAll(pageable);
    }
    @Override
    public Optional<DonDat> findById(Integer id) {
        return donDatRepository.findById(id);
    }
    @Override
    public DonDat save(DonDat entity) {
        return donDatRepository.save(entity);
    }
    @Override
    public DonDat update(DonDat dt, Integer id){
        Optional<DonDat> donDat = donDatRepository.findById(id);
        return donDat.map(o -> {
//            o.setThongTinKhachDatIdKhachDat(dt.getThongTinKhachDatIdKhachDat());
//            o.setHinhThucDatIdHinhThucDat(dt.getHinhThucDatIdHinhThucDat());
            o.setTrangThai(dt.getTrangThai());
            return donDatRepository.save(dt);
        }).orElse(null);
    }


    @Override
    public void deleteById(Integer integer) {
        donDatRepository.deleteById(integer);
    }
}
