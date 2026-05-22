package com.labbooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.labbooking.entity.Approval;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ApprovalMapper extends BaseMapper<Approval> {
    @Select("SELECT * FROM approval WHERE booking_id = #{bookingId}")
    Approval selectByBookingId(Long bookingId);
}