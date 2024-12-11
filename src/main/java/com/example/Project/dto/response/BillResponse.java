package com.example.Project.dto.response;

import com.example.Project.entity.Apartment;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.enums.Enums;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillResponse {

    String id;
    String apartmentId;
    List<ApartmentChargeForBillResponse> apartmentChargeList;
    BigDecimal totalPaymentAmount;
    BigDecimal totalAmountPaid;
    BigDecimal totalAmountDue;
    LocalDateTime monthYear;
    Enums.BillStatus status;    // Còn thiếu / Trả đủ
    Enums.PaymentMethod paymentMethod;  // Tiền mặt / Chuyển khoản / Quét thẻ
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
