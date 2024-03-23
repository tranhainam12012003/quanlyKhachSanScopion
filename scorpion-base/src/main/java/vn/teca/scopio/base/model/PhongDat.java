package vn.teca.scopio.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import vn.teca.scopio.base.util.DateDeserializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "phong_dat")
public class PhongDat {
    @Id
    @Column(name = "id_phong_dat", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "phong_id_phong", nullable = false)
    private Phong phongIdPhong;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "don_dat_id_don_dat", nullable = false)
    private DonDat donDatIdDonDat;

    @NotNull
    @CreatedDate
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "thoi_gian_vao", nullable = false)
    private Instant thoiGianVao;

    @NotNull
    @LastModifiedDate
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "thoi_gian_ra", nullable = false)
    private Instant thoiGianRa;

}