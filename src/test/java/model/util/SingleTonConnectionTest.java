package model.util;

import lombok.experimental.StandardException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleTonConnectionTest {
    @Test
    public void testConnection(){
        assertDoesNotThrow(SingleTonConnection::getInstance);
    }
}