package com.taekwondo.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.taekwondo.dto.auth.CreateLoginDto;
import com.taekwondo.dto.auth.GetLoginDto;
import com.taekwondo.dto.auth.TokenDto;
import com.taekwondo.entity.InvalidToken;
import com.taekwondo.entity.Role;
import com.taekwondo.entity.User;
import com.taekwondo.enums.StatusCode;
import com.taekwondo.exception.AppException;
import com.taekwondo.iservice.IAuthService;
import com.taekwondo.repository.IInvalidTokenRepository;
import com.taekwondo.repository.IUserRepository;
import com.taekwondo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IInvalidTokenRepository tokenRepo;

    @Override
    public GetLoginDto login(CreateLoginDto createLoginDto) {
        User user = userRepo.findByUsername(createLoginDto.getUsername()).orElseThrow(() -> new AppException(StatusCode.USERNAME_INVALID));

        boolean isValid = passwordEncoder.matches(createLoginDto.getPassword(), user.getPassword());

        if(!isValid){
            throw new AppException(StatusCode.PASSWORD_INVALID);
        }

        String token = jwtUtil.generateToken(user);
        Set<Role> roles = user.getRoles();

        return new GetLoginDto(token, roles);
    }

    public void verify(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = jwtUtil.verifyToken(token);
        if(tokenRepo.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
            throw new AppException(StatusCode.UNAUTHENTICATED);
        }
    }

    @Override
    public void logout(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = jwtUtil.verifyToken(token);

        String jit = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidToken invalidToken = new InvalidToken(jit, expiryTime);

        tokenRepo.save(invalidToken);
    }
}
