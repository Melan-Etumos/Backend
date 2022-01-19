package com.dsm.me.model.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {
    @Id
    private String email;
    private String password;
    private String nickname;
    private String id;
    private String profile;
    private String backgroundHax;

    public static String createRandomId(){
        return "";
    }
}
