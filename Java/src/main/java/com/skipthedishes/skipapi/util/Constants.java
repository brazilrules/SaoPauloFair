/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skipthedishes.skipapi.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vanessa Barcelos
 */
public class Constants {
    public static final Map<Integer, String> ORDER_STATUS;
    static {
        Map<Integer, String> temp = new HashMap<>();
        temp.put(0, "Placed");
        temp.put(1, "Inbound");
        temp.put(2, "Delivered");
        temp.put(3, "Canceled");
        temp.put(4, "Rejected");
        ORDER_STATUS = Collections.unmodifiableMap(temp);
    }
}
