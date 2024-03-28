package vn.teca.scopio.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.teca.scopio.base.model.DichVu;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoaiDichVuDto {
    private String TenLoaiDichVu;
    private String tendichVu;

    private BigDecimal Gia;

}
