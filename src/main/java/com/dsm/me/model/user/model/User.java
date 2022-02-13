package com.dsm.me.model.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    private String email;
    private String password;
    private String nickname;
    private String id;
    private String profile;
    @Column(name = "background")
    private String backgroundHax;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public void changePassword(String password){
        this.password = password;
    }


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