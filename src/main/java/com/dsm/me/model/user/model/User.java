package com.dsm.me.model.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

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

    public static String createRandomPassword(){
        String uid = UUID.randomUUID().toString().replaceAll("-","");
        return uid.substring(0,14)+randomSpecialChar();
    }

    private static String randomSpecialChar(){
        String[] scArr = new String[]{
                "!","@","#","$","%","^","&","*"
        };
        int selectRandomInt = (int) (Math.random()*(scArr.length));
        return scArr[selectRandomInt];
    }
}
