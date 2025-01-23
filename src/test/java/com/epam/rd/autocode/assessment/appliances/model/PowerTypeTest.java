package com.epam.rd.autocode.assessment.appliances.model;

import static com.epam.rd.autocode.assessment.appliances.model.TestConstants.POWER_TYPE_TYPE;
import static com.epam.rd.autocode.assessment.appliances.model.TestConstants.PowerType.ENUM_CONSTANT_AC110;
import static com.epam.rd.autocode.assessment.appliances.model.TestConstants.PowerType.ENUM_CONSTANT_AC220;
import static com.epam.rd.autocode.assessment.appliances.model.TestConstants.PowerType.ENUM_CONSTANT_ACCUMULATOR;
import static com.epam.rd.autocode.assessment.appliances.model.TestConstants.PowerType.ENUM_COUNT_CONSTANTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PowerTypeTest {
    private static List<?> constants;
    private static Class<?> clazz;

    @BeforeAll
    static void setup() throws ClassNotFoundException {
        clazz = Class.forName(POWER_TYPE_TYPE);
        constants = Arrays.asList(clazz.getEnumConstants());
    }

    @Test
    @DisplayName("Class " + POWER_TYPE_TYPE + " is Enum")
    void checkIsEnum() {
        final boolean actual = clazz.isEnum();
        assertTrue(actual);
    }

    @Test
    @DisplayName("There must be " + ENUM_COUNT_CONSTANTS + " constants")
    void checkCountConstants() {
        int actual = constants.size();
        assertEquals(ENUM_COUNT_CONSTANTS, actual);
    }

    @Test
    @DisplayName("There must be constant with name " + ENUM_CONSTANT_AC220)
    void checkEnumConstantAC220() {
        long actual = Arrays.stream(clazz.getDeclaredFields())
                .map(f -> f.getName())
                .filter(name -> name.equals(ENUM_CONSTANT_AC220))
                .count();
        assertEquals(1, actual);
    }

    @Test
    @DisplayName("There must be constant with name " + ENUM_CONSTANT_AC110)
    void checkEnumConstantAC110() {
        long actual = Arrays.stream(clazz.getDeclaredFields())
                .map(f -> f.getName())
                .filter(name -> name.equals(ENUM_CONSTANT_AC110))
                .count();
        assertEquals(1, actual);
    }

    @Test
    @DisplayName("There must be constant with name " + ENUM_CONSTANT_ACCUMULATOR)
    void checkEnumConstantACCUMULATOR() {
        long actual = Arrays.stream(clazz.getDeclaredFields())
                .map(f -> f.getName())
                .filter(name -> name.equals(ENUM_CONSTANT_ACCUMULATOR))
                .count();
        assertEquals(1, actual);
    }
}