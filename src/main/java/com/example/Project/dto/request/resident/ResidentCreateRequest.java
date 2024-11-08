package com.example.Project.dto.request.resident;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResidentCreateRequest {

    @NotNull(message = "Id căn hộ không được để trống")
    String apartmentId;

    @NotNull(message = "Tên cư dân không được để trống")
    String username;

    @NotNull (message = "Vai trò cư dân không được để trống")
    Boolean role;

    @Pattern(regexp = "^[0-9]{10}$", message = "Định dạng số điện thoại không hợp lệ")
    String phoneNumber;

    @NotNull(message = "Ngày sinh không được để trống")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Định dạng ngày sinh không hợp lệ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthday;

    @NotNull(message = "Địa chỉ thường trú không được để trống")
    String permanentAddress;

    String temporaryAddress;

}
