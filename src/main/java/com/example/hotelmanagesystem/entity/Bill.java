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
 * @since 2023-06-02
 */
@Getter
@Setter
@TableName("bill")
@ApiModel(value = "Bill对象", description = "")
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("billNo")
    @TableId(value = "billNo", type = IdType.INPUT)
    private String billNo;

    private String roomNo;

    private String memberNo;

    private Float consumeAmount;

    private LocalDate billTime;

    private String billStatus;


}
