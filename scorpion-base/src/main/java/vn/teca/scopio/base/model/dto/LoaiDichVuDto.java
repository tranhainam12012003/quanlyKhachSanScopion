package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.teca.scopio.base.model.DichVu;

import java.io.Serializable;
import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoaiDichVuDto implements Serializable {
    private String tenLoaiDichVu;
    private String tendichVu;
    private BigDecimal Gia;
    private DichVu dichVu;

}
