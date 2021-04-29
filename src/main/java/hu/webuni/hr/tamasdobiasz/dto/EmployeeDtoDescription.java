package hu.webuni.hr.tamasdobiasz.dto;

public class EmployeeDtoDescription {
    private Long descriptionId;
    private String workName;
    private String city;
    private String street;
    private Integer number;
    private Integer zip;


    public EmployeeDtoDescription(Long descriptionId) {
        this.descriptionId = descriptionId;
    }

    public EmployeeDtoDescription(Long descriptionId, String workName, String city, String street, Integer number, Integer zip) {
        this.descriptionId = descriptionId;
        this.workName = workName;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zip = zip;
    }



    public Long getDescriptionId(Long employeeId) {
        return descriptionId;
    }

    public void setDescriptionId(Long decriptionId) {
        this.descriptionId = decriptionId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public long getDescriptionId() {
        return 0;
    }
}
