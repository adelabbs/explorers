package process;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import data.simulation.Item;
import data.simulation.ItemList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

/**
 * Finds and prepares all the available {@link Item} for the
 * {@link Configuration}
 * 
 * @author Adel
 *
 */
public class ConfigurationBuilder {
	private Configuration configuration;

	private static final String DEFAULT_STRINGS_FILE = "ressources/strings.xml";
	private static final String ITEMS_FILE = "ressources/items.xml";

	public ConfigurationBuilder(Configuration configuration) {
		this.configuration = configuration;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * The configuration is built in 2 phases
	 *  -Find all the {@link Item} and fill
	 *  their basic informations using the "items.xml" file 
	 *  The first step uses JAXB unmarshalling 
	 *  -Find the language dependent {@link Item} informations by
	 * reading in the corresponding language file The second step uses javax DOM parser
	 */
	public void buildConfiguration() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ItemList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ItemList items = (ItemList) jaxbUnmarshaller.unmarshal(new File(ITEMS_FILE));

			HashMap<String, String[]> infos = parseInformation(DEFAULT_STRINGS_FILE); // TODO

			for (Item item : items.getItems()) {
				String id = item.getId();
				if (infos.containsKey(id)) {
					String[] itemInfos = infos.get(id);
					if (itemInfos.length >= 2) {
						item.setName(itemInfos[0]);
						item.setDescription(itemInfos[1]);
					}
				}
				configuration.add(item);
				System.out.println(item);
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Utility method to invoke the DOM parser
	 * 
	 * @param file
	 * @return
	 */
	private Document parseDom(String file) {
		try {
			// create parser factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// factory.setValidating(true); //if we need to check a DTD later on

			// Create parser
			DocumentBuilder parser = factory.newDocumentBuilder();

			File xml = new File(file);
			Document document = parser.parse(xml);
			return document;

		} catch (ParserConfigurationException pce) {
			System.err.println("Couldn't configure DOM parser");
		} catch (SAXException se) {
			System.err.println("Couldn't parse document");
		} catch (IOException ioe) {
			System.err.println("I/O Error :" + ioe.getMessage());
		}
		return null;
	}

	/**
	 * This utility method parses the language dependent item informations which are
	 * located in a specific xml file.
	 * 
	 * @param fileName
	 * @return A HashMap<String, String[]> with item ids as keys and an array with
	 *         the remaining item informations
	 */
	private HashMap<String, String[]> parseInformation(String fileName) {
		HashMap<String, String[]> infos = new HashMap<String, String[]>();
		Document dom = parseDom(fileName);
		if (dom != null) {
			NodeList itemNodes = dom.getElementsByTagName("item");
			
			for (int i = 0; i < itemNodes.getLength(); i++) {
				Element element = (Element) itemNodes.item(i);
				if (element.hasAttribute("id")) {
					String itemId = element.getAttribute("id");
					NodeList nameNode = element.getElementsByTagName("name");

					String itemName = (nameNode.getLength() > 0) ? nameNode.item(0).getTextContent() : "";
					NodeList descriptionNode = element.getElementsByTagName("description");

					String itemDescription = (descriptionNode.getLength() > 0)
							? descriptionNode.item(0).getTextContent()
							: "";

					String[] itemInfos = new String[] { itemName, itemDescription };
					infos.put(itemId, itemInfos);
				}
			}
		}
		return infos;
	}

}
