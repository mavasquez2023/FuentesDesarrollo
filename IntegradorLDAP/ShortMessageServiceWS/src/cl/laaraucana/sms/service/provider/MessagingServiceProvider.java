package cl.laaraucana.sms.service.provider;

import cl.laaraucana.sms.utils.Networking;
import cl.laaraucana.sms.utils.Configuration;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessagingServiceProvider {

    private static final Logger logger = Logger.getLogger(MessagingServiceProvider.class);
    private static final Networking networkTools = new Networking();

    public String sendMessage(String requestBody, String authorizationKey, String endpoint) {
        String result = "";
        try {
            URL url = new URL(endpoint);
            HttpURLConnection httpClient;

            if (networkTools.isProxyEnabled()) {
                httpClient = (HttpURLConnection) url.openConnection(networkTools.getProxy());
            } else {
                httpClient = (HttpURLConnection) url.openConnection();
            }

            httpClient.setRequestMethod("POST");
            httpClient.setDoOutput(true);

            // httpClient.setRequestProperty("Accept", "application/json");
            httpClient.setRequestProperty("Content-Type", "application/json; utf-8");
            httpClient.setRequestProperty("Accept", "application/json");
            httpClient.setRequestProperty("Api-key", Configuration.apiKey);
            httpClient.setRequestProperty("Authorization", "Basic " + authorizationKey);

            // Cuerpo del mensaje a enviar al proveedor de servicio
            OutputStream os = httpClient.getOutputStream();
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);

            if (httpClient.getResponseCode() != 200) {
                logger.error(String.format("Error sending message using endpoint %s response code %s",
                        endpoint, httpClient.getResponseCode()));
            } else {
                logger.info(String.format("Response: %s", httpClient.getResponseCode()));
            }

            BufferedReader responseReader = new BufferedReader(new InputStreamReader((httpClient.getInputStream())));
            StringBuilder resultBuilder = new StringBuilder();
            String output;
            while ((output = responseReader.readLine()) != null) {
                logger.info(output);
                resultBuilder.append(output);
            }
            httpClient.disconnect();
            result = resultBuilder.toString();

        } catch (IOException e) {
            logger.error("Exception during service provider call", e);
            e.printStackTrace();
        }
        return result;
    }
}