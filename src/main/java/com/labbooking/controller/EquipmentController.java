package com.labbooking.controller;

import com.labbooking.dto.Result;
import com.labbooking.entity.Equipment;
import com.labbooking.service.EquipmentService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Resource
    private EquipmentService equipmentService;

    @GetMapping("/list")
    public Result<List<Equipment>> list() {
        return Result.success(equipmentService.list());
    }

    @PostMapping
    public Result<?> save(@RequestBody Equipment equipment) {
        return Result.success(equipmentService.saveOrUpdate(equipment));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        return Result.success(equipmentService.removeById(id));
    }

    @GetMapping("/{id}")
    public Result<Equipment> getById(@PathVariable Long id) {
        return Result.success(equipmentService.getById(id));
    }
}