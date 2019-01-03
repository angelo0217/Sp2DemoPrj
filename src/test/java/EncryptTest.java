import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Enumeration;

/**
 * Created on 2019/1/2
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class EncryptTest {


//    Set OPENSSL_CONF=C:\Program Files (x86)\Git\ssl\openssl.cnf
//
//    步驟1 – 生成私密金鑰
//    openssl genrsa -out private.key 1024
//    步驟2 – 創建一個X509證書(.cer檔),其中包含您在註冊私有應用程式時上傳的公開金鑰(或升級到合作夥伴應用程式).
//    openssl req -new -x509 -key private.key -out publickey.cer -days 365
//    步驟3 – 將您的x509證書和私密金鑰匯出到pfx文件.如果您選擇的包裝庫使用.pem檔來簽署請求,則不需要此步驟.
//    openssl pkcs12 -export -out public_privatekey.pfx -inkey private.key -in publickey.cer

    @Test
    public void testEncode() throws Exception {
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

        InputStream in = null;
        try {
            ClassPathResource clas = new ClassPathResource("x509key/publickey.cer");
            in = clas.getInputStream();
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(in);

            String aa = encrypt("5465", cert);
            in.close();
            System.out.println(aa);
            KeyStore keystore = KeyStore.getInstance("PKCS12");
            clas = new ClassPathResource("x509key/public_privatekey.pfx");
            in = clas.getInputStream();
            keystore.load(in,"123456".toCharArray());
            Enumeration<String> aliases = keystore.aliases();

            PrivateKey pKey=(PrivateKey)keystore.getKey(keystore.aliases().nextElement(), "123456".toCharArray());

//            X509Certificate certificate = (X509Certificate)keystore.getCertificate("alias");

            System.out.println(decrypt(aa, pKey));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) in.close();
        }
    }


    private String encrypt(String str, X509Certificate certificate) throws Exception {
        RSAPublicKey pk = (RSAPublicKey ) certificate.getPublicKey();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, pk);
        byte[] encrypted = cipher.doFinal(str.getBytes());
        return Base64.encodeBase64String(encrypted);
    }
    private static final int MAX_DECRYPT_BLOCK = 128;


    public static String decrypt(String data, PrivateKey pKey)throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, pKey);
        byte[] b = Base64.decodeBase64(data);
        byte[] bytes = cipher.doFinal(b);

        return new String(bytes,"UTF-8");
    }

}
