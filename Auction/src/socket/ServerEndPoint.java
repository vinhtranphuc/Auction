package socket;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import config.Validate;
import home.model.bo.HomeBO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@ServerEndpoint(value = "/ServerEndPoint")
public class ServerEndPoint {

	// http session
	private HttpSession httpSession;

	// web socket sessions
	static Set<Session> allSocketSession = Collections.synchronizedSet(new HashSet<>());

	// auctionCouponBO
	private HomeBO homeBO;
	
	private String userNameSession;
	
	private String memberIDsession;
	
	String loginFlag = "false";
	
	private JSONObject obj;
	
	private JSONArray jsonArray;
	
	private Float highestPriceForProduct;
	private Float stepPriceForProduct;
	
	private String memberIDwinner;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@OnOpen
	public void handleOpen(Session clientSocketSession) throws IOException {

		// get http session form web socket
		httpSession = ((PrincipalWithSession) clientSocketSession.getUserPrincipal()).getSession();
		
		userNameSession = (String) httpSession.getAttribute("userName");
		
		homeBO = new HomeBO();
		
		if(Validate.isExistsData(userNameSession)){
			memberIDsession = homeBO.getMemberIDSession(userNameSession);
			loginFlag = "true";
		}
		
		System.out.println("ServerEndPoint session userName: " + userNameSession);
		System.out.println("ServerEndPoint session memberIDsession: " + memberIDsession);

		// save web socket sessions
		allSocketSession.add(clientSocketSession);

		homeBO = new HomeBO();

		createObjectRes(loginFlag,"severResponseAllAuctionList","auctionList",homeBO.getAuctionCouponList());
		
		sendDataSingleClient(clientSocketSession,obj);
	}
	
	@OnMessage
	public void handleMessage(String message, Session clientSocketSession) throws IOException, EncodeException {

		System.out.println("Input message: " + message);
		
		if(StringUtils.equals("", memberIDsession) || memberIDsession == null){

			createObjectRes(loginFlag,"orderFail", "messsage", "Bạn chưa đăng nhập");
			sendDataSingleClient(clientSocketSession, obj);
			
			return;
		}

		if ("severUpdateAuctionList".equals(message)) {
			
			createObjectRes(loginFlag,"severResponseAllAuctionList","auctionList",homeBO.getAuctionCouponList());
			
			sendDataAllClient(obj);

			return;
		}

		String[] parts = message.split("-");
		String signal = "";
		String productIDrequest = "";
		String orderPriceRequest = "";
		String productNameSearch = "";
		try {
			signal = parts[0];
			productIDrequest = parts[1];
			orderPriceRequest = parts[2];
		} catch (Exception e) {
		}
		
		if("searchProduct".equals(signal)) {
			
			productNameSearch = parts[2];
			
			createObjectRes(loginFlag,"severResponseAllAuctionList","auctionList",homeBO.getAuctionCouponList(productNameSearch));
			
			sendDataSingleClient(clientSocketSession,obj);

			return;
			
		}
		
		if ("clientLoadOrderListSingleProduct".equals(signal) && !"".equals(productIDrequest)) {
			
			createObjectRes(loginFlag,"severResponseOrderListSingleProduct","orderListSingleProduct",homeBO.getOrderList(productIDrequest));
			sendDataSingleClient(clientSocketSession, StringProcess.toJSONArrayString(obj));

			return;
		}
		
		if(StringUtils.equals("", memberIDsession) || memberIDsession == null){
			
			createObjectRes(loginFlag,"orderFail", "messsage", "Bạn chưa đăng nhập");
			sendDataSingleClient(clientSocketSession, obj);
			
			return;
		}
		
		if ("cilentOrderPrice".equals(signal) && !"".equals(productIDrequest)
				&& StringProcess.isNumeric(orderPriceRequest)) {
			
			stepPriceForProduct = homeBO.getStepPriceBaseProduct(productIDrequest);
			System.out.println("stepPriceForProduct :"+stepPriceForProduct);
			
			highestPriceForProduct = homeBO.getHighestPrice(productIDrequest);
			System.out.println("highestPriceForProduct :"+highestPriceForProduct);
			
			Float allowPrice = stepPriceForProduct + highestPriceForProduct;
			
			System.out.println("allowPrice "+allowPrice);
			
			memberIDwinner = homeBO.getMemberIDWinner(productIDrequest);
			
			if(StringUtils.equals(memberIDsession, memberIDwinner)) {
				
				createObjectRes(loginFlag,"orderFail", "messsage", "Bạn không được đặt 2 lần liên tiếp!");
				
				sendDataSingleClient(clientSocketSession, obj);
				
				return;
			}

			if(Double.parseDouble(orderPriceRequest) < (allowPrice)){
				
				createObjectRes(loginFlag,"orderFail", "messsage", "Giá không hợp lệ");
				
				sendDataSingleClient(clientSocketSession, obj);
				
				return;
			}
			
			if (homeBO.saveOrderPrice(memberIDsession, productIDrequest, orderPriceRequest)) {

				createObjectRes(loginFlag,"orderSuccess", "messsage", "Đặt thành công!");
				
				sendDataSingleClient(clientSocketSession, obj);

				createObjectRes(loginFlag,"severResponseOrderListSingleProduct", "orderListSingleProduct",
						homeBO.getOrderList(productIDrequest));

				sendDataAllClient(obj);
			}

			return;

		} else {

			createObjectRes(loginFlag,"orderFail", "messsage", "Thất bại!");
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
	
	public void createObjectRes(String loginFlag,String signal,String dataKey,Object data) {
		
		obj = new JSONObject();
		
		obj.put("loginFlag", loginFlag);
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
	
	public void sendAuctionListAllClient() {
		createObjectRes(loginFlag,"severResponseAllAuctionList","auctionList",homeBO.getAuctionCouponList());
		try {
			sendDataAllClient(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}