
package com.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProviderService {

    // use a tree map so they become sorted
    private final Map<String, Provider> providers = new TreeMap<String, Provider>();
    
    private static final Log LOG = LogFactory.getLog(ProviderService.class);

    public ProviderService() {
        providers.put("123", new Provider(123, "Mary", "Kome", "555-555-5555", "001 Hill Road, Meghalaya", "11111"));
        providers.put("456", new Provider(456, "Donald", "Duck", "123-456-7890", "100 Capitol Hill, FL", "11111"));
        providers.put("789", new Provider(789, "Narendra", "Bhai", "201-867-5309", "1, JanKalyan Road", "11112"));
    }

    /**
     * Gets a user by the given id
     *
     * @param id  the id of the user
     * @return the user, or <code>null</code> if no user exists
     */
    public Provider getProvider(String id) {
        return providers.get(id);
    }

    /**
     * List all users
     *
     * @return the list of all users
     */
    public Collection<Provider> listProviders() {
        return providers.values();
    }

    /**
     * Updates or creates the given user
     *
     * @param user the user
     */
    public String updateProvider(Provider user) {
        providers.put("" + user.getId(), user);
        return "Added: " + user.getLastName() + ", " + user.getFirstName();
    }
    
    public Collection<Provider> searchByZip(String zip) {
    	zip.trim();
    	List<Provider> searchResults = new ArrayList<Provider>();
    	for (Map.Entry<String, Provider> entry : providers.entrySet())
    	{
    		if (entry.getValue().getZipCode().equalsIgnoreCase(zip))
    		{
    			searchResults.add(entry.getValue());
    		}
    	}    	
    	return searchResults;
    }
}