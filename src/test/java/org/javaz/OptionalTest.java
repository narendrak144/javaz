package org.javaz;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.*;
import static org.junit.Assert.assertEquals;

public class OptionalTest {

    @Test
    public void testMap() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("a", "5");
        param.put("b", "true");
        param.put("c", "-3");

        assertEquals(5, readPositiveIntParam(param, "a"));
        assertEquals(0, readPositiveIntParam(param, "b"));
        assertEquals(0, readPositiveIntParam(param, "c"));
        assertEquals(0, readPositiveIntParam(param, "d"));
    }

    public int readPositiveIntParam(Map<String, String> params, String name) {
        return ofNullable(params.get(name))
                .flatMap(OptionalTest::s2i)
                .filter(i -> i > 0).orElse(0);
    }

    public static Optional<Integer> s2i(String s) {
        try {
            return of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return empty();
        }
    }
}
