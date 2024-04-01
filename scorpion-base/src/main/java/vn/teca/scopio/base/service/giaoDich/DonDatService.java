package vn.teca.scopio.base.service.giaoDich;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.teca.scopio.base.exception.ResourceNotFoundException;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO;
import vn.teca.scopio.base.model.dto.DonDatDto;
import vn.teca.scopio.base.model.dto.LoaiPhongDatDto;

import java.util.List;
import java.util.Optional;

public interface DonDatService {
    List<DonDat> findAllDonDat(int page);
    List<DonDat> findAllOnline(int page);
    List<DonDat> findAllOffline(int page);
    DonDat save(DonDat entity);

    DonDat findById(Integer integer);

    void deleteById(Integer integer);

    DonDat luuDonDat(DonDatDto donDatDto);

    void update(DonDatDto donDatDto, Integer i);
    DetailThongTinDonDatDTO mapToObject(Object[] result);
    List<DetailThongTinDonDatDTO> getThongTinDonDat(Integer id);
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
