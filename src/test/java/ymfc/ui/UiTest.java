//@@author 3CCLY
package ymfc.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    @Test
    void readCommand_validInput_returnInput() {
        // Parse a test string into the Ui class
        String testString = "Test String";
        ByteArrayInputStream testInput = new ByteArrayInputStream(testString.getBytes());
        Ui ui = new Ui(testInput);
        assertEquals(testString, ui.readCommand());
    }
}
