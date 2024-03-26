package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "hinh_thuc_dat")
public class HinhThucDat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hinh_thuc_dat")
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "ten_hinh_thuc",length = 200)
    private String tenHinhThuc;

}