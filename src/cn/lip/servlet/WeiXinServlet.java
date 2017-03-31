package cn.lip.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qq.weixin.mp.aes.*;

import java.io.*;

/**
 * Servlet implementation class WeiXinServlet
 */
public class WeiXinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public WeiXinServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw=response.getWriter();
//		pw.write("lip33是帅哥！");
		
		String sVerifyMsgSig = request.getParameter("msg_signature");
		String sVerifyTimeStamp = request.getParameter("timestamp");
		String sVerifyNonce = request.getParameter("nonce");
		String sVerifyEchoStr = request.getParameter("echostr");
		String sToken="lip";
		String sEncodingAESKey="n7aANVUYmqx2eIHlH9GRNTAqHhGXmH12kyCkLDwqSuD";
		String sCorpID="wx77d263bdb09a6281";
		WXBizMsgCrypt wxcpt = null;
		try {
			wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
		} catch (AesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sEchoStr="";
		try {
			sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
					sVerifyNonce, sVerifyEchoStr);
/*			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw=response.getWriter();*/
			pw.write(sEchoStr);
		} catch (Exception e) {
			//验证URL失败，错误原因请查看异常
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
