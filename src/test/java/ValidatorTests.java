import com.vilelapinheiro.Identifier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ValidatorTests {

    @Test
    public void identifiersShouldHaveBetween1and6Characters() {
        final String longString = "a123456";

        assertThrows(IllegalArgumentException.class, () -> {Identifier.validate(longString);});

        final String shortString = "";

        assertThrows(IllegalArgumentException.class, () -> {Identifier.validate(shortString);});
    }

    @Test
    public void identifiersShouldStartWithCharacter() {
        var invalidIdentifiers = List.of(
                "1soma", // starts with a number
                "_asd", // starts with an underscore
                "áasd" // starts with a special character
                );

        invalidIdentifiers.forEach(identifier -> {
            assertThrows(IllegalArgumentException.class, () -> {Identifier.validate(identifier);});
        });
    }

    @Test
    public void identifiersShouldNotHaveInvalidCharacter() {
        var invalidIdentifiers = List.of(
                "abc_ef", // has an underscore in the middle
                "contá", // ends with invalid character
                "cont*1" // asterisk
        );

        invalidIdentifiers.forEach(identifier -> {
            assertThrows(IllegalArgumentException.class, () -> {Identifier.validate(identifier);});
        });
    }

    @Test
    public void correctIdentifierShouldaBeAccepted() {
        var invalidIdentifiers = List.of(
                "a",
                "ab",
                "abc",
                "abcd",
                "abcde",
                "abcdef",
                "ab1234",
                "ab1987",
                "cont1"
        );

        invalidIdentifiers.forEach(identifier -> {
            assertDoesNotThrow(() -> {
//                System.out.println(identifier);
                Identifier.validate(identifier);});
        });
    }
}
