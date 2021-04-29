package hu.webuni.hr.tamasdobiasz.mapper;

import hu.webuni.hr.tamasdobiasz.dto.CompanyDto;
import hu.webuni.hr.tamasdobiasz.model.Company;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDto companyToDto(Company company);

    @Mapping(target = "employee", ignore = true)
    @Named("summary")
    CompanyDto companySummaryToDto(Company company);

    List<CompanyDto> companiesToDtos(List<Company> companies);

    @IterableMapping(qualifiedByName = "summary")
    List<CompanyDto> companySummariesToDtos(List<Company> companies);

    Company dtoToCompany(CompanyDto companyDto);
}
