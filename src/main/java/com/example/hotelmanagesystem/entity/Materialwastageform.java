package com.example.hotelmanagesystem.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Kevin
 * @since 2023-06-01
 */
@Getter
@Setter
@TableName("materialWastageForm")
@ApiModel(value = "Materialwastageform对象", description = "")
public class Materialwastageform implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("materialWastageFormNo")
    @TableId(value = "materialWastageFormNo", type = IdType.INPUT)
    private String materialWastageFormNo;

    private String lossGoods;

    private Integer lossQuantity;

    private LocalDate lossDate;

    private String lossType;

    private String note;


}
