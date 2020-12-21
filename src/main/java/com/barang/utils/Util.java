/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ITAdmin
 */
public class Util {

    public static String dateToString(Date date, String format) {
        String formatDate;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        formatDate = sdf.format(date);
        return formatDate;
    }

    public static String getNomorRegister(String sequence) {
        String reg = "REG";
        String now = dateToString(new Date(), "yyyyMMdd");
        int lengthValue = sequence.length();
        for (int i = 0; i < (5 - lengthValue); i++) {
            sequence = "0" + sequence;
        }
        return reg + now + sequence;
    }

    public static String objectToString(Object object) {
        String string = null;
        if (object != null) {
            string = object.toString();
        }
        return string;
    }
}
