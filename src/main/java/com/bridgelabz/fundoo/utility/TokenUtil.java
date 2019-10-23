package com.bridgelabz.fundoo.utility;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

@Component
public class TokenUtil {

	public final String SECRET_TOKEN="userlr";
	
	public String createtoken(Long id)
	{
		try {
			
			Algorithm algorithm=Algorithm.HMAC256(SECRET_TOKEN);
			
			String token=JWT.create().withClaim("user_id", id).sign(algorithm);
			
			return token;
			
		} catch (JWTCreationException e) {
			
			e.printStackTrace();
		}
		catch (IllegalStateException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public Long decodetoken(String token)
	{
		Long user_id;
	
		Verification verification=null;
		
		try {
			
			verification=JWT.require(Algorithm.HMAC256(SECRET_TOKEN));
			
			
			
		} catch (IllegalArgumentException e) {
		
			e.printStackTrace();
		}
		
		JWTVerifier jwtverifier=verification.build();
		
		DecodedJWT decodejwt=jwtverifier.verify(token);
		
		Claim claim=decodejwt.getClaim("user_id");
		
		user_id=claim.asLong();
		
		return user_id;
	}
	
	
}
