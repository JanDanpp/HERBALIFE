package com.missz.herbalifegs.mapper;

import com.missz.herbalifegs.bean.Employee;

//
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);

}
