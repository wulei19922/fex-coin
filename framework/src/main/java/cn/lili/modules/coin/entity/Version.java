package cn.lili.modules.coin.entity;

import cn.lili.mybatis.BaseIdEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Chopper
 */
@Data
@TableName("coin_version")
@ApiModel(value = "公告数据")
@NoArgsConstructor
public class Version extends BaseIdEntity {

    private static final long serialVersionUID = 1L;


    @CreatedBy
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建者", hidden = true)
    private String createBy;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;


    @ApiModelProperty("versionCode")
    private Integer versionCode;

    @ApiModelProperty("versionName")
    private String versionName;

    @ApiModelProperty("versionInfo")
    private String versionInfo;

    @ApiModelProperty("updateType")
    private String updateType;

    @ApiModelProperty("downloadUrl")
    private String downloadUrl;

    @ApiModelProperty("androidUrl")
    private String androidUrl;

    @ApiModelProperty("iosUrl")
    private String iosUrl;





}