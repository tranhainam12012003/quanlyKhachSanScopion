package vn.teca.scopio.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DichVuDatDTO_dong {
   private Integer idDichVuDat;
   private String tenDichVuDat;
   private BigDecimal giaDichVu;
   private int soLuong;


}
