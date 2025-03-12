package com.crm.service;

import com.crm.Exception.ResourceNotFound;
import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    //Constructor base dependencies injection
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(emp);

        return employeeDto;

    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto upadteEmployee(Long id, EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee UpdatedEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(UpdatedEmployee);
        return employeeDto;


    }
    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {

       Sort sort = sortDir.equalsIgnoreCase("asc")  ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();


        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees = all.getContent();


        List<EmployeeDto> dto = employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

//Entity to dto
    //All object content copy from Entity to dto
    public EmployeeDto mapToDto(Employee employee){
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        return dto;

    }

    //Dto to Entity
    public Employee mapToEntity(EmployeeDto dto){
        Employee employee = modelMapper.map(dto, Employee.class);


        return employee;
    }

    public EmployeeDto getEmployeeById(Long empId) {
       Employee employee = employeeRepository.findById(empId).orElseThrow(
               ()-> new ResourceNotFound("Record not Found with this Id: "+empId)
        );
        EmployeeDto dto = mapToDto(employee);
        return dto ;


    }
}