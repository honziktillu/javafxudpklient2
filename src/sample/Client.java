package sample;

import java.io.IOException;
import java.net.*;

public class Client {

    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public Client() {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getLocalHost();
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String sendMsg(String msg) {
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 1234);
        try {
            socket.send(packet);
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            String[] conv = received.split(";");
            Main.characters.get(0).setX(Double.parseDouble(conv[0]));
            Main.characters.get(0).setY(Double.parseDouble(conv[1]));
            return received;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void close() {
        socket.close();
    }


}
