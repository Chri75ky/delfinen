package com.company.accounting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KontingentTest {

    @Test
    public void testofSetKontingentPrice_activeMemberAbove18() {
        //Arrange
        Kontingent kontingent = new Kontingent("Sebastian Bue Bjørner", 25, true);

        //Act
        int expected = 1600;
        kontingent.setKontingentPrice();
        int result = (int) kontingent.getPrice();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testofSetKontingentPrice_passiveMemberAbove18() {
        //Arrange
        Kontingent kontingent = new Kontingent("Sebastian Bue Bjørner", 25, false);

        //Act
        int expected = 500;
        kontingent.setKontingentPrice();
        int result = (int) kontingent.getPrice();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testofSetKontingentPrice_activeMemberAt18() {
        //Arrange
        Kontingent kontingent = new Kontingent("Sebastian Bue Bjørner", 18, true);

        //Act
        int expected = 1600;
        kontingent.setKontingentPrice();
        int result = (int) kontingent.getPrice();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testofSetKontingentPrice_activeMemberAbove60() {
        //Arrange
        Kontingent kontingent = new Kontingent("Sebastian Bue Bjørner", 65, true);

        //Act
        int expected = 1200;
        kontingent.setKontingentPrice();
        int result = (int) kontingent.getPrice();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testofSetKontingentPrice_passiveMemberAbove60() {
        //Arrange
        Kontingent kontingent = new Kontingent("Sebastian Bue Bjørner", 65, false);

        //Act
        int expected = 500;
        kontingent.setKontingentPrice();
        int result = (int) kontingent.getPrice();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testofSetKontingentPrice_activeMemberAt60() {
        //Arrange
        Kontingent kontingent = new Kontingent("Sebastian Bue Bjørner", 60, true);

        //Act
        int expected = 1600;
        kontingent.setKontingentPrice();
        int result = (int) kontingent.getPrice();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testofSetKontingentPrice_activeMemberBelow18() {
        //Arrange
        Kontingent kontingent = new Kontingent("Sebastian Bue Bjørner", 15, true);

        //Act
        int expected = 1000;
        kontingent.setKontingentPrice();
        int result = (int) kontingent.getPrice();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testofSetKontingentPrice_passiveMemberBelow18() {
        //Arrange
        Kontingent kontingent = new Kontingent("Sebastian Bue Bjørner", 15, false);

        //Act
        int expected = 500;
        kontingent.setKontingentPrice();
        int result = (int) kontingent.getPrice();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testofSetKontingentPrice_activeMemberAt0() {
        //Arrange
        Kontingent kontingent = new Kontingent("Sebastian Bue Bjørner", 0, true);

        //Act
        int expected = 1000;
        kontingent.setKontingentPrice();
        int result = (int) kontingent.getPrice();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testofSetKontingentPrice_passiveMemberAt0() {
        //Arrange
        Kontingent kontingent = new Kontingent("Sebastian Bue Bjørner", 0, false);

        //Act
        int expected = 500;
        kontingent.setKontingentPrice();
        int result = (int) kontingent.getPrice();

        //Assert
        assertEquals(expected, result);
    }

}