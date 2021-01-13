import java.io.PrintWriter;
import java.util.Scanner;

/**
 * <Description> <br>
 *
 * @author kallensun <br>
 * @CreateDate 2020/04/11 <br>
 */
public class SendMessage extends Thread {

    private PrintWriter out = null;

    private  Scanner sc = new Scanner(System.in);

    public SendMessage(PrintWriter out){
        this.out = out;
    }

    @Override
    public void run() {
        while (sc.hasNext()) {
            String msg = sc.next();
            out.println(msg);
        }
    }
}
