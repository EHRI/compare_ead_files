package eu.ehri.compare_ead_files;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

/**
 * Detects differences between two versions of the same EAD file
 * 
 * @author Kepa J. Rodriguez (https://github.com/KepaJRodriguez)
 */
public class App {
	public static void main(String[] args) throws FileNotFoundException,
			XMLStreamException, FactoryConfigurationError {
		System.out.println();
		String eadfile1 = args[0];
		String eadfile2 = args[1];

		SameEADid checkSameEAD = new SameEADid();
		boolean isSame = checkSameEAD.hasSameEADid(eadfile1, eadfile2);

		if (isSame == true) {
			System.out.println("Both ead files have the same eadid.");
			System.out.println();
			
			HashMap<String,String> hashOfNodes1 = CreateHashOfNodes.createHashOfNodes(eadfile1);
			HashMap<String,String> hashOfNodes2 = CreateHashOfNodes.createHashOfNodes(eadfile2);
			
			CompareNodeHashes.compareNodeHashes(hashOfNodes1, hashOfNodes2);
			
		} else {
			if (isSame == false) {
				System.out.println();
				System.out.println("Both ead files have a different eadid.");
				System.out.println("please, check whether there are the right files to be compared");
				System.out.println();
			
			}
		}

	}
}
