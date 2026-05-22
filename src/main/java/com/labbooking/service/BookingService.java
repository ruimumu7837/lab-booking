package com.labbooking.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labbooking.entity.Booking;
import com.labbooking.mapper.BookingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService extends ServiceImpl<BookingMapper, Booking> {

    @Resource
    private BookingMapper bookingMapper;

    @Transactional
    public boolean createBooking(Booking booking) {
        List<Booking> conflicts = bookingMapper.findConflictBookings(
            booking.getEquipmentId(), booking.getStartTime(), booking.getEndTime(), -1L);
        if (!conflicts.isEmpty()) throw new RuntimeException("该时间段已被预约");
        if (booking.getStartTime().isBefore(LocalDateTime.now()))
            throw new RuntimeException("预约开始时间不能早于当前时间");
        booking.setStatus("PENDING");
        return save(booking);
    }

    @Transactional
    public boolean cancelBooking(Long bookingId, Long userId) {
        Booking booking = getById(bookingId);
        if (booking == null || !booking.getUserId().equals(userId))
            throw new RuntimeException("无权操作");
        if (!"PENDING".equals(booking.getStatus()) && !"APPROVED".equals(booking.getStatus()))
            throw new RuntimeException("当前状态不可取消");
        booking.setStatus("CANCELLED");
        return updateById(booking);
    }
}