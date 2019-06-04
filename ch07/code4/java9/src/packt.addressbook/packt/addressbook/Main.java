package packt.addressbook;

import java.util.*;
import java.util.ServiceLoader.Provider;
import java.util.logging.Logger;
import java.util.stream.Stream;

import packt.contact.model.*;
import packt.contact.util.*;
import packt.util.*;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {

		logger.info("Address book viewer application: Started");
		List<Contact> contacts = new ArrayList<>();
		ContactLoader contactLoader = new ContactLoader();
		try {
			contacts = contactLoader.loadContacts("../input.xml");
		} catch (ContactLoadException e) {
			logger.severe(e.getMessage());
			System.exit(0);
		}
		Stream<Provider<SortUtil>> providers = SortUtil.getProviderInstanceLazy();
		SortUtil sortUtil = providers.map(Provider::get)
									 .findAny()
									 .orElse(null);
		sortUtil.sortList(contacts);
		System.out.println(contacts);
		logger.info("Address book viewer application: Completed");
	}
}