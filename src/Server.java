import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * <Description> <br>
 *
 * @author kallensun <br>
 * @CreateDate 2020/04/11 <br>
 */
public class Server extends Thread {

    public static void main(String[] args) {
        Server server = new Server(8888);
        server.start();
    }

    private ServerSocket serverSocket = null;

    private Socket clientSocket = null;

    private BufferedReader in = null;

    private PrintWriter out = null;

    public Server(int port) {
        try {
            System.out.println("等待客户端建立连接............");
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            System.out.println("客户端建立连接.............");
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            new SendMessage(out).start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 接受客户端信息的线程
     */
    @Override
    public void run() {
        try {
            String msg = "";
            while (true) {
                msg = in.readLine();
                if (StringUtils.isNotBlank(msg)) {
                    System.out.println("客户端的消息为: " + msg);
                }
            }
        }
        catch (IOException e) {
            try {
                clientSocket.close();
                serverSocket.close();
                System.out.println("断开连接");
                in = null;
                out = null;
            }catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}