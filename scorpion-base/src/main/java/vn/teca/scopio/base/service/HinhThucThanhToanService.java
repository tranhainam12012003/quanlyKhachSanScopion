package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.HinhThucThanhToan;
import vn.teca.scopio.base.repository.HinhThucThanhToanRepository;

import java.util.List;

@Service
public class HinhThucThanhToanService {
    @Autowired
    HinhThucThanhToanRepository hinhThucThanhToanRepository;

    public List<HinhThucThanhToan> getList(){
        return hinhThucThanhToanRepository.findAll();
    }
}
