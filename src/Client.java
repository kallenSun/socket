import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <Description> <br>
 *
 * @author kallensun <br>
 * @CreateDate 2020/04/11 <br>
 */
public class Client extends Thread {

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 8888);
        client.start();
    }

    public Socket socket;

    public BufferedReader in;

    public PrintWriter out;

    public Client(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new SendMessage(out).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = "";
                msg = in.readLine();
                if (StringUtils.isNotBlank(msg)) {
                    System.out.println("服务端的消息为: " + msg);
                }
            }
        }
        catch (IOException e) {
            try {
                socket.close();
                System.out.println("断开连接");
                in = null;
                socket = null;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
