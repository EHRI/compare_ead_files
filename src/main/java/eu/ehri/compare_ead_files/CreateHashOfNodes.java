package eu.ehri.compare_ead_files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import javax.xml.stream.events.Attribute;

/**
 * Represents an EAD file as a hash of paths of a node and 
 * identifiers
 * 
 * @author Kepa J. Rodriguez (https://github.com/KepaJRodriguez)
 */

public class CreateHashOfNodes {

	public static HashMap<String, String> createHashOfNodes(String eadfile)
			throws XMLStreamException, FileNotFoundException {

		System.out.println("Creating hashes");

		FileInputStream fileInputStreamEAD = new FileInputStream(eadfile);
		XMLEventReader xmlEventReaderEAD = XMLInputFactory.newInstance()
				.createXMLEventReader(fileInputStreamEAD);

		HashMap<String, String> nodesandids = new HashMap<String, String>();

		String path = "nopath";
		String identifier = "no_identified";
		
		while (xmlEventReaderEAD.hasNext()) {
			XMLEvent event = xmlEventReaderEAD.nextEvent();

			if (event.isStartElement()) {
				if (event.asStartElement().getName().getLocalPart()
						.equals("did")) {
					path = "nopath";
					identifier = "no_identified";
				}
				if (event.asStartElement().getName().getLocalPart()
						.equals("unitid")) {
					@SuppressWarnings("unchecked")
					Iterator<Attribute> attributes = event.asStartElement()
							.getAttributes();
					while (attributes.hasNext()) {
						Attribute attribute = attributes.next();
						if (attribute.getName().toString().equals("label")) {
							if (attribute.getValue().toString()
									.equals("ehri_structure")) {
								event = xmlEventReaderEAD.nextEvent();
								if (event instanceof Characters) {
									path = event.asCharacters().toString();
								}
							}
							if (attribute.getValue().toString()
									.equals("ehri_main_identifier")) {
								event = xmlEventReaderEAD.nextEvent();
								if (event instanceof Characters) {
									identifier = event.asCharacters()
											.toString();
								}
							}

						}
					}
				}
			}
				if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart()
							.equals("did")) {
						nodesandids.put(path, identifier);
					}
				}

			}


		return nodesandids;
	}
}
