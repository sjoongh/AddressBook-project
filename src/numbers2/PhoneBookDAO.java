package numbers2;

import java.util.List;

public interface PhoneBookDAO {
	public List<PhoneBookVO> getList();
	public boolean insert(PhoneBookVO vo);
	public List<PhoneBookVO> find(String keyword);
	public boolean delete(Long id);
}
