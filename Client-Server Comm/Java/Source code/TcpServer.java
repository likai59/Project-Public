import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;


public class TcpServer {
    public static void main(String[] args) {
        try (var ServerSocket = new ServerSocket(9000)) {
            System.out.println("Server started, waitting for connecting...");

            while (true) {
                var clientSocket = ServerSocket.accept();
                System.out.println("Client connected");
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void handleClient(Socket clientSocket) {
        Random random = new Random();
        try (var out = clientSocket.getOutputStream()) {
            while (true) {
                String data = String.format(
                        "{\"ECG\":%d,\"BP\":%d,\"Pulse\":%d}\n",
                        random.nextInt(40, 70),
                        random.nextInt(100, 170),
                        random.nextInt(110, 155)
                );
                out.write(data.getBytes(StandardCharsets.UTF_8));
                out.flush();
                System.out.print("client receive:" + data);

                Thread.sleep(1000);
            }

        } catch (Exception e) {
            System.out.println("Client disconnected" + e.getMessage());
        }
    }
}