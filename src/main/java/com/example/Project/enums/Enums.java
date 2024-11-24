package com.example.Project.enums;

public class Enums {
    public enum ResidentRole {
        OWNER,
        NON_OWNER;
    }
    public enum ApartmentStatus {
        AVAILABLE,
        OCCUPIED;
    }
    public enum BillStatus {
        PAID,
        UNPAID;
    }
    public enum PaymentMethod {
        CASH,
        BANK_TRANSFER,
        CREDIT_CARD;
    }
    public enum ChargeType {
        SERVICE,
        DONATION,
        MANAGEMENT;
    }
}
