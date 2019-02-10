package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import vo.BoardVO;

public class BoardDAO {
	
	public int getTotalBoard() { //전체 개시물 개수를 가져오기
		int total = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe", "board", "board");
			
			System.out.println("연결성공");
			
			String sql = "select count(*) from board";
			PreparedStatement pt = con.prepareStatement(sql);
			ResultSet rs = pt.executeQuery();
			rs.next();
			total = rs.getInt("count(*)");
			con.close();
			System.out.println("연결해제");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return total;
		
	}
	
	
	
	//1. 게시물 리스트를 불러오는  sql 메소드
	public ArrayList<BoardVO> getBoardList(){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		//디비에 있는 보드 내용을 리스트로 가져온다.
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe", "board", "board");
			
			System.out.println("연결성공");
			
			String sql = "select boardseq, boardtitle, boardwriter, "
					+ "to_char(boardtime,'yyyy-mm-dd hh24:mi:ss') as boardtime, boardviewcount "
					+ "from board order by boardtime desc";
			PreparedStatement pt = con.prepareStatement(sql);
			ResultSet rs = pt.executeQuery();
			
			while(rs.next() == true) {
				int seq = rs.getInt("boardseq");
				String title = rs.getString("boardtitle");
				String writer = rs.getString("boardwriter");
				String time = rs.getString("boardtime");
				int viewcount = rs.getInt("boardviewcount");
				BoardVO vo = new BoardVO();
				vo.setBoardseq(seq);
				vo.setBoardtitle(title);
				vo.setBoardwriter(writer);
				vo.setBoardtime(time);
				vo.setBoardviewcount(viewcount);
				list.add(vo);
			}
			con.close();
			System.out.println("연결해제");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	//////////////////////////////////////
	public ArrayList<BoardVO> getBoardList(int page){
		ArrayList<BoardVO> list = 
				new ArrayList<BoardVO>();
		try {
			Class.forName
			("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
			("jdbc:oracle:thin:@localhost:1521:xe"
			, "board", "board");
			System.out.println("연결성공");
	String sql = 
	"select r, boardseq, boardtitle, "
	+" boardwriter, boardtime, boardviewcount"
	+" from"
	+"	(select rownum r, boardseq, boardtitle, "
	+"   boardwriter, boardtime, boardviewcount"
	+"	from "
	+"	(select * from board order by "
	+"   boardtime desc)"
	+"	)"
	+" where r>= ? and r<=?";	

			PreparedStatement pt =
					con.prepareStatement(sql);
			//1페이지당 2개 : page변수=2페이지
			int start = (page - 1)*2 + 1;
			int end = page * 2;
			pt.setInt(1, start);
			pt.setInt(2,  end);
			ResultSet rs = pt.executeQuery();
			while(rs.next()) {
				int seq = rs.getInt("boardseq");
				String title = rs.getString("boardtitle");
				String writer = rs.getString("boardwriter");
				String time = rs.getString("boardtime");
				int viewcount = rs.getInt("boardviewcount");
				BoardVO vo = new BoardVO();
				vo.setBoardseq(seq);
				vo.setBoardtitle(title);
				vo.setBoardwriter(writer);
				vo.setBoardtime(time);
				vo.setBoardviewcount(viewcount);
				list.add(vo);
			}
			
			con.close();
			System.out.println("연결해제성공");			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//2. 내가 요청한 게시물 번호에 대한 상세 내용을 불러오는 sql 메소드
	public BoardVO getBoardDetail(int seq){
		
		int bseq = 0;
		String title = "";
		String contents = "";
		String writer = "";
		String time = "";
		int viewcount = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe", "board", "board");
			
			System.out.println("연결성공");
			
			String sql = "select boardseq, boardtitle, boardcontents, boardwriter, "
					+ "to_char(boardtime,'yyyy-mm-dd hh24:mi:ss') as boardtime, "
					+ "boardviewcount from board where boardseq = ?";
			PreparedStatement pt = con.prepareStatement(sql); //String을 디비에 저장할 수 있는 객체로 만듦
			// sql을 저장하고 데이터베이스로 전송하는 역할의 객체를 만들어준다. PreparedStatement 타입으로 리턴
			
			pt.setInt(1, seq);
			
			//3. SQL 결과 검색
			
			ResultSet rs = pt.executeQuery();
			//테이블 구조를 리턴한다.
			
			while(rs.next()) {
				bseq = rs.getInt("boardseq");
				title = rs.getString("boardtitle");
				contents = rs.getString("boardcontents");
				writer = rs.getString("boardwriter");
				time = rs.getString("boardtime");
				viewcount = rs.getInt("boardviewcount");
			}
			
			con.close();
			System.out.println("연결해제");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		BoardVO vo = new BoardVO(bseq,title,contents,writer,time,viewcount);	
		//게시물 상세 내용을 보여주는 메소드
		return vo;
	}
	
	//3. 게시물 저장 sql 메소드
	public void insertBoard(BoardVO vo){
		//게시물 저장
		 try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe", "board", "board");
			
			System.out.println("연결성공");
			
			String sql = "insert into board values ((select max(boardseq)+1 from board), ?, ?, ?, sysdate, 0)";
			PreparedStatement pt = con.prepareStatement(sql); //String을 디비에 저장할 수 있는 객체로 만듦
			// sql을 저장하고 데이터베이스로 전송하는 역할의 객체를 만들어준다. PreparedStatement 타입으로 리턴
			
			
			pt.setString(1, vo.getBoardtitle()); 
			pt.setString(2, vo.getBoardcontents()); 
			pt.setString(3, vo.getBoardwriter()); 
			
			//3. SQL 결과 검색
			int insertRow = pt.executeUpdate(); 
			
			//pt에 저장된 sql을 디비로 전송할테니 실행시켜 달라는 의미
			// 몇 개의 행이  insert 됐는지 개수를 리턴
			System.out.println("삽입된 행의 개수는 : " + insertRow);
			
			con.close();
			System.out.println("연결해제");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateViewCount(int seq) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe", "board", "board");
			
			System.out.println("연결성공");
			
			String sql = "update board set boardviewcount = (boardviewcount + 1) where boardseq = ?";
			PreparedStatement pt = con.prepareStatement(sql); //String을 디비에 저장할 수 있는 객체로 만듦
			// sql을 저장하고 데이터베이스로 전송하는 역할의 객체를 만들어준다. PreparedStatement 타입으로 리턴
			
			pt.setInt(1, seq);
			
			//3. SQL 결과 검색
			int insertRow = pt.executeUpdate(); 
			
			//pt에 저장된 sql을 디비로 전송할테니 실행시켜 달라는 의미
			// 몇 개의 행이  insert 됐는지 개수를 리턴
			System.out.println("삽입된 행의 개수는 : " + insertRow);
	
			con.close();
			System.out.println("연결해제");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//4. 게시물 수정 sql 메소드
	public void updateBoard(BoardVO vo){
			//게시물 저장
			//수정된 게시물 수 리턴?
			try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe", "board", "board");
			
			System.out.println("연결성공");
			
			String sql = "update board set boardcontents = ?, boardtitle = ? where boardseq = ?";
			PreparedStatement pt = con.prepareStatement(sql); //String을 디비에 저장할 수 있는 객체로 만듦
			// sql을 저장하고 데이터베이스로 전송하는 역할의 객체를 만들어준다. PreparedStatement 타입으로 리턴
			
			
			
			pt.setString(1, vo.getBoardcontents());
			pt.setString(2, vo.getBoardtitle());
			pt.setInt(3, vo.getBoardseq());
			
			//3. SQL 결과 검색
			int updateRow = pt.executeUpdate(); 
			
			//pt에 저장된 sql을 디비로 전송할테니 실행시켜 달라는 의미
			// 몇 개의 행이  insert 됐는지 개수를 리턴
			System.out.println("변경된 행의 개수는 : " + updateRow);
			
			con.close();
			System.out.println("연결해제");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	//5. 게시물 삭제 sql 메소드
	public void deleteBoard(BoardVO vo){
		//게시물 저장
		// 삭제된 게시물 수 리턴?
		 try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe", "board", "board");
			
			System.out.println("연결성공");
			
			String sql = "delete from board where boardseq = ?";
			PreparedStatement pt = con.prepareStatement(sql); //String을 디비에 저장할 수 있는 객체로 만듦
			// sql을 저장하고 데이터베이스로 전송하는 역할의 객체를 만들어준다. PreparedStatement 타입으로 리턴
				
			pt.setInt(1, vo.getBoardseq());
			
			//3. SQL 결과 검색
			int deleteRow = pt.executeUpdate(); 
			
			//pt에 저장된 sql을 디비로 전송할테니 실행시켜 달라는 의미
			// 몇 개의 행이  insert 됐는지 개수를 리턴
			System.out.println("변경된 행의 개수는 : " + deleteRow);		
			con.close();
			System.out.println("연결해제");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
