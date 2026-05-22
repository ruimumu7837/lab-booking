package com.labbooking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("approval")
public class Approval {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long bookingId;
    private Long approverId;
    private String result;
    private String comment;
    private LocalDateTime approvedAt;
}