package vn.teca.scopio.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.teca.scopio.base.model.DichVu;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DichVuDatAdd_dong {
    private Integer idDichVuDat;
    private int soLuong;
    private BigDecimal soTien;
    private Integer idPhongDat;
    private List<DichVu>dichVuList;
}
