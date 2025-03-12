package com.crm.payload;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class EmployeeDto {
    private Long id;

    @NotBlank
    @Size(min=3,message ="At least 3 chars required")
    private String name;
    @Email
    private String emailId;

    //Other Country

    @Size(min=10, max=10, message="should be 10 digits")
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(min = 3, message = "At least 2 chars required") String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(min = 3, message = "At least 2 chars required") String name) {
        this.name = name;
    }

    public @Email String getEmailId() {
        return emailId;
    }

    public void setEmailId(@Email String emailId) {
        this.emailId = emailId;
    }

    public @Size(min = 10, max = 10, message = "should be 10 digits") String getMobile() {
        return mobile;
    }

    public void setMobile(@Size(min = 10, max = 10, message = "should be 10 digits") String mobile) {
        this.mobile = mobile;
    }
}
