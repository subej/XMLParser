package Parser;

import java.util.List;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler {

	// Remember information being parsed
	private StringBuffer accumulator;
	private Double latAcc;
	public List<String> Coordinates;
	private int acc = 0;
	public XMLParser(List<String> Coordinates){
		this.Coordinates = Coordinates;
	} 
	@Override
	public void startDocument() {
		// Print out a message to let you know something is happening. This is
		// just to help you trace the program executing.
		System.out.println("Start Document!");

		// Use accumulator to remember information parsed. Just initialize for
		// now.

		accumulator = new StringBuffer();
		latAcc = 0.0;
	}

	/**
	 * Called when the parsing of an element starts. (e.g., <book>)
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) {
		 // QWERTY This code works and creates a list of POI's
		// What are we parsing?
		if (qName.toLowerCase().equals("coordinates")) {
			//System.out.println("StartElement: " + qName);
			// Its a POI! write out the isbn
			Coordinates.add(atts.getValue("coordinates"));
			acc++;
		}

		accumulator.setLength(0);
	}

	public void characters(char[] temp, int start, int length) {
		// Remember the value parsed
		accumulator.append(temp, start, length);
	}

	/**
	 * Called when the end of an element is seen. (e.g., </title>)
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	public void endElement(String uri, String localName, String qName) {
		// Print out that we have seen the end of an element
		if(qName.equals("coordinates")){
			System.out.println(" coordinates: " + accumulator);
		}

		if (qName.toLowerCase().equals("lat")) {
			// Maybe we would store the book in some other object in our system
			latAcc = Double.parseDouble(accumulator.toString());
		}

		accumulator.setLength(0);
	}

	/**
	 * Called when the end of the document is reached
	 */
	public void endDocument() {
		// Just let the user know as something to do
		System.out.println("End Document! " + Coordinates.size() + " " + acc);
	}

}



