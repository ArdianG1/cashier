package com.smk.cashier.service;

import com.smk.cashier.model.Stok;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Stok ServiceTest {

    @Test
    @Order(2)
    void getStokList() {
        List<Stok> barangList = StokService.getInstance().getStokList();
        assertEquals(barangList.size(),3);
    }
    @Test
    @Order(3)
    void findByKode() {
        List<Stok> resultList = StokService.getInstance().findByKode("LP002");
        assertEquals(resultList.size(),1);
    }

    @Test
    @Order(1)
    void addStok() {
        Stok laptop = new Stok();
        laptop.setId(1);
        laptop.setKodeBarang("LP001");
        laptop.setStokBarang(2);
        StokService.getInstance().addStok(laptop);

        Stok mouse = new Stok();
        mouse.setId(2);
        mouse.setKodeBarang("M0001");
        mouse.setStokBarang(7);
        StokService.getInstance().addStok(mouse);

        Stok laptopGaming = new Stok();
        laptop.setId(3);
        laptop.setKodeBarang("LP002");
        laptop.setStokBarang(3);
        StokService.getInstance().addStok(laptopGaming);

    }
}
