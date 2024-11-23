package com.example.Project.dto.response;

import com.example.Project.entity.Apartment;
import com.example.Project.entity.ApartmentCharge;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillResponse {

    String id;
    BigDecimal totalPaymentAmount;
    BigDecimal totalAmountPaid;
    BigDecimal totalAmountDue;
    LocalDateTime month;
    String status;    // Còn thiếu / Trả đủ
    String paymentMethod;     // Tiền mặt / Chuyển khoản / Quét thẻ
    LocalDateTime createAt;
    LocalDateTime updateAt;

    List<ApartmentCharge> apartmentChargeList;
}
