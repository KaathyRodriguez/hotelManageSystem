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
@TableName("cateringOrderForm")
@ApiModel(value = "Cateringorderform对象", description = "")
public class Cateringorderform implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("orderNo")
    @TableId(value = "orderNo", type = IdType.INPUT)
    private String orderNo;

    private String memberNo;

    private LocalDate purchaseDate;

    private String orderType;

    private Integer orderDeposit;

    private String orderDescription;

    private String orderStatus;

    private String note;


}
