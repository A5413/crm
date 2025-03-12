package com.crm.controller;
import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto dto,
                                                        BindingResult result
    ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EmployeeDto employeeDto = employeeService.addEmployee(dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);


    }



    @DeleteMapping()
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> upadteEmployee(@RequestParam Long id,
                                 @RequestBody EmployeeDto dto) {
        employeeService.upadteEmployee(id,dto);
        return new ResponseEntity<>("upadted",HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee?pageNo=1&pageSize=5&sortBy=emailId&sortDir=asc
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(@RequestParam(name="pageSize", required=false,defaultValue = "5")int pageSize,
                                                          @RequestParam(name="pageNo", required=false,defaultValue="0") int pageNo,
                                                          @RequestParam(name="sortBy", required=false, defaultValue="Name")String sortBy,
                                                          @RequestParam(name="sortDir", required=false, defaultValue="asc")String sortDir)
    {
        List<EmployeeDto> employeesDto = employeeService.getEmployee(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>( employeesDto,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee/employeeId/1
    //it is not Query parameter it is path parameter
    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
            @PathVariable Long empId
    ) {

     EmployeeDto dto =  employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }

}
