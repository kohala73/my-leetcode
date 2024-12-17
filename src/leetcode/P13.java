package leetcode;

/**
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * @author: lisp2c
 * @date: 2019-03-28
 */
public class P13 {


    public int romanToInt(String s) {
        int a = 0;
        if (s.indexOf("IV") != -1) {
            a -= 2;
        }
        if (s.indexOf("IX") != -1) {
            a -= 2;
        }
        if (s.indexOf("XL") != -1) {
            a -= 20;
        }
        if (s.indexOf("XC") != -1) {
            a -= 20;
        }
        if (s.indexOf("CD") != -1) {
            a -= 200;
        }
        if (s.indexOf("CM") != -1) {
            a -= 200;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'I') {
                a += 1;
            }
            if (c == 'V') {
                a += 5;
            }
            if (c == 'X') {
                a += 10;
            }
            if (c == 'L') {
                a += 50;
            }
            if (c == 'C') {
                a += 100;
            }
            if (c == 'D') {
                a += 500;
            }
            if (c == 'M') {
                a += 1000;
            }

        }


        return a;

    }
}

