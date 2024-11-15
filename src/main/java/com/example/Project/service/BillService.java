package com.example.Project.service;
import com.example.Project.entity.Apartment;
import com.example.Project.entity.ApartmentCharge;
import com.example.Project.entity.Bill;
import com.example.Project.repository.ApartmentChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private ApartmentChargeRepository apartmentChargeRepository;

    public void calculateTotalPaymentAmount(Bill bill) {
        Apartment apartment = bill.getApartment();
        if (apartment == null) {
            throw new IllegalArgumentException("Căn hộ không được null.");
        }

        // lấy danh sách tất cả các ApartmentCharge của căn hộ
        List<ApartmentCharge> charges = apartmentChargeRepository.findByApartmentId(apartment.getId());

        // tổng
        double totalPaymentAmount = charges.stream()
                .mapToDouble(ApartmentCharge::getChargeAmount)
                .sum();

        bill.setTotalPaymentAmount(totalPaymentAmount);
    }
}
