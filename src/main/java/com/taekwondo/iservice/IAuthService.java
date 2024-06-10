package com.taekwondo.iservice;

import com.nimbusds.jose.JOSEException;
import com.taekwondo.dto.auth.CreateLoginDto;
import com.taekwondo.dto.auth.GetLoginDto;
import com.taekwondo.dto.auth.TokenDto;

import java.text.ParseException;

public interface IAuthService {
    public GetLoginDto login(CreateLoginDto createLoginDto);
    public void verify(String token) throws ParseException, JOSEException;
    public void logout(String token) throws ParseException, JOSEException;
}
