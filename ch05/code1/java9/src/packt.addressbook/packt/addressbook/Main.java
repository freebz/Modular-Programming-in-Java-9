package packt.addressbook;

import java.util.*;
import java.util.logging.Logger;
import packt.addressbook.model.*;
import packt.addressbook.util.*;
import packt.util.*;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	public static void main(String[] args) {
		logger.info("Address book viewer application: Started");
		ContactUtil contactUtil = new ContactUtil();
		SortUtil sortUtil = new SortUtil();
		List<Contact> contacts = contactUtil.getContacts();
		sortUtil.sortList(contacts);
		System.out.println(contacts);
		logger.info("Address book viewer application: Completed");
	}
}