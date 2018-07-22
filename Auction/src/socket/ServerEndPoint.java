package socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringUtils;

import common.StringProcess;
import config.PrincipalWithSession;
import model.bo.AuctionCouponBO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@ServerEndpoint(value = "/ServerEndPoint")
public class ServerEndPoint {

	// http session
	private HttpSession httpSession;

	// web socket sessions
	static Set<Session> allSocketSession = Collections.synchronizedSet(new HashSet<>());

	// auctionCouponBO
	private AuctionCouponBO auctionCouponBO;
	
	private String userNameSession;
	
	private String memberIDsession;
	
	private JSONObject obj;
	
	private JSONArray jsonArray;

	
	@OnOpen
	public void handleOpen(Session clientSocketSession) throws IOException {

		// get http session form web socket
		httpSession = ((PrincipalWithSession) clientSocketSession.getUserPrincipal()).getSession();
		
		userNameSession = (String) httpSession.getAttribute("userName");
		
		auctionCouponBO = new AuctionCouponBO();
		
		if(!StringUtils.equals("", userNameSession) && userNameSession != null){
			memberIDsession = auctionCouponBO.getMemberIDSession(userNameSession);
		}
		
		
		System.out.println("ServerEndPoint session userName: " + userNameSession);

		// save web socket sessions
		allSocketSession.add(clientSocketSession);

		auctionCouponBO = new AuctionCouponBO();

		createObjectRes("severResponseAllAuctionList","auctionList",auctionCouponBO.getAuctionCouponList());
		
		sendDataSingleClient(clientSocketSession,obj);
	}
	
	@OnMessage
	public void handleMessage(String message, Session clientSocketSession) throws IOException, EncodeException {

		System.out.println("Input message: " + message);

		if ("severUpdateAuctionList".equals(message)) {
			
			createObjectRes("severResponseAllAuctionList","auctionList",auctionCouponBO.getAuctionCouponList());
			
			sendDataAllClient(obj);

			return;
		}

		String[] parts = message.split("-");
		String signal = "";
		String productIDrequest = "";
		String orderPriceRequest = "";

		try {
			signal = parts[0];
			productIDrequest = parts[1];
			orderPriceRequest = parts[2];
		} catch (Exception e) {
		}
		
		if ("clientLoadOrderListSingleProduct".equals(signal) && !"".equals(productIDrequest)) {
			
			createObjectRes("severResponseOrderListSingleProduct","orderListSingleProduct",auctionCouponBO.getOrderList(productIDrequest));
			sendDataSingleClient(clientSocketSession, StringProcess.toJSONArrayString(obj));

			return;
		}
		
		if(StringUtils.equals("", memberIDsession) || memberIDsession == null){
			
			createObjectRes("orderFail", "messsage", "Bạn chưa đăng nhập");
			sendDataSingleClient(clientSocketSession, obj);
			
			return;
		}
		
		if ("cilentOrderPrice".equals(signal) && !"".equals(productIDrequest)
				&& StringProcess.isNumeric(orderPriceRequest)
				&& auctionCouponBO.saveOrderPrice(memberIDsession, productIDrequest, orderPriceRequest)) {

			createObjectRes("orderSuccess", "messsage", "Đặt thành công!");
			sendDataSingleClient(clientSocketSession, obj);

			createObjectRes("severResponseOrderListSingleProduct", "orderListSingleProduct",
					auctionCouponBO.getOrderList(productIDrequest));	
			sendDataAllClient(obj);

			return;

		} else {

			createObjectRes("orderFail", "messsage", "Thất bại!");
			sendDataSingleClient(clientSocketSession, obj);
			return;
		}
		
	}

	@OnClose
	public void handleClose(Session clientSocketSession) {
		allSocketSession.remove(clientSocketSession);
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
	
	public void createObjectRes(String signal,String dataKey,Object data) {
		
		obj = new JSONObject();
		
		obj.put("signal", signal);
		
		if(data instanceof List) {
			
		    jsonArray = JSONArray.fromObject(data);
			obj.put(dataKey, jsonArray);
			return;
		}
		
		if(data instanceof String) {
			obj.put(dataKey, data);
		}	
	}
	
	
	public void sendDataSingleClient(Session clientSocketSession,Object obj) throws IOException{
		
		if (clientSocketSession.isOpen()) {
			clientSocketSession.getBasicRemote().sendText(StringProcess.toJSONArrayString(obj));
		}
	}
	
	public void sendDataAllClient(Object obj) throws IOException{
		
		for (Session socketSessionElement : allSocketSession) {
			if (socketSessionElement.isOpen()) {
				socketSessionElement.getBasicRemote().sendText(StringProcess.toJSONArrayString(obj));
			}
		}
	}

}