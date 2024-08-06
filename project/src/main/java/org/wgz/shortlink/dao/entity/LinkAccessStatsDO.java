package org.wgz.shortlink.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_link_access_stats
 */
@TableName(value ="t_link_access_stats")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkAccessStatsDO implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 日期
     */
    private Date date;

    /**
     * 访问量
     */
    private Integer pv;

    /**
     * 独立访问数
     */
    private Integer uv;

    /**
     * 独立IP数
     */
    private Integer uip;

    /**
     * 小时
     */
    private Integer hour;

    /**
     * 星期
     */
    private Integer weekday;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标识：0 未删除 1 已删除
     */
    @TableLogic
    private Integer delFlag;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LinkAccessStatsDO other = (LinkAccessStatsDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGid() == null ? other.getGid() == null : this.getGid().equals(other.getGid()))
            && (this.getFullShortUrl() == null ? other.getFullShortUrl() == null : this.getFullShortUrl().equals(other.getFullShortUrl()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getPv() == null ? other.getPv() == null : this.getPv().equals(other.getPv()))
            && (this.getUv() == null ? other.getUv() == null : this.getUv().equals(other.getUv()))
            && (this.getUip() == null ? other.getUip() == null : this.getUip().equals(other.getUip()))
            && (this.getHour() == null ? other.getHour() == null : this.getHour().equals(other.getHour()))
            && (this.getWeekday() == null ? other.getWeekday() == null : this.getWeekday().equals(other.getWeekday()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGid() == null) ? 0 : getGid().hashCode());
        result = prime * result + ((getFullShortUrl() == null) ? 0 : getFullShortUrl().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getPv() == null) ? 0 : getPv().hashCode());
        result = prime * result + ((getUv() == null) ? 0 : getUv().hashCode());
        result = prime * result + ((getUip() == null) ? 0 : getUip().hashCode());
        result = prime * result + ((getHour() == null) ? 0 : getHour().hashCode());
        result = prime * result + ((getWeekday() == null) ? 0 : getWeekday().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gid=").append(gid);
        sb.append(", fullShortUrl=").append(fullShortUrl);
        sb.append(", date=").append(date);
        sb.append(", pv=").append(pv);
        sb.append(", uv=").append(uv);
        sb.append(", uip=").append(uip);
        sb.append(", hour=").append(hour);
        sb.append(", weekday=").append(weekday);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}