package com.itsolver.util.seguridad;

import com.itsolver.util.exception.ProtocoloDeSeguridadException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * @author  mliz
 */
public final class PasswordService
{
  private static PasswordService instance;

  private PasswordService()
  {
  }

  public synchronized String encrypt(String plaintext) throws ProtocoloDeSeguridadException
  {
    MessageDigest md = null;
    try
    {
      md = MessageDigest.getInstance("SHA"); //step 2
    }
    catch(NoSuchAlgorithmException e)
    {
      throw new ProtocoloDeSeguridadException("Problemas con el protocolo de cifrado");
    }
    try
    {
      md.update(plaintext.getBytes("UTF-8")); //step 3
    }
    catch(UnsupportedEncodingException e)
    {
      throw new ProtocoloDeSeguridadException("Problemas para cifrar los caracteres ingresados");
    }

    byte raw[] = md.digest(); //step 4
    //String hash = (new BASE64()).get.encode(raw); //step 5
    String hash = Base64.getEncoder().encodeToString(raw);
    return hash; //step 6
  }
  
  /**
 * @return  the instance
 * @uml.property  name="instance"
 */
public static synchronized PasswordService getInstance() //step 1
  {
    if(instance == null)
    {
       instance = new PasswordService(); 
    } 
    return instance;
  }
}

