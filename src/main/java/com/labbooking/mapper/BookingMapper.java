package com.labbooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.labbooking.entity.Booking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BookingMapper extends BaseMapper<Booking> {
    @Select("SELECT * FROM booking WHERE equipment_id = #{equipmentId} " +
            "AND status IN ('PENDING', 'APPROVED') " +
            "AND id != #{excludeId} " +
            "AND ((start_time <= #{end} AND end_time >= #{start}))")
    List<Booking> findConflictBookings(@Param("equipmentId") Long equipmentId,
                                        @Param("start") LocalDateTime start,
                                        @Param("end") LocalDateTime end,
                                        @Param("excludeId") Long excludeId);
}