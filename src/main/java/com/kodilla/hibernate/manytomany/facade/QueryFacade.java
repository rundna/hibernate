package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryFacade {

    @Autowired
    private EmployeeDao employee;
    @Autowired
    private CompanyDao company;

    public List<Company> searchCompanyLike(String name) {
        List<Company> list = company.searchCompanyLike(name);
        return list;
    }

    public List<Employee> searchLastNameLike(String name) {
        List<Employee> list = employee.searchLastNameLike(name);
        return list;
    }


}
