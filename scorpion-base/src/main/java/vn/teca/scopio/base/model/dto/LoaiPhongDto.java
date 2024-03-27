package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import vn.teca.scopio.base.model.HinhAnh;
import vn.teca.scopio.base.model.TienIch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link vn.teca.scopio.base.model.LoaiPhong}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoaiPhongDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(max = 200)
    private String tenLoaiPhong;
    @NotNull
    @Size(max = 200)
    private String huongNhin;
    @NotNull
    @Size(max = 10)
    private String dienTich;
    @NotNull
    private BigDecimal giaTien;
    @NotNull
    private String moTa;
    @NotNull
    private Integer soLuongNguoiO;
    private List<HinhAnh> hinhAnh;
    private List<TienIch> tienTienIch;
}