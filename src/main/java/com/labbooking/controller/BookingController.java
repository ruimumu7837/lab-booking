package com.labbooking.controller;

import com.labbooking.dto.Result;
import com.labbooking.entity.Booking;
import com.labbooking.service.BookingService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Resource
    private BookingService bookingService;

    @PostMapping
    public Result<?> create(@RequestBody Booking booking, @RequestAttribute("userId") Long userId) {
        booking.setUserId(userId);
        try {
            return Result.success(bookingService.createBooking(booking));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/my")
    public Result<List<Booking>> myBookings(@RequestAttribute("userId") Long userId) {
        return Result.success(bookingService.list());
    }

    @PutMapping("/cancel/{id}")
    public Result<?> cancel(@PathVariable Long id, @RequestAttribute("userId") Long userId) {
        try {
            return Result.success(bookingService.cancelBooking(id, userId));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}