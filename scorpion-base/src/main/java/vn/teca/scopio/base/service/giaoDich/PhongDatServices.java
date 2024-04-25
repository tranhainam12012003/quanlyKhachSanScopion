package vn.teca.scopio.base.service.giaoDich;

import vn.teca.scopio.base.model.PhongDat;
import vn.teca.scopio.base.model.dto.LoadDonDatDto;
import vn.teca.scopio.base.model.dto.PhongDatDto;

import java.util.List;

//@Service
public interface PhongDatServices {
    PhongDat save(PhongDatDto dto);

    PhongDat doiPhong(PhongDatDto dto);

    List<LoadDonDatDto> getDonDatToChekIn(Integer idDonDat);

    void checkin(Integer id);
    void update(PhongDatDto dto);
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
