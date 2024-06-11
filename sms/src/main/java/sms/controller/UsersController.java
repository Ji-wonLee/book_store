package sms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sms.dto.UsersDto;
import sms.dao.UsersDao;

@Controller
public class UsersController {

	@Autowired
	UsersDao usersDao;
	HashMap<String, Object> param = new HashMap<String, Object>();
	//�α��� -> ȭ�� �̵�
	@RequestMapping(value = "/mainLgn.do")
	public String mainLogin(@RequestParam("userId") String userId,
			@RequestParam("userPass") String userPass,ModelMap model,
			HttpServletRequest req) throws Exception{

		param.put("user_id", userId);
		param.put("passwd", userPass);

		String webStt = "";
		String loginChk = usersDao.selectLoginCheck(param);// ����� �̸�

		if(loginChk == null) {
			//��ư�� �α��� â����?
			model.addAttribute("userLoginStt" , "fail");
			webStt = "redirect:index.jsp";
		}else{
			//����� id ���� ����
			HttpSession session=req.getSession();
			session.setAttribute("user_id",userId);
			
			model.addAttribute("userLoginStt" , "success");
			String clientChk = usersDao.selectCheckClient(param); //gname
			model.addAttribute("userName" , loginChk);
			model.addAttribute("userClientStt" , clientChk);
			model.addAttribute("user_id" , userId);

			if(clientChk.equals("admin")) {   //������
				webStt =  "menu/admin";
			}else { //�����
				webStt = "redirect:/customermain";
			}
		}
		return webStt;
	}
	//���� ���� JSP �̵�
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void getRegister() throws Exception{
	}
	//���̵� �ߺ� üũ
	@ResponseBody
	@RequestMapping(value="/idChk", method = RequestMethod.POST)
	public int idChk(String id) throws Exception {
		int result = usersDao.selectIdCheck(id);
		return result;
	}
	//���� ȸ������
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String postRegister(@RequestParam("userId") String userId,
			@RequestParam("userPass") String userPass,
			@RequestParam("userAddr") String userAddr,
			@RequestParam("userName") String userName,
			@RequestParam("userCall") String userCall,
			ModelMap model, UsersDto vo) throws Exception {

		param.put("user_id", userId);
		param.put("passwd", userPass);
		param.put("address", userAddr);
		param.put("name", userName);
		param.put("phonenum", userCall);
		param.put("grade_no", 1);
		param.put("gname", "�Ϲ�");

		int idChkStt = usersDao.selectIdCheck(userId);// 1�̸� ����

		String webCtrl = "";

		if(idChkStt == 1) {
			model.addAttribute("idChkStt" , idChkStt);
			return "user/join";
		} else if(idChkStt == 0) {
			int cntInsert = usersDao.insertUsers(param);
			return "redirect:index.jsp";
		}

		return webCtrl;
	}
	//ȸ������ ��������
	@RequestMapping(value = "/toJoin", method = RequestMethod.GET)
	public String toJoin() {
		
		return "user/join";
	}
	//����� ��� ���
	@RequestMapping(value = "/admStt", method = RequestMethod.GET)
	public String admStt(ModelMap model) throws Exception {
		List<UsersDto> userList = usersDao.selectUsersList();
		model.addAttribute("userList", userList);
		return "user/userList";
	}
	//�� ����
	@RequestMapping(value = "/myInfo", method = RequestMethod.GET)
	public String myInfo(ModelMap model,HttpServletRequest req) throws Exception {

		//String myInfoId = req.getParameter("userId");
		//�������� ��������
		HttpSession session=req.getSession();
		String myInfoId = (String) session.getAttribute("user_id");
		model.addAttribute("userId", myInfoId);

		List<UsersDto> myInfoList = usersDao.selectMyInfo(myInfoId);
		model.addAttribute("myInfoList", myInfoList);
		return "user/myInfo";
	}
	//����� ���� ����
	@RequestMapping(value = "/updateMyInfo", method = RequestMethod.GET)
	public String updateMyInfo(ModelMap model,HttpServletRequest req,
			@RequestParam("userPass") String userPass,
			@RequestParam("userAddr") String userAddr,
			@RequestParam("userCall") String userCall,UsersDto vo, HttpSession session
			) throws Exception {
		Object sid = session.getAttribute("user_id");
		
		List<UsersDto> userInfo = usersDao.selectMyInfo(String.valueOf(sid));
		
		String id = userInfo.toString().split(":")[0];
		String idSpl = id.substring(1);
		String passwd = userInfo.toString().split(":")[2];
		String addr = userInfo.toString().split(":")[3];
		String callNum = userInfo.toString().split(":")[6];
		String callNumSpl = callNum.split("]")[0];

		if(userPass.trim().equals("")) {
			userPass = passwd;
		}
		
		if(userAddr.trim().equals("")) {
			userAddr = addr;
		}

		if(String.valueOf(userCall).trim().equals("")) {
			userCall = callNumSpl;
		}
		
		param.put("user_id", idSpl);
		param.put("passwd", userPass);
		param.put("address", userAddr);
		param.put("phonenum", userCall);
		param.put("grade_no", 1);
		int userLogin = usersDao.updateMyInfo(param);
		
		// ���� 
		return "redirect:/customermain";
	}
}