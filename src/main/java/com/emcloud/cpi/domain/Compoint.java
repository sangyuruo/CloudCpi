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
 * 采集点信息表
 *
 * @author Capejor
 */
@ApiModel(description = "采集点信息表 @author Capejor")
@Entity
@Table(name = "com_point")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Compoint implements Serializable {

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
     * 登记代码
     */
    @NotNull
    @ApiModelProperty(value = "登记代码", required = true)
    @Column(name = "register_code", nullable = false)
    private Integer registerCode;

    /**
     * 登记名称
     */
    @Size(max = 50)
    @ApiModelProperty(value = "登记名称")
    @Column(name = "register_name", length = 50)
    private String registerName;

    /**
     * 地址编码
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "地址编码", required = true)
    @Column(name = "address_code", length = 64, nullable = false)
    private String addressCode;

    /**
     * 组织编码
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "组织编码", required = true)
    @Column(name = "organization_code", length = 64, nullable = false)
    private String organizationCode;

    /**
     * 公司编码
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "公司编码", required = true)
    @Column(name = "company_code", length = 64, nullable = false)
    private String companyCode;

    /**
     * ip地址
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "ip地址", required = true)
    @Column(name = "ip", length = 20, nullable = false)
    private String ip;

    /**
     * 服务器名称
     */
    @NotNull
    @Size(max = 30)
    @ApiModelProperty(value = "服务器名称", required = true)
    @Column(name = "host_name", length = 30, nullable = false)
    private String hostName;

    /**
     * 服务器端口
     */
    @NotNull
    @ApiModelProperty(value = "服务器端口", required = true)
    @Column(name = "host_port", nullable = false)
    private Integer hostPort;

    /**
     * 请求超时时间
     */
    @NotNull
    @ApiModelProperty(value = "请求超时时间", required = true)
    @Column(name = "request_timeout", nullable = false)
    private Integer requestTimeout;

    /**
     * 响应超时时间
     */
    @NotNull
    @ApiModelProperty(value = "响应超时时间", required = true)
    @Column(name = "reply_timeout", nullable = false)
    private Integer replyTimeout;

    /**
     * 是否有效
     */
    @NotNull
    @ApiModelProperty(value = "是否有效", required = true)
    @Column(name = "jhi_enable", nullable = false)
    private Boolean enable;

    /**
     * 是否心跳
     */
    @NotNull
    @ApiModelProperty(value = "是否心跳", required = true)
    @Column(name = "keep_alive", nullable = false)
    private Boolean keepAlive;

    /**
     * 链接模式
     */
    @NotNull
    @ApiModelProperty(value = "链接模式", required = true)
    @Column(name = "connect_mode", nullable = false)
    private Integer connectMode;

    /**
     * 是否包装
     */
    @ApiModelProperty(value = "是否包装", required = true)
    @Column(name = "encapsulated", nullable = false)
    private Boolean encapsulated;
    /**
     * 创建人员
     */
    @Size(max = 20)
    @ApiModelProperty(value = "创建人员", required = true)
    @Column(name = "created_by", length = 20, nullable = false)
    private String createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    /**
     * 更新人员
     */
    @Size(max = 20)
    @ApiModelProperty(value = "更新人员", required = true)
    @Column(name = "updated_by", length = 20, nullable = false)
    private String updatedBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = true)
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    public Compoint encapsulated(Boolean encapsulated) {
        this.encapsulated = encapsulated;
        return this;
    }

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

    public Compoint comPointCode(String comPointCode) {
        this.comPointCode = comPointCode;
        return this;
    }

    public void setComPointCode(String comPointCode) {
        this.comPointCode = comPointCode;
    }

    public Integer getRegisterCode() {
        return registerCode;
    }

    public Compoint registerCode(Integer registerCode) {
        this.registerCode = registerCode;
        return this;
    }

    public void setRegisterCode(Integer registerCode) {
        this.registerCode = registerCode;
    }

    public String getRegisterName() {
        return registerName;
    }

    public Compoint registerName(String registerName) {
        this.registerName = registerName;
        return this;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public Compoint addressCode(String addressCode) {
        this.addressCode = addressCode;
        return this;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public Compoint organizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
        return this;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public Compoint companyCode(String companyCode) {
        this.companyCode = companyCode;
        return this;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getIp() {
        return ip;
    }

    public Compoint ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public Compoint hostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getHostPort() {
        return hostPort;
    }

    public Compoint hostPort(Integer hostPort) {
        this.hostPort = hostPort;
        return this;
    }

    public void setHostPort(Integer hostPort) {
        this.hostPort = hostPort;
    }

    public Integer getRequestTimeout() {
        return requestTimeout;
    }

    public Compoint requestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
        return this;
    }

    public void setRequestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public Integer getReplyTimeout() {
        return replyTimeout;
    }

    public Compoint replyTimeout(Integer replyTimeout) {
        this.replyTimeout = replyTimeout;
        return this;
    }

    public void setReplyTimeout(Integer replyTimeout) {
        this.replyTimeout = replyTimeout;
    }

    public Boolean isEnable() {
        return enable;
    }

    public Compoint enable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean isKeepAlive() {
        return keepAlive;
    }

    public Compoint keepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
        return this;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Integer getConnectMode() {
        return connectMode;
    }

    public Compoint connectMode(Integer connectMode) {
        this.connectMode = connectMode;
        return this;
    }

    public void setConnectMode(Integer connectMode) {
        this.connectMode = connectMode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Compoint createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public Compoint createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Compoint updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public Compoint updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
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
        Compoint compoint = (Compoint) o;
        if (compoint.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), compoint.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Compoint{" +
            "id=" + getId() +
            ", comPointCode='" + getComPointCode() + "'" +
            ", registerCode='" + getRegisterCode() + "'" +
            ", registerName='" + getRegisterName() + "'" +
            ", addressCode='" + getAddressCode() + "'" +
            ", organizationCode='" + getOrganizationCode() + "'" +
            ", companyCode='" + getCompanyCode() + "'" +
            ", ip='" + getIp() + "'" +
            ", hostName='" + getHostName() + "'" +
            ", hostPort='" + getHostPort() + "'" +
            ", requestTimeout='" + getRequestTimeout() + "'" +
            ", replyTimeout='" + getReplyTimeout() + "'" +
            ", enable='" + isEnable() + "'" +
            ", keepAlive='" + isKeepAlive() + "'" +
            ", connectMode='" + getConnectMode() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }

    public Boolean getEncapsulated() {
        return encapsulated;
    }

    public void setEncapsulated(Boolean encapsulated) {
        this.encapsulated = encapsulated;
    }
}
