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
@TableName("roomOrderForm")
@ApiModel(value = "Roomorderform对象", description = "")
public class Roomorderform implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("orderID")
    @TableId(value = "orderID", type = IdType.INPUT)
    private String orderID;

    private LocalDate orderTime;

    private String memberName;

    private String memberCardID;

    private String orderRoomNo;

    private String orderRoomType;

    private LocalDate orderComingTime;

    private Float orderAmount;

    private String orderStatus;


}
