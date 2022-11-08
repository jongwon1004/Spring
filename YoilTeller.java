package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller // 1. 원격 호출 가능한 프로그램 등록 
public class YoilTeller {
//	public static void main(String[] args) {
		@RequestMapping("/getYoil") // 2. URL 과 메서드 연결 (맵핑, mapping)
		public void main(HttpServletRequest request, HttpServletResponse response) throws IOException{	
		
		// 1. 입력									 HTTP 요청을 보낼떄 , Parameter 을 함께 끼워서 보낼 수 있음 
//		로그인할때 폼 내용이 바로 , HTTP  요청의 파라미터(Parameter)이 되는것임 
//		이러한 HTTP 요청을 받은 서버는 폼을 일단 확인할텐데 , 일단 Parameter 값을 얻어내야 함
		String year = request.getParameter("year");  // requerst.getParameter() << HTTP 요청의 파라미터 깂을 얻기위해 사용하는 메서드
		String month = request.getParameter("month");  // 
		String day = request.getParameter("day");
		
		int yyyy = Integer.parseInt(year);  // Parameter 값을 int로 바꿔줌 
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		// 2. 처리
		Calendar cal = Calendar.getInstance();   // getInstance() << 하나의 인스턴스만 가지고 공유해서 쓴다 , 생성자 private , 싱글톤 패턴 
		cal.set(yyyy, mm, dd);  // Parameter 값을 정수로 바꾼수를시간 설정해줌 
		
		int dayofWeek = cal.get(Calendar.DAY_OF_WEEK);  //  일0 월1 화2 수3 목4 금5 토6 일7 
		char yoil = " 일월화수목금토".charAt(dayofWeek);
		
		// 3. 출력
		response.setContentType("text/html"); // 응답해줄때 컨텐트 타입을 지정해줘야 html 인지 알겠지 ? 
		response.setCharacterEncoding("utf-8");  // POST 방식으로 보내는값이 한글일때 한글이 깨지지 않고 전달하기위한 코드 
//		response.setContentType("text/html; charset-utf-8");
		PrintWriter out = response.getWriter();  // response 객체에서 브라우저로의 출력 스트림을 얻는다.
		out.println(year + "년 " + month  + "월 " + day + "일은 ");
		out.println(yoil + "요일입니다. ");
	}

}
