package kr.or.dgit.phonebookv02.ctrl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import kr.or.dgit.phonebookv02.dto.Phone;

public class PhoneControl {
	private Map<String, Phone> phoneBook;

	public PhoneControl() {

		phoneBook = new HashMap<>();
		phoneBook.put("홍길동", new Phone("홍길동", "대구", "010-1111-1111"));
		phoneBook.put("김태희", new Phone("김태희", "울산", "010-2222-2222"));
		phoneBook.put("태연", new Phone("태연", "서울", "010-3333-3333"));

	}

	@Override
	public String toString() {
		return String.format("%s", phoneBook);
	}

	public boolean insertPhone(Phone newPhone) {

		if (isExist(newPhone)) {
			return false;
		}
		phoneBook.put(newPhone.getName(), newPhone);
		return true;
	}

	public boolean isExist(Phone newPhone) {
		return phoneBook.containsKey(newPhone.getName());
	}

	public boolean deletePhone(Phone delPhone) {
		if (!isExist(delPhone)) {
			return false;
		}
		phoneBook.remove(delPhone.getName());
		return true;
	}

	/*
	 * public void deletePhone(Phone delPhone) { if (!isExist(delPhone)) {
	 * JOptionPane.showMessageDialog(null, delPhone.getName() +
	 * "등록되지 않은 사용자 입니다."); return; } phoneBook.remove(delPhone.getName()); }
	 */

	public Phone searchPhone(Phone searchPhone) {
		if (!isExist(searchPhone)) {
			// JOptionPane.showMessageDialog(null, searchPhone.getName() + "등록되지
			// 않은 사용자 입니다.");
			return null;
		}
		return phoneBook.get(searchPhone.getName());
		// JOptionPane.showMessageDialog(null, phone);
	}

	public Object[][] showPhones(){
		Object[][] datas = new Object[phoneBook.size()][];
		
		int i =0;
		
		for(Entry<String,Phone>e:phoneBook.entrySet()){
			Object[] arr = new Object[4]; //번호,이름,주소,연락처
			arr[0] = i+1;
			Object[] phoneArr = e.getValue().toArray(); //홍길동,대구,010-1111-1111;//toarray를 새로생성해주었다.
			System.arraycopy(phoneArr, 0,arr,1, phoneArr.length); //배열copy
			//원본 ,phoneArr의 0부터 ,arr의 배열에,1번부터,원본의 길이 복사만큼 
			
			datas[i]=arr;
			System.out.println(Arrays.toString(arr));
			i++;
			
		}
			return datas;
		
	}

	// ���� insertPhone()
	// ���� deletePhone()
	// ã�� searchPhone()
	// ��ü����showPhones()

}
