package com.dsm.me.model.user.service;

import java.util.Random;

public class EmailService {
    public void sendEmailCode(String email) {
        Random random = new Random();
        int dice = random.nextInt(49999)+45554;


    }


}
