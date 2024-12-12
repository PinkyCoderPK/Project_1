package com.example.Project.dto.request.resident;

import com.example.Project.enums.Enums;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResidentRequest {

    @NotNull(message = "Id căn hộ không được để trống")
    String apartmentId;

    @NotNull(message = "Tên cư dân không được để trống")
    String residentName;

    @Builder.Default
    Enums.ResidentRole role = Enums.ResidentRole.NON_OWNER;

    @Pattern(regexp = "^[0-9]{10}$", message = "Định dạng số điện thoại không hợp lệ")
    String phoneNumber;

    @NotNull(message = "Ngày sinh không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthday;

    @NotNull(message = "Địa chỉ thường trú không được để trống")
    String permanentAddress;

    String temporaryAddress;

}
