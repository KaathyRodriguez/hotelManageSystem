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
@TableName("Checkinform")
@ApiModel(value = "Checkinform对象", description = "")
public class Checkinform implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("checkInFormNo")
    @TableId(value = "checkInFormNo", type = IdType.INPUT)
    private String checkInFormNo;

    private String memberNo;

    private String memberCardID;

    private String roomNo;

    private Integer livingPersonNum;

    private LocalDate comingTime;

    private LocalDate leaveTime;

    private Integer roomAmount;

    private String isFirst;

    private String state;


}
