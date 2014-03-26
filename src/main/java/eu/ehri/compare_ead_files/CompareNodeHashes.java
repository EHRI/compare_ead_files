package eu.ehri.compare_ead_files;

import java.util.HashMap;

/**
 * Takes two hashes representing two versions of the EAD file and
 * looks for differences
 * 
 * @author Kepa J. Rodriguez (https://github.com/KepaJRodriguez)
 */


public class CompareNodeHashes {
	
	
	public static String compareNodeHashes(HashMap<String, String> ead1,
			HashMap<String, String> ead2) {

		System.out.println();
		System.out.println("First EAD has " + ead1.size() + " nodes");
		System.out.println("Second EAD has " + ead2.size() + " nodes");
		System.out.println();
		
		Boolean difference = false;
		
		for (String key1 : ead1.keySet()) {
			boolean found = false;

			for (String key2 : ead2.keySet()) {
				if (key1.equals(key2)) {
					found = true;
					if (!ead1.get(key1).equals(ead2.get(key2))) {
						difference = true;
						System.out
								.println("Node "
										+ key1
										+ " has different identifier in both EAD files.");
					}
				}
			}
			if (found == false) {
				difference = true;
				System.out
						.println("Node with path "
								+ key1
								+ " of the first EAD file is not present in the second EAD file");
			}
		}

		for (String key2 : ead2.keySet()) {
			boolean found = false;
			for (String key1 : ead1.keySet()) {
				if (key1.equals(key2)) {
					found = true;
				}
			}
			if (found == false) {
				difference = true;
				System.out
						.println("Node with path "
								+ key2
								+ " of the second EAD file is not present in the first EAD file");
			}
		}
		
		if (difference == false){
			System.out.println("No changes found!!");
		}

		return null;
	}

}
