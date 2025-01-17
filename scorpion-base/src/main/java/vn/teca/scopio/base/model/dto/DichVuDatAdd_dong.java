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
    private Integer idPhongDat;
    private List<DichVuDto_dong>dichVuList;
}
