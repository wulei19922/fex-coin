package cn.lili.modules.coin.entity.vos;

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
public class AddressVo  {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "地址ID", hidden = true)
    private String id;


    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;


    @ApiModelProperty("remark")
    private String remark;

    @ApiModelProperty("notice_title")
    private String noticeTitle;

    @ApiModelProperty("price")
    private Float price;


}