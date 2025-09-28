package com.saber.demojavaee.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Test {
    public static void main(String[] args) {
        BigInteger bigInt1 = new BigInteger("12345678901234");
        BigInteger bigInt2 = new BigInteger("99999999999999");
        BigInteger bigInt3 = new BigInteger("1000000000000");

        System.out.println("BigInteger Examples:");
        System.out.println(bigInt1 + " = " + PersianNumberConverter.convert(bigInt1));
        System.out.println(bigInt2 + " = " + PersianNumberConverter.convert(bigInt2));
        System.out.println(bigInt3 + " = " + PersianNumberConverter.convert(bigInt3));

        // BigDecimal examples
        BigDecimal bigDec1 = new BigDecimal("12345678901234567890.123");
        BigDecimal bigDec2 = new BigDecimal("999999999999999999999.9999");
        BigDecimal bigDec3 = new BigDecimal("-123456789.99");

        System.out.println("\nBigDecimal Examples:");
        System.out.println(bigDec1 + " = " + PersianNumberConverter.convert(bigDec1, 3));
        System.out.println(bigDec2 + " = " + PersianNumberConverter.convert(bigDec2, 4));
        System.out.println(bigDec3 + " = " + PersianNumberConverter.convert(bigDec3));

        // Currency examples
        BigDecimal salary = new BigDecimal("15000000.50");
        System.out.println("\nCurrency Example:");
        System.out.println(PersianNumberConverter.convertWithCurrency(salary, "ریال"));

        // Formatting examples
        System.out.println("\nFormatted Examples:");
        System.out.println(PersianNumberConverter.formatNumber(bigInt1));
        System.out.println(PersianNumberConverter.formatNumber(bigDec1));

        // Very large numbers
        BigInteger hugeNumber = new BigInteger("1000000000000000000000000000000000000"); // 10^36
        System.out.println("\nVery Large Number:");
        System.out.println(PersianNumberConverter.convertScientific(hugeNumber));
    }
}
