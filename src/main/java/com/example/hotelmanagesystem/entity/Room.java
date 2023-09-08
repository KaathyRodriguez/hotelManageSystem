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
 * @since 2023-05-23
 */
@Getter
@Setter
@TableName("room")
@ApiModel(value = "Room对象", description = "")
public class Room implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty("roomNo")
    @TableId(value = "roomNo", type = IdType.INPUT)
    private String roomNo;

    @ApiModelProperty("roomType")
    private String roomType;

    @ApiModelProperty("roomStatus")
    private String roomStatus;

    @ApiModelProperty("roomPrice")
    private Float roomPrice;

    @ApiModelProperty("roomPosition")
    private String roomPosition;


}
