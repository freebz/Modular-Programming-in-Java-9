package packt.addressbook;

import java.util.*;
import packt.addressbook.model.*;
import packt.addressbook.util.*;
import packt.util.*;

public class Main {
	public static void main(String[] args) {
		ContactUtil contactUtil = new ContactUtil();
		SortUtil sortUtil = new SortUtil();
		List<Contact> contacts = contactUtil.getContacts();
		sortUtil.sortList(contacts);
		System.out.println(contacts);
	}
}