package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceDaoTestSuite {
    @Autowired
    ProductDao productDao;
    @Autowired
    ItemDao itemDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Test
    public void testInvoiceDaoSave(){
        Product p1 = new Product("apple");
        Product p2 = new Product("bananas");
        Product p3 = new Product("oranges");
        Item i1 = new Item(p1,new BigDecimal(100),2);
        Item i2 = new Item(p2,new BigDecimal(20),4);
        Item i3 = new Item(p3,new BigDecimal(40),40);

        i1.setProduct(p1);
        i2.setProduct(p2);
        i3.setProduct(p3);

        p1.getItemList().add(i1);
        p2.getItemList().add(i2);
        p3.getItemList().add(i3);

        Invoice invoice1 = new Invoice("1/03/2018");
        invoice1.getItemsList().add(i1);
        invoice1.getItemsList().add(i2);
        invoice1.getItemsList().add(i3);

        i1.setInvoice(invoice1);
        i2.setInvoice(invoice1);
        i3.setInvoice(invoice1);

        invoiceDao.save(invoice1);

        Assert.assertEquals("1/03/2018",i1.getInvoice().getNumber());



    }
}
