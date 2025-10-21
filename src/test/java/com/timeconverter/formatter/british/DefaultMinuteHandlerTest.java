package com.timeconverter.formatter.british;

import com.timeconverter.model.TimeInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultMinuteHandlerTest {

    @Mock
    private NumberToWordConverter numberConverter;

    private DefaultMinuteHandler handler;

    @BeforeEach
    void setUp() {
        handler = new DefaultMinuteHandler(numberConverter);
    }

    @Test
    void testCanHandle_Minute31() {
        assertTrue(handler.canHandle(31));
    }

    @Test
    void testCanHandle_Minute34() {
        assertTrue(handler.canHandle(34));
    }

    @Test
    void testCanHandle_Minute37() {
        assertTrue(handler.canHandle(37));
    }

    @Test
    void testCanHandle_Minute43() {
        assertTrue(handler.canHandle(43));
    }

    @Test
    void testCanHandle_Minute59() {
        assertTrue(handler.canHandle(59));
    }

    @Test
    void testCannotHandle_Minute30() {
        assertFalse(handler.canHandle(30));
    }

    @Test
    void testCannotHandle_Minute35() {
        assertFalse(handler.canHandle(35));
    }

    @Test
    void testCannotHandle_Minute40() {
        assertFalse(handler.canHandle(40));
    }

    @Test
    void testCannotHandle_Minute45() {
        assertFalse(handler.canHandle(45));
    }

    @Test
    void testCannotHandle_Minute50() {
        assertFalse(handler.canHandle(50));
    }

    @Test
    void testCannotHandle_Minute55() {
        assertFalse(handler.canHandle(55));
    }

    @Test
    void testCannotHandle_Minute0() {
        assertFalse(handler.canHandle(0));
    }

    @Test
    void testCannotHandle_Minute15() {
        assertFalse(handler.canHandle(15));
    }

    @Test
    void testFormat() {
        // Given
        TimeInput timeInput = new TimeInput(10, 32);
        when(numberConverter.convertHourToWord(10)).thenReturn("ten");
        when(numberConverter.convertMinuteToWord(32)).thenReturn("thirty two");

        // When
        String result = handler.format(timeInput);

        // Then
        assertEquals("ten thirty two", result);
        verify(numberConverter).convertHourToWord(10);
        verify(numberConverter).convertMinuteToWord(32);
    }

    @Test
    void testFormat_ElevenFiftySeven() {
        // Given
        TimeInput timeInput = new TimeInput(11, 57);
        when(numberConverter.convertHourToWord(11)).thenReturn("eleven");
        when(numberConverter.convertMinuteToWord(57)).thenReturn("fifty seven");

        // When
        String result = handler.format(timeInput);

        // Then
        assertEquals("eleven fifty seven", result);
        verify(numberConverter).convertHourToWord(11);
        verify(numberConverter).convertMinuteToWord(57);
    }

    @Test
    void testFormat_NoonMinute31() {
        // Given
        TimeInput timeInput = new TimeInput(12, 31);
        when(numberConverter.convertHourToWord(12)).thenReturn("noon");
        when(numberConverter.convertMinuteToWord(31)).thenReturn("thirty one");

        // When
        String result = handler.format(timeInput);

        // Then
        assertEquals("noon thirty one", result);
        verify(numberConverter).convertHourToWord(12);
        verify(numberConverter).convertMinuteToWord(31);
    }
}
