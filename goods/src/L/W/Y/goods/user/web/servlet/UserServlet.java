package L.W.Y.goods.user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import L.W.Y.goods.user.domain.User;
import L.W.Y.goods.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;


/*
 * �û�ģ���web�� 
 * 
 */
public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();
	
	/**
	 * ajax�û����Ƿ�ע��У��
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateLoginname(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. ��ȡ�û���
		 */
		String loginname = req.getParameter("loginname");
		/*
		 * 2. ͨ��service�õ�У����
		 */
		boolean b = userService.ajaxValidateLoginname(loginname);
		/*
		 * 3. �����ͻ���
		 */
		resp.getWriter().print(b);
		return null;
	}
	
	/**
	 * ajax Email�Ƿ�ע��У��
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateEmail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. ��ȡEmail
		 */
		String email = req.getParameter("email");
		/*
		 * 2. ͨ��service�õ�У����
		 */
		boolean b = userService.ajaxValidateEmail(email);
		/*
		 * 3. �����ͻ���
		 */
		resp.getWriter().print(b);
		return null;
	}
	
	/**
	 * ajax��֤���Ƿ���ȷУ��
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateVerifyCode(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. ��ȡ������е���֤��
		 */
		String verifycode = req.getParameter("verifycode");
		/*
		 * 2. ��ȡͼƬ����ʵ��У����
		 */
		String vcode = (String) req.getSession().getAttribute("vCode");
		/*
		 * 3. ���к��Դ�Сд�Ƚϣ��õ����
		 */
		boolean b = verifycode.equalsIgnoreCase(vcode);
		/*
		 * 4. ���͸��ͻ���
		 */
		resp.getWriter().print(b);
		return null;
	}

	/**
	 * ע�Ṧ��
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. ��װ�����ݵ�User����
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		/*
		 * 2. У��֮, ���У��ʧ�ܣ����������Ϣ�����ص�regist.jsp��ʾ
		 */
		Map<String,String> errors = validateRegist(formUser, req.getSession());
		if(errors.size() > 0) {
			req.setAttribute("form", formUser);
			req.setAttribute("errors", errors);
			return "f:/account.jsp";
		}
		/*
		 * 3. ʹ��service���ҵ��
		 */
		userService.regist(formUser);
		/*
		 * 4. ����ɹ���Ϣ��ת����msg.jsp��ʾ��
		 */
		req.setAttribute("code", "success");
		req.setAttribute("msg", "ע�Ṧ�ܣ������ϵ����伤�");
		return "f:/msg.jsp";
	}
	
	/*
	 * ע��У��
	 * �Ա����ֶν������У�飬����д���ʹ�õ�ǰ�ֶ�����Ϊkey��������ϢΪvalue�����浽map��
	 * ����map
	 */
	private Map<String,String> validateRegist(User formUser, HttpSession session) {
		Map<String,String> errors = new HashMap<String,String>();
		/*
		 * 1. У���¼��
		 */
		String loginname = formUser.getLoginname();
		if(loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "�û�������Ϊ�գ�");
		} else if(loginname.length() < 3 || loginname.length() > 20) {
			errors.put("loginname", "�û������ȱ�����3~20֮�䣡");
		} else if(!userService.ajaxValidateLoginname(loginname)) {
			errors.put("loginname", "�û����ѱ�ע�ᣡ");
		}
		
		/*
		 * 2. У���¼����
		 */
		String loginpass = formUser.getLoginpass();
		if(loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "���벻��Ϊ�գ�");
		} else if(loginpass.length() < 3 || loginpass.length() > 20) {
			errors.put("loginpass", "���볤�ȱ�����3~20֮�䣡");
		}
		
		/*
		 * 3. ȷ������У��
		 */
		String reloginpass = formUser.getReloginpass();
		if(reloginpass == null || reloginpass.trim().isEmpty()) {
			errors.put("reloginpass", "ȷ�����벻��Ϊ�գ�");
		} else if(!reloginpass.equals(loginpass)) {
			errors.put("reloginpass", "�������벻һ�£�");
		}
		
		/*
		 * 4. У��email
		 */
		String email = formUser.getEmail();
		if(email == null || email.trim().isEmpty()) {
			errors.put("email", "Email����Ϊ�գ�");
		} else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
			errors.put("email", "Email��ʽ����");
		} else if(!userService.ajaxValidateEmail(email)) {
			errors.put("email", "Email�ѱ�ע�ᣡ");
		}
		
		/*
		 * 5. ��֤��У��
		 */
		String verifycode = formUser.getVerifycode();
		String vcode = (String) session.getAttribute("vCode");
		if(verifycode == null || verifycode.trim().isEmpty()) {
			errors.put("verifycode", "��֤�벻��Ϊ�գ�");
		} else if(!verifycode.equalsIgnoreCase(vcode)) {
			errors.put("verifycode", "��֤�����");
		}
		
		return errors;
	}
	
	/**
	 * 
	 * �����
	 * @param req 
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public String activation(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		System.out.println("actvation....");
		return null;
	}
}
