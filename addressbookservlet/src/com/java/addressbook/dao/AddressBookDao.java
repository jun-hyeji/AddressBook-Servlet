package com.java.addressbook.dao;

import java.util.List;

import com.java.addressbook.vo.AddressBookVO;

public interface AddressBookDao {

		public List<AddressBookVO> getList();
		//	AddressBook 리스트
		
		public List<AddressBookVO> search(String key);
		//	AddressBook에 key포함되어 있는 이름 출력

		public boolean insert(AddressBookVO vo);;
		//	AddressBook 새러운 번호 저장
}
