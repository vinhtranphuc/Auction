package socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import common.StringProcess;
import model.bo.AuctionCouponBO;

@ServerEndpoint(value = "/ServerEndPoint")
public class ServerEndPoint {
	
	AuctionCouponBO auctionCouponBO = new AuctionCouponBO();
	
	static Set<Session> allSession = Collections.synchronizedSet(new HashSet<>());

	@OnOpen
	public void handleOpen(Session session) throws IOException {
		
		allSession.add(session);
		
		System.out.println(StringProcess.toJSONArrayString(auctionCouponBO.getAuctionCouponList()));
			
		for (Session elementSession : allSession) {
			
			elementSession.getBasicRemote().sendText(StringProcess.toJSONArrayString(auctionCouponBO.getAuctionCouponList()));
		}
	}

	@OnMessage
	public void handleMessage(String message) throws IOException, EncodeException {

		if ("load-data".equals(message)) {

			for (Session session : allSession) {
				session.getBasicRemote().sendText(message);
				// session.getBasicRemote().sendText(jsonArray.toString());
			}
		}

	}

	@OnClose
	public void handleClose(Session session) {
		allSession.remove(session);
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}

}
