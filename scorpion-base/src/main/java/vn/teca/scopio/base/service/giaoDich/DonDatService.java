package vn.teca.scopio.base.service.giaoDich;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.teca.scopio.base.exception.ResourceNotFoundException;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.model.dto.DonDatDto;

import java.util.Optional;

public interface DonDatService {
    Page<DonDat> findAll(Pageable pageable);

    DonDat save(DonDat entity);

    DonDat findById(Integer integer);

    void deleteById(Integer integer);

    void luuDonDat(DonDatDto donDatDto);

    void update(DonDatDto donDatDto, Integer i);
//    DonDatDto createDonDat(DonDatDto donDatDTO);
//
//    //    public DonDat add(DonDat donDat,LoaiPhongDat loaiPhongDat){
//    //        loaiPhongDat.setDonDatIdDonDat(savedDonDat);
//    //        return donDatRepository.save(donDat) + loaiPhongDatRepository.save()
//    //
//    //    }
//    DonDatDto getDonDatById(Integer id) throws ResourceNotFoundException;
//
//    DonDatDto updateDonDat(Integer id, DonDatDto donDatDTO) throws ResourceNotFoundException;
//
//    void deleteDonDat(Integer id) throws ResourceNotFoundException;
//
////    Page<DonDat> findAll(Pageable pageable);
////
////    Optional<DonDat> findById(Integer id);
////
////    DonDat save(DonDat entity);
////
////    DonDat update(DonDat dt, Integer id);
////
////    void deleteById(Integer integer);
}
