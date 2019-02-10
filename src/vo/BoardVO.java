package vo;

public class BoardVO {
	//보드 테이블의 컬럼 = 변수
	
	int boardseq;
	String boardtitle;
	String boardcontents;
	String boardwriter;
	String boardtime;
	int boardviewcount;
	
	
	
	
	public BoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardVO(int boardseq, String boardtitle, String boardcontents, String boardwriter, String boardtime,
			int boardviewcount) {
		this.boardseq = boardseq;
		this.boardtitle = boardtitle;
		this.boardcontents = boardcontents;
		this.boardwriter = boardwriter;
		this.boardtime = boardtime;
		this.boardviewcount = boardviewcount;
	}
	
	public int getBoardseq() {
		return boardseq;
	}
	public void setBoardseq(int boardseq) {
		this.boardseq = boardseq;
	}
	public String getBoardtitle() {
		return boardtitle;
	}
	public void setBoardtitle(String boardtitle) {
		this.boardtitle = boardtitle;
	}
	public String getBoardcontents() {
		return boardcontents;
	}
	public void setBoardcontents(String boardcontents) {
		this.boardcontents = boardcontents;
	}
	public String getBoardwriter() {
		return boardwriter;
	}
	public void setBoardwriter(String boardwriter) {
		this.boardwriter = boardwriter;
	}
	public String getBoardtime() {
		return boardtime;
	}
	public void setBoardtime(String boardtime) {
		this.boardtime = boardtime;
	}
	public int getBoardviewcount() {
		return boardviewcount;
	}
	public void setBoardviewcount(int boardviewcount) {
		this.boardviewcount = boardviewcount;
	}
	@Override
	public String toString() {
		return "BoardVO [boardseq=" + boardseq + ", boardtitle=" + boardtitle + ", boardcontents=" + boardcontents
				+ ", boardwriter=" + boardwriter + ", boardtime=" + boardtime + ", boardviewcount=" + boardviewcount
				+ "]";
	}
	
	
	
	
}
