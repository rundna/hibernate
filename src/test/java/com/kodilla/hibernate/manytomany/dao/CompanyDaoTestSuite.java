package com.kodilla.hibernate.manytomany.dao;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyDaoTestSuite {
    @Autowired
    CompanyDao companyDao;

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testSaveManyToMany() {
        //Given
        Employee johnSmith = new Employee("John", "Smith");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");

        Company softwareMachine = new Company("Software Machine");
        Company dataMaesters = new Company("Data Maesters");
        Company greyMatter = new Company("Grey Matter");

        softwareMachine.getEmployees().add(johnSmith);
        dataMaesters.getEmployees().add(stephanieClarckson);
        dataMaesters.getEmployees().add(lindaKovalsky);
        greyMatter.getEmployees().add(johnSmith);
        greyMatter.getEmployees().add(lindaKovalsky);

        johnSmith.getCompanies().add(softwareMachine);
        johnSmith.getCompanies().add(greyMatter);
        stephanieClarckson.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(greyMatter);

        //When
        companyDao.save(softwareMachine);
        int softwareMachineId = softwareMachine.getId();
        companyDao.save(dataMaesters);
        int dataMaestersId = dataMaesters.getId();
        companyDao.save(greyMatter);
        int greyMatterId = greyMatter.getId();
        List<Employee> result = employeeDao.searchByLastName("Kovalsky");


        //Then
        Assert.assertNotEquals(0, softwareMachineId);
        Assert.assertNotEquals(0, dataMaestersId);
        Assert.assertNotEquals(0, greyMatterId);
        Assert.assertEquals(2,result.size());

        //CleanUp
        //  /try {
        //    companyDao.delete(softwareMachineId);
        //    companyDao.delete(dataMaestersId);
        //    companyDao.delete(greyMatterId);
        //} catch (Exception e) {
        //    //do nothing
        //}

    }
    @Test
    public void testSearchByName() {
        //Given
        employeeDao.save(new Employee("John", "Smith"));
        employeeDao.save(new Employee("Stephanie", "Clarckson"));
        employeeDao.save(new Employee("Linda", "Kovalsky"));

        //When
        List<Employee> result = employeeDao.searchByLastName("Kovalsky");

        //Then
        Assert.assertEquals(1,result.size());

        //CleanUp
        try {
            employeeDao.deleteAll();

        } catch (Exception e) {

        }
    }
    @Test
    public void testSearchByThreeLetters() {
        //Given
        companyDao.save(new Company("Software Machine"));
        companyDao.save(new Company("Data Maesters"));
        companyDao.save(new Company("Grey Matter"));

        //When
        List<Company> result = companyDao.searchByFirstThreeLetters("Dat",3);

        //Then
        Assert.assertEquals(1,result.size());

        //CleanUp
        try {
            companyDao.deleteAll();
        } catch (Exception e) {
        }
    }

}
