package eu.ehri.compare_ead_files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class SameEADid {

	public boolean hasSameEADid(String EADfile1, String EADfile2)
			throws FileNotFoundException, XMLStreamException,
			FactoryConfigurationError {
		boolean sameid = false;
		String id1 = "";
		String id2 = "";

		FileInputStream fileInputStreamEAD1 = new FileInputStream(EADfile1);
		XMLEventReader xmlEventReaderEAD1 = XMLInputFactory.newInstance()
				.createXMLEventReader(fileInputStreamEAD1);

		FileInputStream fileInputStreamEAD2 = new FileInputStream(EADfile2);
		XMLEventReader xmlEventReaderEAD2 = XMLInputFactory.newInstance()
				.createXMLEventReader(fileInputStreamEAD2);

		while (xmlEventReaderEAD1.hasNext()) {
			XMLEvent event = xmlEventReaderEAD1.nextEvent();
			if (event.isStartElement()) {
				if (event.asStartElement().getName().getLocalPart()
						.equals("eadid")) {
					 id1 = GetCharacterData.getCharacterData(event, xmlEventReaderEAD1);
					System.out.println("First eadid is " + id1);
				}
			}
		}

		while (xmlEventReaderEAD2.hasNext()) {
			XMLEvent event = xmlEventReaderEAD2.nextEvent();
			if (event.isStartElement()) {
				if (event.asStartElement().getName().getLocalPart()
						.equals("eadid")) {
					 id2 = GetCharacterData.getCharacterData(event, xmlEventReaderEAD2);
					System.out.println("Second eadid is " + id2);
				}
			}
		}	
		
		if (id1.equals(id2)){
			sameid = true;
		}
		
		return sameid;
	}

	

}

