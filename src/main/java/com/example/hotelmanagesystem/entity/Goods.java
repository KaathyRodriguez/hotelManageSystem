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
@TableName("goods")
@ApiModel(value = "Goods对象", description = "")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("goodsNo")
    @TableId(value = "goodsNo", type = IdType.INPUT)
    private String goodsNo;

    private String goodsName;

    private String goodsType;

    private Integer goodsNum;

    private Float goodsCost;

    private Integer goodsLimit;


}
