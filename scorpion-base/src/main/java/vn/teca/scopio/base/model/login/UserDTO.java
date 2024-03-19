package vn.teca.scopio.base.model.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    private Integer activated;
    private String langKey;
    private String createdBy;
    private Instant createdDate;
    private String lastModifiedBy;
    private Instant lastModifiedDate;
    private String maDonVi;
    private String fullName;
    private Long birthday;
    private Integer gender;
    private String phoneNumber;
    private String department;
    private String position;
    private String addresss;
    private Long vaiTro;
    private String ticket;
    private Long type;
    private String serialKey;
    private String sessionId;
    private String tenDonVi;
    private String soDienThoai;
    private String maChucVu;
    private String tenChucVu;
    private String maPhongBan;
    private Long trangThai;
    private String tenPhongBan;
    private Pageable pageable;
    private Long typeUser;
    private String tenHt;
    private Long selected;
    private Long menuId;
    private List<Long> dsMenu;
    private String tenNhom;
    private Long isAdmin;
    private String jwtAuth;
    private Boolean isChoosen;
    private String maDangKy;
    private String maCuocKt;
    private Long isDoiPass;
    private String activationKey;
    private String resetKey;
    private Instant resetDate;
    private Long dangKyId;
    private String nguoiTao;
    private String hoten;
    private Long idOrder;
    private String tokenName;
    private String tokenTu;
    private String tokenDen;
    private String tokenIssue;
    private String tokenSerial;
    private Integer trangThaiKhoa;
    private String loginCu;
    private Instant ngayDoiPass;

    private UserInfoDto userInfoDto;
    private String userLevel;

    private Boolean deploy;
    private Set<GrantedAuthority> authorities;

    public UserDTO(UserInfoDto userInfoDto) {
        this.login = userInfoDto.getUserCode();
        this.type = 1L;
        this.fullName = userInfoDto.getUserName();
        this.email = userInfoDto.getEmail();
        this.userLevel = userInfoDto.getUserLevel();
        this.phoneNumber = userInfoDto.getMobile();
        this.position = userInfoDto.getPosition();
        this.soDienThoai = userInfoDto.getMobile();
        this.maDonVi = userInfoDto.getSpecUnitCode();
        this.tenDonVi = userInfoDto.getSpecUnitName();
        this.maPhongBan = userInfoDto.getAreaCode();
        this.tenPhongBan = userInfoDto.getAreaName();
        this.maChucVu = userInfoDto.getJobCode();
        this.tenChucVu = userInfoDto.getJobName();
        if (CollectionUtils.isNotEmpty(userInfoDto.getAuthorities())) {
            Set<GrantedAuthority> privileges = userInfoDto.getAuthorities().stream()
                    .filter(StringUtils::isNotBlank)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
            this.authorities = privileges;
        }
    }

    public UserDTO(UserDTO userInfoDto) {
        this.login = userInfoDto.getLogin();
        this.type = 1L;
        this.fullName = userInfoDto.getFullName();
        this.email = userInfoDto.getEmail();
        this.userLevel = userInfoDto.getUserLevel();
        this.phoneNumber = userInfoDto.getPhoneNumber();
        this.position = userInfoDto.getPosition();
        this.soDienThoai = userInfoDto.getSoDienThoai();
        this.maDonVi = userInfoDto.getMaDonVi();
        this.tenDonVi = userInfoDto.getTenDonVi();
        this.maPhongBan = userInfoDto.getMaPhongBan();
        this.tenPhongBan = userInfoDto.getTenPhongBan();
        this.maChucVu = userInfoDto.getMaChucVu();
        this.tenChucVu = userInfoDto.getTenChucVu();
    }

    public UserInfoDto getUserInfoDto() {
        if (this.userInfoDto == null || StringUtils.isBlank(this.userInfoDto.getUserCode())) {
            UserInfoDto dto = UserInfoDto.builder()
                    .userCode(this.login)
                    .userName(this.fullName)
                    .email(this.email)
                    .userLevel(this.userLevel)
                    .mobile(this.phoneNumber)
                    .position(this.position)
                    .specUnitCode(this.maDonVi)
                    .specUnitName(this.tenDonVi)
                    .areaCode(this.maPhongBan)
                    .areaName(this.tenPhongBan)
                    .jobCode(this.maChucVu)
                    .jobName(this.tenChucVu)
                    .build();

            if (CollectionUtils.isNotEmpty(this.authorities)) {
                List<String> privileges = this.authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());
                dto.setAuthorities(new HashSet<>(privileges));
            }
            return dto;
        }
        return this.userInfoDto;
    }
}
