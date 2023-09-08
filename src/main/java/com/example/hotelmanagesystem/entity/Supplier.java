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
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("supplier")
@ApiModel(value = "Supplier对象", description = "")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("supplierNo")
    @TableId(value = "supplierNo", type = IdType.INPUT)
    private String supplierNo;

    @ApiModelProperty("supplierName")
    private String supplierName;

    @ApiModelProperty("contactPersonName")
    private String contactPersonName;

    @ApiModelProperty("contactTelephone")
    private String contactTelephone;

    @ApiModelProperty("email")
    private String email;

    @ApiModelProperty("supplierPlace")
    private String supplierPlace;

    @ApiModelProperty("supplyGoodsList")
    private String supplyGoodsList;

    @ApiModelProperty("contractStartDate")
    private LocalDate contractStartDate;

    @ApiModelProperty("contractOverDate")
    private LocalDate contractOverDate;


}
