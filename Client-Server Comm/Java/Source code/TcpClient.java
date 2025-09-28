import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TcpClient {
    public static void main(String[] args) {
        try{var socket = new Socket("127.0.0.1",9000);
            var reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            System.out.println("Serverr connected, started to receiving data...");

            String line;
            while ((line = reader.readLine()) != null){
                System.out.println("data received" + line);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }

    }
}