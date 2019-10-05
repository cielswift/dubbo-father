package com.ciel.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class BasePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.UUID)
    private String id;

    @TableField(value = "CREATE_DATE",fill= FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    @TableField(value = "UPDATE_DATE",fill= FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;

    @TableLogic
    @TableField(value = "DELETED",fill= FieldFill.INSERT)
    private Integer deleted;

}
