package com.labbooking.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labbooking.entity.Equipment;
import com.labbooking.mapper.EquipmentMapper;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService extends ServiceImpl<EquipmentMapper, Equipment> {
}