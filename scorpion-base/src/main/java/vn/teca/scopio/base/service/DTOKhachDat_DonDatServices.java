package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DTOThongtinKhachDat_DonDat;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.repository.DonDatRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DTOKhachDat_DonDatServices {
    @Autowired
    private DonDatRepository donDatRepository;

    public List<DTOThongtinKhachDat_DonDat> getThongTin() {
        List<DonDat> donDats = donDatRepository.findAll();
        List<DTOThongtinKhachDat_DonDat> dto = donDats.stream().map(DTOThongtinKhachDat_DonDat::new).collect(Collectors.toList());
        return dto;
    }
}
