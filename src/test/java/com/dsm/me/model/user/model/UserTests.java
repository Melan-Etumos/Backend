package com.dsm.me.model.user.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;

public class UserTests {
    @DisplayName("random id create test")
    @Test
    public void idRandomCreateTest(){
        String id = User.Info.createRandomId("testEmail@com");
        assertThat(id, startsWith("t"));
    }
}
