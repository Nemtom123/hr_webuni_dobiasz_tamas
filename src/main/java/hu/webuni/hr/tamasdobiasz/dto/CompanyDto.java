package hu.webuni.hr.tamasdobiasz.dto;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class CompanyDto {
    @JsonView(Views.BaseData.class)
    private Long id;
    @JsonView(Views.BaseData.class)
    private Long companyRegistrationNumber;
    @JsonView(Views.BaseData.class)
    private String companyName;
    @JsonView(Views.BaseData.class)
    private String companyAdress;

    private List<HrDto> employee;

    public CompanyDto(Long id, Long companyRegistrationNumber, String companyName, String companyAdress, List<HrDto> employee) {
        this.id = id;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.companyName = companyName;
        this.companyAdress = companyAdress;
        this.employee = employee;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<HrDto> getEmployee() {
        return employee;
    }

    public void setEmployee(List<HrDto> employee) {
        this.employee = employee;
    }

    public Long getCompanyRegistrationNumber() {
        return companyRegistrationNumber;
    }

    public void setCompanyRegistrationNumber(Long companyRegistrationNumber) {
        this.companyRegistrationNumber = companyRegistrationNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
    }
}
