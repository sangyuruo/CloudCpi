package com.emcloud.cpi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 采集点状态信息表
 * @author Capejor
 */
@ApiModel(description = "采集点状态信息表 @author Capejor")
@Entity
@Table(name = "com_point_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Compointstatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 设备编码 外键
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "设备编码 外键", required = true)
    @Column(name = "com_point_code", length = 64, nullable = false)
    private String comPointCode;

    /**
     * 通讯状态
     */
    @NotNull
    @ApiModelProperty(value = "通讯状态", required = true)
    @Column(name = "communication_status", nullable = false)
    private Integer communicationStatus;

    /**
     * 记录时间
     */
    @NotNull
    @ApiModelProperty(value = "记录时间", required = true)
    @Column(name = "record_time", nullable = false)
    private Instant recordTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComPointCode() {
        return comPointCode;
    }

    public Compointstatus comPointCode(String comPointCode) {
        this.comPointCode = comPointCode;
        return this;
    }

    public void setComPointCode(String comPointCode) {
        this.comPointCode = comPointCode;
    }

    public Integer getCommunicationStatus() {
        return communicationStatus;
    }

    public Compointstatus communicationStatus(Integer communicationStatus) {
        this.communicationStatus = communicationStatus;
        return this;
    }

    public void setCommunicationStatus(Integer communicationStatus) {
        this.communicationStatus = communicationStatus;
    }

    public Instant getRecordTime() {
        return recordTime;
    }

    public Compointstatus recordTime(Instant recordTime) {
        this.recordTime = recordTime;
        return this;
    }

    public void setRecordTime(Instant recordTime) {
        this.recordTime = recordTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Compointstatus compointstatus = (Compointstatus) o;
        if (compointstatus.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), compointstatus.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Compointstatus{" +
            "id=" + getId() +
            ", comPointCode='" + getComPointCode() + "'" +
            ", communicationStatus='" + getCommunicationStatus() + "'" +
            ", recordTime='" + getRecordTime() + "'" +
            "}";
    }
}
