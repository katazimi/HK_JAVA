import java.util.List;

import com.hk.dao.AnsDao;
import com.hk.dto.AnsDto;

public class mainClass {

	public static void main(String[] args) {
		AnsDao dao = new AnsDao();
		AnsDto dto = new AnsDto();
		
		dto.setId("hk");
		dto.setTitle("제목입니다.");
		dto.setContent("글 내용입니다.");
	
		
		
		
		

	}

}
