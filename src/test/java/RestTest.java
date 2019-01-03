import org.apache.commons.codec.binary.Base64;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

/**
 * Created on 2018/12/27
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
public class RestTest {

    @Test
    public void test() {
        String url = "http://localhost:8888/demo/oauth/token?username=user&password=12345&grant_type=password&scope=read&client_id=admin&client_secret=abc123";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

//        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//        map.add("email", "first.last@example.com");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);

        ResponseEntity<String> response = new RestTemplate().postForEntity(url, request, String.class);

        System.out.println(response.toString());
    }

    @Test
    public void test2() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File("D:/test.p12"));
        keyStore.load(instream, "1234567".toCharArray());
        //相信自己的CA和所有自簽名的證書
        SSLContext sslcontext = SSLContextBuilder.create()
                .loadKeyMaterial(keyStore, "1234567".toCharArray())
                .loadTrustMaterial(new TrustAllStrategy())
                .build();

        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
// 只允許使用TLSv1協議
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, hostnameVerifier);

        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpclient);

        String url = "https://localhost:8888/demo/oauth/token?username=user&password=12345&grant_type=password&scope=read&client_id=admin&client_secret=abc123";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);

        ResponseEntity<String> response = new RestTemplate(clientHttpRequestFactory).postForEntity(url, request, String.class);

        System.out.println(response.toString());
    }

    public class TrustAllStrategy implements TrustStrategy {
        /**
         * Implement strategy to always trust certificates.
         *
         * @see {org.apache.http.ssl.TrustStrategy} TrustStrategy
         */
        @Override
        public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            return true;
        }

    }

}
