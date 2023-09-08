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
 * @since 2023-05-31
 */
@Getter
@Setter
@TableName("supplyForm")
@ApiModel(value = "Supplyform对象", description = "")
public class Supplyform implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("supplyFormNo")
    @TableId(value = "supplyFormNo", type = IdType.INPUT)
    private String supplyFormNo;

    private String goodsNo;

    private LocalDate purchaseDate;

    private String purchaseManager;

    private Float purchaseAmount;

    private Float purchasePrice;


}
