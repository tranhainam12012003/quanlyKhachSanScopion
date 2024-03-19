package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "hinh_thuc_dat")
public class HinhThucDat {
    @Id
    @Column(name = "id_hinh_thuc_dat", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "ten_hinh_thuc", nullable = false, length = 200)
    private String tenHinhThuc;

}