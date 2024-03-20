package vn.teca.scopio.base.model.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String hoVaTen;
    private String soDienThoai;
    private String email;
    private boolean gioiTinh;
    private String password;
}
