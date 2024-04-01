package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class HoaDonRequestDto {
    private Integer phong_dat_id_phong_dat;
    private BigDecimal tienPhong;
    private BigDecimal tienDichVu;

    private BigDecimal tienDaThanhToan;

}