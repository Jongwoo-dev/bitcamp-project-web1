package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;

// 톰캣 서버가 실행할 수 있는 클래스는 반드시 Servlet 규격에 맞추어 제작해야 한다.
// 그러나 Servlet 인터페이스의 메서드가 많아서 구현하기 번거롭다.
// 그래서 AbstractServlet이라는 추상 클래스를 만들어서, 
// 이 클래스를 상속받아 간접적으로 Servlet 인터페이스를 구현하는 방식을 취한다.
// 이 클래스를 상속받게되면 오직 service() 메서드만 만들면 되기 때문에 코드가 편리하다.
@WebServlet("/teacher/list")
public class TeacherListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      ArrayList<Teacher> list = teacherDao.getList();
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>강사관리-목록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>강사 정보</h1>");
      out.println("<a href='form.html'>추가</a><br>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th><th>전화번호</th>"
          + "<th>나이</th><th>담당과목</th><th>경력</th><th>연봉</th><th>주소</th>");
      out.println("</tr>");
      
      for (Teacher teacher : list) {
        out.println("<tr>");
        out.printf("<td><a href='view?userId=%1$s'>%s</a></td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>"
            + "<td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>\n",
            teacher.getUserId(),
            teacher.getPassword(),
            teacher.getName(),
            teacher.getEmail(),
            teacher.getTel(),
            teacher.getAge(),
            teacher.getSubject(),
            teacher.getCarrer(),
            teacher.getSalary(),
            teacher.getAddress());
        
        out.println("</tr>");
      }
      out.println("</table>");
      out.println("</body>");
      out.println("</html>");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
