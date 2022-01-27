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
@Table(name = "tbl_user")
public class User {
    @Id
    private String email;
    private String password;
    private String nickname;
    private String id;
    private String profile;
    private String backgroundHax;

    public static class Info{
        public static String createRandomId(String email){
            return email.charAt(0)+createUid().substring(0, 10)+ createUid().substring(0, (int) (Math.random() * 10));
        }

        public static String createRandomPassword(){
            return createUid().substring(0,14)+randomSpecialChar();
        }

        private static String createUid(){
            return UUID.randomUUID().toString().replaceAll("-","");
        }

        private static String randomSpecialChar(){
            String[] scArr = new String[]{
                    "!","@","#","$","%","^","&","*"
            };
            int selectRandomInt = (int) (Math.random()*(scArr.length));
            return scArr[selectRandomInt];
        }

    }
}