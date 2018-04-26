package com.kodilla.hibernate.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import com.kodilla.hibernate.manytomany.facade.QueryFacade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchFacadeTestSuite {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmployeeDao employeeDao;
    //@Autowired
    //private QueryFacade queryFacade;

    @Test
    public void testSearchLastNameLike() {
        //Given
        employeeDao.save(new Employee("John", "Smith"));
        employeeDao.save(new Employee("Stephanie", "Clarckson"));
        employeeDao.save(new Employee("Linda", "Kovalsky"));

        //When
        List<Employee> list = employeeDao.searchLastNameLike("cks");

        //Then
        Assert.assertEquals(1,list.size());

        //CleanUp
        try {
            employeeDao.deleteAll();

        } catch (Exception e) {

        }
    }
    @Test
    public void testSearchCompanyLike() {
        //Given
        companyDao.save(new Company("Software Machine"));
        companyDao.save(new Company("Data Maesters"));
        companyDao.save(new Company("Grey Matter"));

        //When
        List<Company> list = companyDao.searchCompanyLike("Mat");

        //Then
        Assert.assertEquals(1,list.size());

        //CleanUp
        try {
            companyDao.deleteAll();
        } catch (Exception e) {
        }
    }
}
