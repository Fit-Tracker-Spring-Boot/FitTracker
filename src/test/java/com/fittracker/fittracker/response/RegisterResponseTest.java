package com.fittracker.fittracker.response;

import static org.assertj.core.api.Assertions.assertThat;

import com.fittracker.fittracker.entity.User;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class RegisterResponseTest {

    @Test
    void givenUser_shouldReturnRegisterResponse() {
        String exampleUserName = "exampleUserName";
        String exampleEmail = "user@example.com";
        String examplePassword = "examplePassword";
        UUID exampleId = UUID.fromString("948cc727-68e5-455c-ab6d-942e585bde0d");
        User user = new User(exampleId, exampleUserName, exampleEmail, examplePassword);

        var expected = new RegisterResponse(exampleId, exampleUserName, exampleEmail);
        var result = RegisterResponse.fromUser(user);

        assertThat(result).isEqualTo(expected);
    }
}