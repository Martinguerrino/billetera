package billetera.Modelo.Servicios;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import billetera.Modelo.DAO.MonedaDAO;
import org.json.JSONObject; // Necesita agregar la librería org.json para trabajar con JSON

import billetera.Auxiliar.Moneda;
public class ConsultarPrecioCripto extends TimerTask{
   private static final String URL_API = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";
   @Override
   public void run(){
       HttpClient cliente = HttpClient.newHttpClient();
       HttpRequest solicitud = HttpRequest.newBuilder()
               .uri(URI.create(URL_API))
               .GET()
               .build();
       try {
           HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
           if (respuesta.statusCode() == 200) {
               parsearYMostrarPrecios(respuesta.body());
           } else {
               System.out.println("Error: " + respuesta.statusCode());
           }
       } catch (IOException | InterruptedException e) {
           e.printStackTrace();
       }
   }
   

    
   private static void parsearYMostrarPrecios(String cuerpoRespuesta) {
       JSONObject json = new JSONObject(cuerpoRespuesta);
       List<Moneda> monedas = new ArrayList<>();

       System.out.println("Precios de Criptomonedas (en USD):");
       double precioBTC = json.getJSONObject("bitcoin").getDouble("usd");
       monedas.add(new Moneda("BITCOIN", (float) precioBTC));
       System.out.println("BTC: $" + precioBTC);
       double precioETH = json.getJSONObject("ethereum").getDouble("usd");
         monedas.add(new Moneda("ETHEREUM", (float) precioETH));
       System.out.println("ETH: $" + precioETH);
       double precioUSDC = json.getJSONObject("usd-coin").getDouble("usd");
        monedas.add(new Moneda("USDC", (float) precioUSDC));
       System.out.println("USDC: $" + precioUSDC);
       double precioUSDT = json.getJSONObject("tether").getDouble("usd");
        monedas.add(new Moneda("USDT", (float) precioUSDT));
       System.out.println("USDT: $" + precioUSDT);
       double precioDOGE = json.getJSONObject("dogecoin").getDouble("usd");
        monedas.add(new Moneda("DOGE", (float) precioDOGE));
       System.out.println("DOGE: $" + precioDOGE);
        ActualizarPrecios ap = new ActualizarPrecios(monedas);
        ap.Actualizacion();
   }
   
}