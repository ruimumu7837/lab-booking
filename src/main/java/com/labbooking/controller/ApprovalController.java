package com.labbooking.controller;

import com.labbooking.dto.Result;
import com.labbooking.entity.Approval;
import com.labbooking.service.ApprovalService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/api/approval")
public class ApprovalController {

    @Resource
    private ApprovalService approvalService;

    @PostMapping
    public Result<?> approve(@RequestBody Approval approval, @RequestAttribute("userId") Long userId) {
        approval.setApproverId(userId);
        try {
            return Result.success(approvalService.approve(approval));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}