package com.zsolis.pathpainter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.zsolis.pathpainter.common.Position;

public class InfoSender {
	private Socket socket;
	
	public InfoSender(String servAddr ,int servPort) throws IOException {
		socket = new Socket(servAddr ,servPort);
	}
	
	public void sendPath(List<Position> positionList) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream (socket.getOutputStream());
		oos.writeObject(positionList);
		oos.close();
	}
	
	/*
	public static void main (String arg[]) throws IOException {
		InfoSender mySender = new InfoSender("192.168.0.101", 8111);
		List<Position> path = new ArrayList<Position>();
		path.add(new Position (20,10));
		path.add(new Position (20,100));
		path.add(new Position (20,200));
		path.add(new Position (100,200));
		path.add(new Position (250,200));
		mySender.sendPath(path);
	}*/
}
