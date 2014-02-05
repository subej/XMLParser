package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import Parser.XMLParser;


public class Tester {
	private List<String> Coordinates;
	
	@Before
	public void setUp() {
		Coordinates = new ArrayList<String>();
		
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(new XMLParser(Coordinates));
			reader.parse("MapInfo.xml");
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testNumPOIs() {
		assertEquals(7271, Coordinates.size());
	}
}
