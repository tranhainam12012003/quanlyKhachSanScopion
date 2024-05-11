package vn.teca.scopio.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoanKhachAddDto {
    private String hoTen;
    private String sdt;
    private String email;
    private Boolean gioiTinh;
    private String matKhau;

}
