package com.labbooking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("equipment")
public class Equipment {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String category;
    private String location;
    private String status;      // AVAILABLE, IN_USE, MAINTENANCE, SCRAPPED
    private String description;
    private LocalDateTime createdAt;
}