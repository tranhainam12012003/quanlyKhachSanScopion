package vn.teca.scopio.base.service.giaoDich.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.model.dto.DonDatDto;
import vn.teca.scopio.base.model.dto.LoaiPhongDatDto;
import vn.teca.scopio.base.model.dto.LoaiPhongDto;
import vn.teca.scopio.base.repository.DonDatRepository;
import vn.teca.scopio.base.repository.LoaiPhongDatRepository;
import vn.teca.scopio.base.service.giaoDich.DonDatService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DonDatServiceImpl implements DonDatService {

    @Autowired
    private DonDatRepository donDatRepository;

    @Autowired
    private LoaiPhongDatRepository loaiPhongDatRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<DonDat> findAll(Pageable pageable) {
        pageable = Pageable.ofSize(15);
        return donDatRepository.findAll(pageable);
    }

    @Override
    public DonDat save(DonDat entity) {
        return donDatRepository.save(entity);
    }

    @Override
    public DonDat findById(Integer integer) {
        Optional<DonDat> optional = donDatRepository.findById(integer);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public void deleteById(Integer integer) {
        donDatRepository.deleteById(integer);
    }

    @Override
    public void luuDonDat(DonDatDto donDatDto) {
        DonDat donDat = new DonDat();
        donDat.setTongTien(donDatDto.getTongTien());
        donDat.setThoiGianVao(donDatDto.getThoiGianVao());
        donDat.setTrangThai(donDatDto.getTrangThai());
        donDat.setHinhThucDatIdHinhThucDat(donDatDto.getHinhThucDatIdHinhThucDat());
        donDat.setThongTinKhachDatIdKhachDat(donDatDto.getThongTinKhachDatIdKhachDat());
        donDat.setThoiGianRa(donDatDto.getThoiGianRa());
        donDatRepository.save(donDat);
//        DonDat donDat = new DonDat();
//        donDat = modelMapper.map(donDatDto, DonDat.class);
//        donDatRepository.save(donDat);
//        LoaiPhongDat loaiPhongDat = new LoaiPhongDat();
//        loaiPhongDat.setDonDatIdDonDat(donDat);
//        loaiPhongDat.setLoaiPhongIdLoaiPhong(donDatDto.getLoaiPhongIdLoaiPhong());
//        loaiPhongDat.setSoLuong(donDatDto.getSoLuong());
//        loaiPhongDatRepository.save(loaiPhongDat);

        List<LoaiPhongDat> loaiPhongDatList = new ArrayList<>();
         for(LoaiPhongDatDto loaiPhongDatDto : donDatDto.getLoaiPhongDatDto()){
             LoaiPhongDat loaiPhongDat = new LoaiPhongDat();
             loaiPhongDat.setDonDatIdDonDat(donDat);
             loaiPhongDat.setLoaiPhongIdLoaiPhong(loaiPhongDatDto.getLoaiPhongIdLoaiPhong());
             loaiPhongDat.setSoLuong(loaiPhongDatDto.getSoLuong());
             loaiPhongDatList.add(loaiPhongDat);
         }
         loaiPhongDatRepository.saveAll(loaiPhongDatList);
//         donDatDto.setLoaiPhongDatDto(loaiPhongDat);


//        LoaiPhongDat loaiPhongDat = modelMapper.map(donDatDto, LoaiPhongDat.class);
//        loaiPhongDat.setDonDatIdDonDat(donDat);
//        loaiPhongDatRepository.save(loaiPhongDat);
    }

    @Override
    public void update(DonDatDto donDatDto, Integer i){
        DonDat donDat = new DonDat();
        donDat.setId(donDat.getId());
        Optional<DonDat> optional = donDatRepository.findById(i);
        optional.map(o ->{
            o.setTrangThai(donDatDto.getTrangThai());
            return donDatRepository.save(o);
        }).orElse(null);
    }
}
