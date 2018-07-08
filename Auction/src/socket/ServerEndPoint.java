package socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import common.StringProcess;
import config.PrincipalWithSession;
import model.bo.AuctionCouponBO;

@ServerEndpoint(value = "/ServerEndPoint")
public class ServerEndPoint {
	
	// http session
	private HttpSession httpSession;
	
	// web socket sessions
	static Set<Session> allWebSocketSession = Collections.synchronizedSet(new HashSet<>());
	
	// auctionCouponBO
	private AuctionCouponBO auctionCouponBO;

	@OnOpen
	public void handleOpen(Session webSocketSession) throws IOException {
		
		// get http session form web socket
		httpSession = ((PrincipalWithSession) webSocketSession.getUserPrincipal()).getSession();
		
		System.out.println("ServerEndPoint session userName: "+httpSession.getAttribute("userName"));
		
		// save web socket sessions
		allWebSocketSession.add(webSocketSession);
		
		auctionCouponBO = new AuctionCouponBO();
		
		System.out.println(StringProcess.toJSONArrayString(auctionCouponBO.getAuctionCouponList()));
		
		webSocketSession.getBasicRemote()
					.sendText(StringProcess.toJSONArrayString(auctionCouponBO.getAuctionCouponList()));
	}

	@OnMessage
	public void handleMessage(String message) throws IOException, EncodeException {

		if ("load-data".equals(message)) {

			for (Session webSocketSession : allWebSocketSession) {
				webSocketSession.getBasicRemote().sendText(message);
			}
		}
	}

	@OnClose
	public void handleClose(Session webSocketSession) {
		allWebSocketSession.remove(webSocketSession);
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}

}
