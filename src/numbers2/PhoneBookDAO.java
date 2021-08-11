package numbers2;

import java.util.List;

public interface PhoneBookDAO {
	public List<PhoneBookVO> getList(); // 리스트
	public boolean insert(PhoneBookVO vo); // 추가
	public List<PhoneBookVO> find(String keyword); // 검색
	public boolean delete(Long id);
}
