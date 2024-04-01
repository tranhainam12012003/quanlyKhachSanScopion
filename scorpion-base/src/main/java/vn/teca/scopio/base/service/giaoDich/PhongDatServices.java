package vn.teca.scopio.base.service.giaoDich;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.PhongDat;
import vn.teca.scopio.base.model.dto.PhongDatDto;
import vn.teca.scopio.base.repository.PhongDatRepository;

import java.util.List;
import java.util.Optional;

//@Service
public interface PhongDatServices {
    PhongDat save(PhongDatDto dto);
    void checkin(Integer id);
    void update(PhongDatDto dto,Integer id);
    void checkout(Integer id);
    Integer countCheckout(Integer id);
    void checkoutDonDat(Integer id);
//    @Autowired
//    private PhongDatRepository phongDatRepository;
//    public List<PhongDat> getall(){
//        return phongDatRepository.findAll();
//    }
//    public PhongDat detail(Integer id) {
//        Optional<PhongDat> optional = phongDatRepository.findById(id);
//        return optional.orElse(null);
//    }
}
