package com.labbooking.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labbooking.entity.Approval;
import com.labbooking.entity.Booking;
import com.labbooking.mapper.ApprovalMapper;
import com.labbooking.mapper.BookingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class ApprovalService extends ServiceImpl<ApprovalMapper, Approval> {

    @Resource
    private BookingMapper bookingMapper;

    @Transactional
    public boolean approve(Approval approval) {
        Booking booking = bookingMapper.selectById(approval.getBookingId());
        if (booking == null || !"PENDING".equals(booking.getStatus()))
            throw new RuntimeException("预约状态不正确");
        booking.setStatus(approval.getResult());
        bookingMapper.updateById(booking);
        approval.setApprovedAt(LocalDateTime.now());
        return save(approval);
    }
}
