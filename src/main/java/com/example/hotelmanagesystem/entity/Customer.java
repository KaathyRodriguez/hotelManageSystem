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
 * @since 2023-05-31
 */
@Getter
@Setter
@TableName("customer")
@ApiModel(value = "Customer对象", description = "")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("memberNo")
    @TableId(value = "memberNo", type = IdType.INPUT)
    private String memberNo;

    private String memberCardID;

    private String memberName;

    private String memberSex;

    private String memberLevel;

    private Integer memberPoint;

    private String memberTelephone;



}
