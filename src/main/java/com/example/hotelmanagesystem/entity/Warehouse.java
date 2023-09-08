package com.example.hotelmanagesystem.entity;

import java.io.Serializable;

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
@TableName("warehouse")
@ApiModel(value = "Warehouse对象", description = "")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("warehouseNo")
    @TableId(value = "warehouseNo", type = IdType.INPUT)
    private String warehouseNo;

    private String warehouseName;

    private String warehouseManager;

    private String warehousePlace;

    private String storeGoodsList;

    private String warehouseState;

    private String warehouseSafeLevel;


}
