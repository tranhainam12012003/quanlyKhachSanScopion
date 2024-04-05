package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoanhThuDto_dong {
    private Integer tuan;
    private Integer nam;
    private Integer thang;
    private BigDecimal doanhThuOnline;
    private BigDecimal doanhThuOffline;
    private BigDecimal TongDoanhThu;

}
