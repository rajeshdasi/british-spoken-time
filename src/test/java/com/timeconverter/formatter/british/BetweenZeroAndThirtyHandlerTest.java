package com.timeconverter.formatter.british;

import com.timeconverter.dto.TimeInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BetweenZeroAndThirtyHandlerTest {

    @Mock
    private NumberToWordConverter numberConverter;

    private BetweenZeroAndThirtyHandler handler;

    @BeforeEach
    void setUp() {
        handler = new BetweenZeroAndThirtyHandler(numberConverter);
    }

    @Test
    void testCanHandle_Minute1() {
        assertTrue(handler.canHandle(1));
    }

    @Test
    void testCanHandle_Minute4() {
        assertTrue(handler.canHandle(4));
    }

    @Test
    void testCanHandle_Minute7() {
        assertTrue(handler.canHandle(7));
    }

    @Test
    void testCanHandle_Minute13() {
        assertTrue(handler.canHandle(13));
    }

    @Test
    void testCanHandle_Minute29() {
        assertTrue(handler.canHandle(29));
    }

    @Test
    void testCannotHandle_Minute0() {
        assertFalse(handler.canHandle(0));
    }

    @Test
    void testCannotHandle_Minute5() {
        assertFalse(handler.canHandle(5));
    }

    @Test
    void testCannotHandle_Minute10() {
        assertFalse(handler.canHandle(10));
    }

    @Test
    void testCannotHandle_Minute15() {
        assertFalse(handler.canHandle(15));
    }

    @Test
    void testCannotHandle_Minute20() {
        assertFalse(handler.canHandle(20));
    }

    @Test
    void testCannotHandle_Minute25() {
        assertFalse(handler.canHandle(25));
    }

    @Test
    void testCannotHandle_Minute30() {
        assertFalse(handler.canHandle(30));
    }

    @Test
    void testCannotHandle_Minute31() {
        assertFalse(handler.canHandle(31));
    }

    @Test
    void testFormat() {
        // Given
        TimeInput timeInput = new TimeInput(6, 3);
        when(numberConverter.convertHourToWord(6)).thenReturn("six");
        when(numberConverter.convertMinuteToWord(3)).thenReturn("o three");

        // When
        String result = handler.format(timeInput);

        // Then
        assertEquals("six o three", result);
        verify(numberConverter).convertHourToWord(6);
        verify(numberConverter).convertMinuteToWord(3);
    }

    @Test
    void testFormat_SevenTwentyOne() {
        // Given
        TimeInput timeInput = new TimeInput(7, 21);
        when(numberConverter.convertHourToWord(7)).thenReturn("seven");
        when(numberConverter.convertMinuteToWord(21)).thenReturn("twenty one");

        // When
        String result = handler.format(timeInput);

        // Then
        assertEquals("seven twenty one", result);
        verify(numberConverter).convertHourToWord(7);
        verify(numberConverter).convertMinuteToWord(21);
    }

    @Test
    void testFormat_MidnightMinute1() {
        // Given
        TimeInput timeInput = new TimeInput(0, 1);
        when(numberConverter.convertHourToWord(0)).thenReturn("midnight");
        when(numberConverter.convertMinuteToWord(1)).thenReturn("o one");

        // When
        String result = handler.format(timeInput);

        // Then
        assertEquals("midnight o one", result);
        verify(numberConverter).convertHourToWord(0);
        verify(numberConverter).convertMinuteToWord(1);
    }
}
