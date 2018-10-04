package ca.mcgill.ecse.pds.application;

import ca.mcgill.ecse.pds.model.PDS;
import ca.mcgill.ecse.pds.persistence.PersistenceObjectStream;

public class PdsApplication {
	private static PDS pds;
	private static String filename = "data.pds";
	public static void main(String[] args) {
		
		
		
	}
	
	public static PDS getPDS() {
		if (pds == null) {
			// load model
			pds = load();
		}
 		return pds;
	}
	
	public static void save() {
		PersistenceObjectStream.serialize(pds);
	}
	
	public static PDS load() {
		PersistenceObjectStream.setFilename(filename);
		pds = (PDS) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty BTMS
		if (pds == null) {
			pds = new PDS();
		}
		else {
			//TODO: Enable after adding full persistence
			//pds.reinitialize();
		}
		return pds;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}
}
