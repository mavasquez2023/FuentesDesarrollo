package cl.laaraucana.rendicionpagonomina.services;

public interface EncryptService {

	public String encrypt(String plainText);

	public String decrypt(String encryptedText);

}
