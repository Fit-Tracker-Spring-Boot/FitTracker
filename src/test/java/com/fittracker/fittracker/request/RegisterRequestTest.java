package com.fittracker.fittracker.request;

import com.fittracker.fittracker.entity.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterRequestTest {

    @Test
    void givenRegisterRequest_toUserShouldReturnUser() {
        String exampleUserName = "exampleUserName";
        String exampleEmail = "user@example.com";
        String examplePassword = "examplePassword";
        RegisterRequest request = new RegisterRequest(exampleUserName, exampleEmail, examplePassword);

        var expected = new User(exampleUserName,exampleEmail);
        var result = request.toUser();

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}